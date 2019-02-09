package de.illilli.opendata.service.publicTransportElevator.converter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Coordinate;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

/**
 * Converts the data given by url to an List of Elevators.
 */
public class Url2ElevatorList implements Converter<List<Elevator>, URL> {

	@Override
	public List<Elevator> getAsObject(URL value) {

		List<Elevator> data = new ArrayList<Elevator>();

		FeatureCollection featureCollection = null;
		try {
			featureCollection = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
					.readValue(new URL2InputStream().getAsObject(value), FeatureCollection.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Feature> features = featureCollection.getFeatures();
		for (Feature feature : features) {

			Elevator elevator = new Elevator();

			Map<String, Object> properties = feature.getProperties();
			elevator.setId((String) properties.get("Kennung"));
			elevator.setBezeichnung((String) properties.get("Bezeichnung"));
			int haltestellenbereich = Integer.parseInt((String) properties.get("Haltestellenbereich"));
			elevator.setHaltestellenbereich(haltestellenbereich);
			elevator.setInfo((String) properties.get("Info"));

			if (feature.getGeometry() instanceof Point) {
				Point point = (Point) feature.getGeometry();
				elevator.setCoordinate(
						new Coordinate(point.getCoordinates().getLatitude(), point.getCoordinates().getLongitude()));
			}

			data.add(elevator);
		}
		return data;
	}

}
