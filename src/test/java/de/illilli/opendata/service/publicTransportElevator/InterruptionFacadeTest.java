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
import de.illilli.opendata.service.publicTransportElevator.jdbc.StoringResult;

/**
 * Testing the InterruptionFacade. Can I put Information to Database. Checking
 * not all data, but some values. Because of the need of a database, it's marked
 * with Category IntegrationTest.
 */
@Category(IntegrationTest.class)
public class InterruptionFacadeTest extends Main {

	/**
	 * Test with real data from 2019-01-22. Try to store them to database and check
	 * the StoringResult from inserting.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@Test
	public void testStoringResult() throws ClassNotFoundException, IOException, SQLException, NamingException {

		// setting up data
		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.20190122.json");
		Facade<StoringResult> facade = new InterruptionFacade(url);
		StoringResult result = facade.getData();
		// what i expect
		int expectedInserted = 34;
		int expectedSkipped = 1;
		int expectedUpdated = 0;
		// test
		Assert.assertEquals(expectedInserted, result.getInserted());
		Assert.assertEquals(expectedSkipped, result.getSkipped());
		Assert.assertEquals(expectedUpdated, result.getUpdated());

	}

}
