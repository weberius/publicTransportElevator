package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.publicTransportElevator.askfor.AskForElevators;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;
import de.illilli.opendata.service.publicTransportElevator.model.Geometrie;

public class ElevatorsFacade implements Facade {

	List<Elevator> data = new ArrayList<Elevator>();

	public ElevatorsFacade() throws IOException {
		this(ElevatorsFacade.class.getClassLoader().getResource("/fahrtreppen.json"));
	}

	public ElevatorsFacade(URL url) throws IOException {

		AskFor<InputStream> askFor = new AskForElevators(url);

		FeatureCollection featureCollection = new ObjectMapper()
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.readValue(askFor.getData(), FeatureCollection.class);

		List<Feature> features = featureCollection.getFeatures();
		for (Feature feature : features) {

			Elevator elevator = new Elevator();

			Map<String, Object> properties = feature.getProperties();
			elevator.setId((String) properties.get("Kennung"));
			elevator.setBezeichnung((String) properties.get("Bezeichnung"));
			elevator.setHaltestellenbereich((String) properties.get("Haltestellenbereich"));
			elevator.setInfo((String) properties.get("Info"));

			if (feature.getGeometry() instanceof Point) {
				Point point = (Point) feature.getGeometry();
				Geometrie geometrie = new Geometrie();
				geometrie.setType("Point");
				geometrie.setLat(point.getCoordinates().getLatitude());
				geometrie.setLng(point.getCoordinates().getLongitude());
				elevator.setGeometrie(geometrie);
			}

			data.add(elevator);
		}

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new Gson().toJson(data);
	}

}
