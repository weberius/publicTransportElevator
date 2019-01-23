package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectStop implements Select<ElevatorDTO> {

	static final String sqlFileName = "/sql/selectElevator.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectElevator.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public Class<ElevatorDTO> getDtoClazz() {
		return ElevatorDTO.class;
	}

}
