package de.illilli.opendata.service.publicTransportElevator.converter;

import org.junit.Assert;
import org.junit.Test;

import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Coordinate;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Elevator2dtoTest {

	@Test
	public void testCoordinateX() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		double expected = 50.95442714;
		double actual = dto.getGeom().getX();
		double delta = 0.0;

		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void testCoordinateY() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		double expected = 6.90961502;
		double actual = dto.getGeom().getY();
		double delta = 0.0;

		Assert.assertEquals(expected, actual, delta);

	}

	@Test
	public void testBezeichnung() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		String expected = "Zwischenebene West - Mittelbahnsteig aufwärts";
		String actual = dto.getBezeichnung();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testId() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		String expected = "155-53";
		String actual = dto.getId();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testHaltestellenbereich() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		int expected = 255;
		int actual = dto.getHaltestellenbereich();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testInfo() {

		ElevatorDTO dto = new Elevator2dto().getAsObject(Elevator2dtoTest.elevator());

		String expected = "eine information";
		String actual = dto.getInfo();

		Assert.assertEquals(expected, actual);

	}

	static Elevator elevator() {

		Elevator value = new Elevator();
		value.setBezeichnung("Zwischenebene West - Mittelbahnsteig aufwärts");
		value.setHaltestellenbereich(255);
		value.setId("155-53");
		value.setInfo("eine information");
		value.setCoordinate(new Coordinate(50.95442714, 6.90961502));

		return value;
	}

}
