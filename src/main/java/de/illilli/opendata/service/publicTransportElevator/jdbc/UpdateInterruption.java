package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class UpdateInterruption implements InsertOrUpdate {

	static final String sqlFileName = "/sql/updateInterruption.sql";
	private InterruptionDTO dtofromDb;
	private InterruptionDTO dtoFromSource;

	public UpdateInterruption(InterruptionDTO dtofromDb, InterruptionDTO dtoFromSource) {
		this.dtofromDb = dtofromDb;
		this.dtoFromSource = dtoFromSource;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { dtoFromSource.getTime(), dtoFromSource.getStop(), dtoFromSource.getElevatorid(),
				dtofromDb.getStart() };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertElevator.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
