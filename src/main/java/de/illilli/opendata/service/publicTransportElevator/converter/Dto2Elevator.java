package de.illilli.opendata.service.publicTransportElevator.converter;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Coordinate;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Dto2Elevator implements Converter<Elevator, ElevatorDTO> {

	@Override
	public Elevator getAsObject(ElevatorDTO dto) {

		Elevator elevator = new Elevator();

		elevator.setId(dto.getId());
		elevator.setBezeichnung(dto.getBezeichnung());
		elevator.setHaltestellenbereich(dto.getHaltestellenbereich());
		elevator.setInfo(dto.getInfo() == null ? "" : dto.getInfo());

		Coordinate coordinate = new Coordinate(dto.getGeom().getX(), dto.getGeom().getY());
		elevator.setCoordinate(coordinate);

		return elevator;

	}

}
