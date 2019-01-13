package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class ElevatorsFacadeTest {

	@Test
	@Ignore
	public void testGetJson() throws IOException {
		URL url = this.getClass().getClassLoader().getResource("/fahrtreppen.json");
		Facade facade = new ElevatorsFacade(url);
		System.out.println(facade.getJson());
		Assert.assertTrue(facade.getJson() != null);
	}

}
