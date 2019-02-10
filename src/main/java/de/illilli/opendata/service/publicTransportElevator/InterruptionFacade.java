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

public class InterruptionFacade implements Facade<StoringResult> {

	StoringResult result = new StoringResult();

	public InterruptionFacade() throws IOException, ClassNotFoundException, SQLException, NamingException {
		this(new URL(Config.getProperty("kvb.fahrtreppen_gestoert.url")));
	}

	public InterruptionFacade(URL url) throws IOException, ClassNotFoundException, SQLException, NamingException {

		Connection connection = ConnectionFactory.getConnection();

		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		for (Interruption interruption : interruptionList) {
			StoringResult result = new Interruption2Database(connection).save(interruption);
			this.result.addAll(result);
		}

	}

	@Override
	public StoringResult getData() throws JsonProcessingException {
		return this.result;
	}

}
