package de.illilli.opendata.service.publicTransportElevator.jdbc;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the StoringResult. Adding values; merging with other StoringResult.
 */
public class StoringResultTest {

	@Test
	public void testAddAll() {

		// setting up a result
		StoringResult result = new StoringResult();
		result.addInserted(1);
		result.addSkipped(1);
		result.addUpdated(1);
		// I need a second one where i can merge into
		StoringResult second = new StoringResult();
		second.addInserted(1);
		second.addSkipped(1);
		second.addUpdated(1);
		// merge them
		second.addAll(result);
		// what i expect
		int expectedInserted = 1;
		int expectedSkipped = 1;
		int expectedUpdated = 1;
		// test
		Assert.assertEquals(expectedInserted, result.getInserted());
		Assert.assertEquals(expectedSkipped, result.getSkipped());
		Assert.assertEquals(expectedUpdated, result.getUpdated());
		// what i expect
		expectedInserted = 2;
		expectedSkipped = 2;
		expectedUpdated = 2;
		// test
		Assert.assertEquals(expectedInserted, second.getInserted());
		Assert.assertEquals(expectedSkipped, second.getSkipped());
		Assert.assertEquals(expectedUpdated, second.getUpdated());

	}

	@Test
	public void testAddInserted() {
		// setting up a result
		StoringResult result = new StoringResult();
		result.addInserted(1);
		// what i expect
		int expectedInserted = 1;
		int expectedSkipped = 0;
		int expectedUpdated = 0;
		// test
		Assert.assertEquals(expectedInserted, result.getInserted());
		Assert.assertEquals(expectedSkipped, result.getSkipped());
		Assert.assertEquals(expectedUpdated, result.getUpdated());
	}

	@Test
	public void testAddUpdated() {
		// setting up a result
		StoringResult result = new StoringResult();
		result.addUpdated(1);
		// what i expect
		int expectedInserted = 0;
		int expectedSkipped = 0;
		int expectedUpdated = 1;
		// test
		Assert.assertEquals(expectedInserted, result.getInserted());
		Assert.assertEquals(expectedSkipped, result.getSkipped());
		Assert.assertEquals(expectedUpdated, result.getUpdated());
	}

	@Test
	public void testAddSkipped() {
		// setting up a result
		StoringResult result = new StoringResult();
		result.addSkipped(1);
		// what i expect
		int expectedInserted = 0;
		int expectedSkipped = 1;
		int expectedUpdated = 0;
		// test
		Assert.assertEquals(expectedInserted, result.getInserted());
		Assert.assertEquals(expectedSkipped, result.getSkipped());
		Assert.assertEquals(expectedUpdated, result.getUpdated());
	}

}
