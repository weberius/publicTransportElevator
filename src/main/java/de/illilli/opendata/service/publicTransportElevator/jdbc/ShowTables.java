package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class ShowTables implements Select<TableNamesDTO> {

	static final String sqlFileName = "/sql/showTables.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectVersion.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[0];
	}

	@Override
	public Class<TableNamesDTO> getDtoClazz() {
		return TableNamesDTO.class;
	}

}
