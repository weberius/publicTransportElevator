package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;

import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class SelectInterruptionByElevatorId extends SelectInterruption {

	private String elevatorId;

	public SelectInterruptionByElevatorId(Interruption interruption) {
		elevatorId = interruption.getElevatorid();
	}

	@Override
	public String getSql() throws IOException {
		StringBuffer sql = new StringBuffer(super.getSql());
		sql.append("\n");
		sql.append("WHERE elevatorid = ?");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { elevatorId };
	}

}
