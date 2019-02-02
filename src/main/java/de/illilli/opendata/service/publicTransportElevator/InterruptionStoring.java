package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.illilli.jdbc.InsertDao;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.publicTransportElevator.converter.Interruption2dto;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InsertInterruption;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InterruptionDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectInterruptionByElevatorId;
import de.illilli.opendata.service.publicTransportElevator.jdbc.StoringResult;
import de.illilli.opendata.service.publicTransportElevator.jdbc.UpdateInterruption;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

/**
 * With this class interreptions from source can be stored to database. It needs
 * the list from source, checks some conditions ans stores all data needed.
 */
public class InterruptionStoring {

	private List<Interruption> interruptionList = new ArrayList<>();
	private Connection connection;
	private StoringResult result = new StoringResult();

	/**
	 * this needs the data and a connection to storing to database.
	 * 
	 * @param list
	 * @param connection
	 */
	public InterruptionStoring(List<Interruption> list, Connection connection) {
		this.interruptionList = list;
		this.connection = connection;
	}

	/**
	 * Iterate the Interruption list. For comparison use elevatorid and time from
	 * source.
	 * <ul>
	 * <li>check for occurence in db, if none: insert</li>
	 * <li>check for new stop is before past start: update</li>
	 * <li>check for new stop is after past start: insert</li>
	 * <li>check for duplicated values in source: skip storing</li>
	 * </ul>
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public void storeToDb() throws SQLException, IOException {

		for (Interruption interruption : this.interruptionList) {

			List<InterruptionDTO> dtoList = new SelectListDao<InterruptionDTO>(
					new SelectInterruptionByElevatorId(interruption), connection).execute();

			if (dtoList.isEmpty()) {
				// if no interruption does'nt exists, insert just like this
				new InsertDao(new InsertInterruption(interruption), connection).execute();
				result.addInserted(1);
			} else {
				// check only last occurence
				InterruptionDTO dtofromDb = dtoList.get(dtoList.size() - 1);
				InterruptionDTO dtoFromSource = new Interruption2dto().getAsObject(interruption);
				if (dtofromDb.equals(dtoFromSource)) {
					// do nothing duplicate values in source; data is alreade stored to db
					// TODO: do logging
					System.out.println("duplicate value: " + dtofromDb.toString());
					result.addSkipped(1);
				} else {
					if (dtofromDb.getStop().before(dtoFromSource.getStart())) {
						// update if stop from db is < start from source
						new InsertDao(new UpdateInterruption(dtoFromSource), connection).execute();
						result.addUpdated(1);
					} else {
						// insert if stop is > start
						new InsertDao(new InsertInterruption(dtoFromSource), connection).execute();
						result.addInserted(1);
					}
				}
			}
		}
	}

	public StoringResult getResult() {
		return result;
	}

}
