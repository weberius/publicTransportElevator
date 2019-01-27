package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;
import de.illilli.opendata.service.publicTransportElevator.converter.Interruption2dto;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class InsertInterruption implements InsertOrUpdate {

	static final String sqlFileName = "/sql/InsertInterruption.sql";
	private InterruptionDTO dto;

	public InsertInterruption(Interruption interruption) {
		this.dto = new Interruption2dto().getAsObject(interruption);
	}

	public InsertInterruption(InterruptionDTO dto) {
		this.dto = dto;
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				this.dto.getElevatorid(), this.dto.getTime(), this.dto.getStart(), this.dto.getStop(),
				this.dto.getPeriod() };
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertElevator.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
