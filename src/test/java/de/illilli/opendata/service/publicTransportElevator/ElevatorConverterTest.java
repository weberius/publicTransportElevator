package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

@Category(IntegrationTest.class)
public class ElevatorConverterTest {

	@Test
	public void testGetJson() throws IOException {
		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Converter<List<Elevator>, URL, String> converter = new ElevatorConverter();
		List<Elevator> elevatorList = converter.getAsObject(url);
		// System.out.println(elevatorList);
		Assert.assertTrue(elevatorList != null);
	}

}
