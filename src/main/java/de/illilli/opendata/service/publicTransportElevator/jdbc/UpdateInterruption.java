package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;

public class UpdateInterruption implements InsertOrUpdate {

	static final String sqlFileName = "/sql/updateInterruption.sql";
	private InterruptionDTO dto;

	public UpdateInterruption(InterruptionDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { dto.getStop(), dto.getElevatorid(), dto.getTime() };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertElevator.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
