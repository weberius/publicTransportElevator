package de.illilli.opendata.service.publicTransportElevator.askfor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Config;

/**
 * Diese Klasse fragt die Schnittstelle zu den Fahrtreppen ab.
 */
public class AskForElevators implements AskFor<InputStream> {

	private InputStream data;
	public static final String ENCODING = Config.getProperty("encoding");

	/**
	 * Die Url ist in der config.properties definiert.
	 * 
	 * @throws IOException
	 */
	public AskForElevators() throws IOException {
		this(new URL(Config.getProperty("kvb.fahrtreppen.url")));
	}

	/**
	 * Die url muss hier bereits fertig sein. Es wird aus dem inputstream mit Hilfe
	 * der IOUtils ein String erstellt.
	 * 
	 * @param url
	 *            die fertige url
	 * @throws IOException
	 */
	public AskForElevators(URL url) throws IOException {
		this.data = url.openStream();
	}

	@Override
	public InputStream getData() {
		return this.data;
	}

}
