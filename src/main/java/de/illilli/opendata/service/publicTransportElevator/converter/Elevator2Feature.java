package de.illilli.opendata.service.publicTransportElevator.converter;

import java.util.HashMap;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.Point;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Elevator2Feature implements Converter<Feature, Elevator> {

	@Override
	public Feature getAsObject(Elevator elevator) {

		Feature feature = new Feature();
		feature.setId(elevator.getId() + "");

		Map<String, Object> properties = new HashMap<>();
		properties.put("bezeichnung", elevator.getBezeichnung());
		properties.put("haltestellenbereich", elevator.getHaltestellenbereich());
		properties.put("info", elevator.getInfo());
		feature.setProperties(properties);

		Point point = new Point(elevator.getCoordinate().getLng(), elevator.getCoordinate().getLat());
		feature.setGeometry(point);

		return feature;
	}

}
