package de.illilli.opendata.service.publicTransportElevator.converter;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InterruptionDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class Dto2Interruption implements Converter<Interruption, InterruptionDTO> {

	@Override
	public Interruption getAsObject(InterruptionDTO dto) {
		Interruption interruption = new Interruption();
		interruption.setElevatorid(dto.getElevatorid());
		interruption.setTimestamp(dto.getTime());
		interruption.setStart(dto.getStart());
		interruption.setStop(dto.getStop());
		return interruption;
	}

}
