package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportElevator.converter.Url2InterruptionList;
import de.illilli.opendata.service.publicTransportElevator.jdbc.StoringResult;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class InterruptionFacade implements Facade<String> {

	StoringResult result;

	public InterruptionFacade() throws IOException, ClassNotFoundException, SQLException, NamingException {
		this(new URL(Config.getProperty("kvb.fahrtreppen_gestoert.url")));
	}

	public InterruptionFacade(URL url) throws IOException, ClassNotFoundException, SQLException, NamingException {

		Connection connection = ConnectionFactory.getConnection();

		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, connection);
		storing.storeToDb();
		this.result = storing.getResult();

	}

	@Override
	public String getData() throws JsonProcessingException {
		return this.result.toString();
	}

}
