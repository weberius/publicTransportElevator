package de.illilli.opendata.service.publicTransportElevator;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
public class ServiceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(Service.class);
	}

	/**
	 * Test to see that the restend-point 'elevators' exists.
	 */
	@Test
	public void testPing() {
		final int status = target().path("elevators").request().head().getStatus();
		assertEquals(Response.Status.OK.getStatusCode(), status);
	}

}
