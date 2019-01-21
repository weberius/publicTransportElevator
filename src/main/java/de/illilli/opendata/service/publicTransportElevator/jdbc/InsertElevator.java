package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.InsertOrUpdate;
import de.illilli.opendata.service.publicTransportElevator.converter.Elevator2dto;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class InsertElevator implements InsertOrUpdate {

	static final String sqlFileName = "/sql/insertElevator.sql";
	private ElevatorDTO dto;

	public InsertElevator(Elevator elevator) {
		this.dto = new Elevator2dto().getAsObject(elevator);
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { //
				this.dto.getId(), //
				dto.getBezeichnung(), //
				dto.getHaltestellenbereich(), //
				dto.getInfo(), //
				dto.getGeom() //
		};
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = InsertElevator.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

}
