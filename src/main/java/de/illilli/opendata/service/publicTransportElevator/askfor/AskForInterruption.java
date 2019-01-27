package de.illilli.opendata.service.publicTransportElevator.askfor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Config;

public class AskForInterruption implements AskFor<InputStream> {

	private InputStream data;
	public static final String ENCODING = Config.getProperty("encoding");

	public AskForInterruption() throws IOException {
		this(new URL(Config.getProperty("kvb.fahrtreppen_gestoert.url")));
	}

	public AskForInterruption(URL url) throws IOException {
		this.data = url.openStream();
	}

	@Override
	public InputStream getData() {
		return this.data;
	}

}
