package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

public class Interruption2Database {

	private StoringResult result = new StoringResult();
	private Connection connection;

	public Interruption2Database(Connection connection) {
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
	public StoringResult save(Interruption interruption) throws SQLException, IOException {

		// System.out.println("interruption: " + interruption.toString());

		List<InterruptionDTO> dtoList = new SelectListDao<InterruptionDTO>(
				new SelectInterruptionByElevatorId(interruption), connection).execute();

		// System.out.println("dtoList: " + dtoList.toString());

		if (dtoList.isEmpty()) {
			// if no interruption does'nt exists, insert just like this
			new InsertDao(new InsertInterruption(interruption), connection).execute();
			result.addInserted(1);
			// System.out.println("insert:\t" + interruption.toString());
		} else {
			// check only last occurence
			InterruptionDTO dtofromDb = dtoList.get(dtoList.size() - 1);
			InterruptionDTO dtoFromSource = new Interruption2dto().getAsObject(interruption);
			if (dtofromDb.equals(dtoFromSource)) {
				// do nothing duplicate values in source; data is alreade stored to db
				// TODO: do logging
				// System.out.println("duplicate value: " + dtofromDb.toString());
				// System.out.println("skip:\t" + dtofromDb.toString());
				result.addSkipped(1);
			} else {
				if (dtofromDb.getStop().equals(dtoFromSource.getTime())) {
					// update if stop from db is < start from source

					// System.out.println("update:\t" + interruption.toString());
					// System.out.println("update:\t" + dtofromDb.toString());

					new InsertDao(new UpdateInterruption(dtofromDb, dtoFromSource), connection).execute();
					result.addUpdated(1);
				} else {
					// insert if stop is > start
					// System.out.println("insert:\t" + interruption.toString());
					// System.out.println("insert:\t" + dtofromDb.toString());
					new InsertDao(new InsertInterruption(dtoFromSource), connection).execute();
					result.addInserted(1);
				}
			}
		}

		return result;

	}
}
