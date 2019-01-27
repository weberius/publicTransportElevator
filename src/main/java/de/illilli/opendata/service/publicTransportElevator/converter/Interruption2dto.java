package de.illilli.opendata.service.publicTransportElevator.converter;

import java.sql.Timestamp;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InterruptionDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class Interruption2dto implements Converter<InterruptionDTO, Interruption> {

	@Override
	public InterruptionDTO getAsObject(Interruption value) {

		InterruptionDTO dto = new InterruptionDTO();
		dto.setElevatorid(value.getElevatorid());
		long time = value.getTimestamp().getTime();
		dto.setTime(new Timestamp(time));
		dto.setPeriod(Integer.parseInt(Config.getProperty("period")));
		long start = time;
		dto.setStart(new Timestamp(start));
		// calculate milliseconds from minutes
		long stop = start + (Integer.parseInt(Config.getProperty("period")) * 60 * 1000);
		dto.setStop(new Timestamp(stop));

		return dto;

	}

}
