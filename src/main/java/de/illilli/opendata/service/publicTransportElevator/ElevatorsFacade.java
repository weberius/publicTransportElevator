package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportElevator.converter.Dto2Elevator;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectElevator;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectElevatorById;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class ElevatorsFacade implements Facade<String> {

	List<Elevator> data = new ArrayList<Elevator>();

	public ElevatorsFacade() throws IOException, SQLException, ClassNotFoundException, NamingException {

		Connection connection = ConnectionFactory.getConnection();

		// read from database
		List<ElevatorDTO> dtoList = new SelectListDao<ElevatorDTO>(new SelectElevator(), connection).execute();
		for (ElevatorDTO dto : dtoList) {
			Elevator elevator = new Dto2Elevator().getAsObject(dto);
			data.add(elevator);
		}
	}

	public ElevatorsFacade(String id) throws IOException, SQLException, ClassNotFoundException, NamingException {

		Connection connection = ConnectionFactory.getConnection();

		// read from database
		List<ElevatorDTO> dtoList = new SelectListDao<ElevatorDTO>(new SelectElevatorById(id), connection).execute();
		for (ElevatorDTO dto : dtoList) {
			Elevator elevator = new Dto2Elevator().getAsObject(dto);
			data.add(elevator);
		}
	}

	@Override
	public String getData() throws JsonProcessingException {
		return new Gson().toJson(data);
	}

}
