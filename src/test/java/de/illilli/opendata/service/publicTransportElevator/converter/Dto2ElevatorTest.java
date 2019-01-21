package de.illilli.opendata.service.publicTransportElevator.converter;

import org.junit.Assert;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Dto2ElevatorTest {

	@Test
	public void testBezeichnung() {
		ElevatorDTO dto = Dto2ElevatorTest.getElevatorDTO();
		Converter<Elevator, ElevatorDTO> converter = new Dto2Elevator();
		Elevator elevator = converter.getAsObject(dto);
		String expected = "Zwischenebene West - Mittelbahnsteig aufwärts";
		String actual = elevator.getBezeichnung();
		Assert.assertEquals(expected, actual);
	}

	static ElevatorDTO getElevatorDTO() {
		ElevatorDTO dto = new ElevatorDTO();
		dto.setBezeichnung("Zwischenebene West - Mittelbahnsteig aufwärts");
		dto.setHaltestellenbereich(255);
		dto.setId("155-53");
		dto.setInfo("eine information");

		Coordinate coordinate = new Coordinate(50.95442714, 6.90961502);
		Point point = new GeometryFactory().createPoint(coordinate);
		dto.setGeom(point);

		return dto;
	}
}
