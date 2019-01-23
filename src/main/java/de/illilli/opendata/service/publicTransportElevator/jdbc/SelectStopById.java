package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;

public class SelectStopById extends SelectStop {

	private String id;

	public SelectStopById(String id) {
		this.id = id;
	}

	@Override
	public String getSql() throws IOException {
		StringBuffer sql = new StringBuffer(super.getSql());
		sql.append("\n");
		sql.append("WHERE haltestellenbereich = ? ");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.id };
	}

}
