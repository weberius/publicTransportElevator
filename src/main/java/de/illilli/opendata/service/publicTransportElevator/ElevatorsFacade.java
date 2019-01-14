package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class ElevatorsFacade implements Facade {

	List<Elevator> data = new ArrayList<Elevator>();
	Converter<List<Elevator>, URL, String> converter = new ElevatorConverter();

	public ElevatorsFacade() throws IOException {
		this(ElevatorsFacade.class.getClassLoader().getResource("fahrtreppen.json"));
	}

	public ElevatorsFacade(URL url) throws IOException {

		this.data = converter.getAsObject(url);

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return converter.getAsString(data);
	}

}
