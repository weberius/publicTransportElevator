package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;

public class SelectElevatorById extends SelectElevator {

	private String id;

	public SelectElevatorById(String id) {
		this.id = id;
	}

	@Override
	public String getSql() throws IOException {
		StringBuffer sql = new StringBuffer(super.getSql());
		sql.append("\n");
		sql.append("WHERE id = ? ");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { this.id };
	}

}
