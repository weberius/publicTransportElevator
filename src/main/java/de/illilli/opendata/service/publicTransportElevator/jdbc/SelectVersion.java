package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectVersion implements Select<VersionDTO> {

	static final String sqlFileName = "/sql/selectVersion.sql";

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
	public Class<VersionDTO> getDtoClazz() {
		return VersionDTO.class;
	}

}
