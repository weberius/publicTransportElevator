package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

import de.illilli.opendata.service.Facade;

@Category(IntegrationTest.class)
public class ElevatorsFacadeTest extends Main {

	@Test
	public void testGetJson() throws IOException, ClassNotFoundException, SQLException, NamingException {
		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Facade facade = new ElevatorsFacade();
		Assert.assertTrue(facade.getJson() != null);
		// System.out.println(facade.getJson());
	}

}
