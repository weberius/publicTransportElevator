package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectInterruption implements Select<InterruptionDTO> {

	static final String sqlFileName = "/sql/selectInterruption.sql";

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
	public Class<InterruptionDTO> getDtoClazz() {
		return InterruptionDTO.class;
	}

}
