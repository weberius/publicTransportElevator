package de.illilli.opendata.service.publicTransportElevator.converter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.askfor.AskForInterruption;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class Url2InterruptionList implements Converter<List<Interruption>, URL> {

	@Override
	public List<Interruption> getAsObject(URL value) {

		List<Interruption> data = new ArrayList<Interruption>();

		AskFor<InputStream> askFor;
		FeatureCollection featureCollection = null;
		try {
			askFor = new AskForInterruption(value);
			featureCollection = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
					.readValue(askFor.getData(), FeatureCollection.class);
		} catch (IOException e) {
			// TODO: use Logger
			e.printStackTrace();
		}

		List<Feature> features = featureCollection.getFeatures();
		for (Feature feature : features) {

			Interruption interruption = new Interruption();

			Map<String, Object> properties = feature.getProperties();
			interruption.setElevatorid((String) properties.get("Kennung"));
			try {
				// parse something like that '2019-01-22T21:39:09Z'
				String format = "yyyy-MM-dd'T'HH:mm:ss";
				long timestamp = new SimpleDateFormat(format).parse((String) properties.get("timestamp")).getTime();
				interruption.setTimestamp(new Date(timestamp));
			} catch (ParseException e) {
				// TODO: use Logger
				e.printStackTrace();
			}

			data.add(interruption);
		}
		return data;
	}

}
