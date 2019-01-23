package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

@Path("elevators")
public class Service {

	// private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

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
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getElevators() throws IOException, ClassNotFoundException, SQLException, NamingException {

		Facade facade = null;

		String format = request.getParameter("format");
		if (Format.geojson.name().equals(format)) {
			facade = new ElevatoresGeoJsonFacade();
		} else {
			facade = new ElevatorsFacade();
		}

		return Response.status(Status.OK).entity(facade.getJson()).build();

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public Response getElevators(@PathParam("id") String id) throws IOException, ClassNotFoundException, SQLException, NamingException {

		Facade facade = null;

		String format = request.getParameter("format");
		if (Format.geojson.name().equals(format)) {
			facade = new ElevatoresGeoJsonFacade(id);
		} else {
			facade = new ElevatorsFacade(id);
		}

		return Response.status(Status.OK).entity(facade.getJson()).build();

	}
}
