package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportElevator.askfor.AskForInterruption;

public class InterruptionFacade implements Facade {

	public InterruptionFacade() throws IOException {
		AskFor<InputStream> askFor = new AskForInterruption();
		InputStream in = askFor.getData();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return null;
	}

}
