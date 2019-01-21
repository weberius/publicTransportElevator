package de.illilli.opendata.service.publicTransportElevator.converter;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Elevator2Insert implements Converter<String, Elevator> {

	@Override
	public String getAsObject(Elevator elevator) {

		StringBuilder insertElevator = null;

		insertElevator = new StringBuilder("insert into elevator values (");
		insertElevator.append("'" + elevator.getId() + "'");
		insertElevator.append(",");
		insertElevator.append("'" + elevator.getBezeichnung() + "'");
		insertElevator.append(",");
		insertElevator.append(elevator.getHaltestellenbereich());
		insertElevator.append(",");
		insertElevator.append("'" + elevator.getInfo() + "'");
		insertElevator.append(",");
		insertElevator
				.append("'POINT(" + elevator.getCoordinate().getLat() + " " + elevator.getCoordinate().getLng() + ")'");
		insertElevator.append(")");

		return insertElevator.toString();
	}

}
