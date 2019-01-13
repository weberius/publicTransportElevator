package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Facade;

@Path("elevators")
public class Service {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * pinging with head request
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@HEAD
	public Response ping() throws JsonProcessingException {
		return Response.status(Status.OK).build();
	}

	/**
	 * <p>
	 * Returns elevators; use filter "failure" to filter only those not working or
	 * only those working.
	 * </p>
	 * <ul>
	 * Filter:
	 * <li>failure=yes: returns only the elevator with failure</li>
	 * <li>failure=no: returns only the elevator without failure</li>
	 * </ul>
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getElevators() throws IOException {

		Facade facade = new ElevatorsFacade();
		return Response.status(Status.OK).entity(facade.getJson()).build();

	}

}
