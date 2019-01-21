package de.illilli.opendata.service.publicTransportElevator.converter;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Elevator2dto implements Converter<ElevatorDTO, Elevator> {

	@Override
	public ElevatorDTO getAsObject(Elevator elevator) {

		ElevatorDTO dto = new ElevatorDTO();
		dto.setId(elevator.getId());
		dto.setBezeichnung(elevator.getBezeichnung());
		dto.setHaltestellenbereich(elevator.getHaltestellenbereich());
		dto.setInfo(elevator.getInfo() == null ? "" : elevator.getInfo());

		Coordinate coordinate = new Coordinate(elevator.getCoordinate().getLat(), elevator.getCoordinate().getLng());
		Point point = new GeometryFactory().createPoint(coordinate);
		dto.setGeom(point);
		return dto;

	}

}
