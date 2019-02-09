package de.illilli.opendata.service.publicTransportElevator.converter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.illilli.opendata.service.Converter;

/**
 * Diese Klasse fragt die Schnittstelle zu den Fahrtreppen ab.
 */
public class URL2InputStream implements Converter<InputStream, URL> {

	@Override
	public InputStream getAsObject(URL url) {
		InputStream data = null;
		try {
			data = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
