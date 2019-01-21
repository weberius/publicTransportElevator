package de.illilli.opendata.service.publicTransportElevator.converter;

import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Elevator2InsertTest {

	@Test
	public void testStartsWithInsert() {
		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Converter<List<Elevator>, URL> converter = new Url2ElevatorList();
		List<Elevator> elevatorList = converter.getAsObject(url);

		for (Elevator elevator : elevatorList) {
			String insert = new Elevator2Insert().getAsObject(elevator);
			String prefix = "insert into elevator values";
			Assert.assertTrue(insert.startsWith(prefix));
			System.out.println(insert);
		}

	}

}
