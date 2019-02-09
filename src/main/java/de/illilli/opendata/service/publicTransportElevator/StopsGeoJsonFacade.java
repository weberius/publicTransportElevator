package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.publicTransportElevator.converter.Elevator2Feature;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class StopsGeoJsonFacade extends StopsFacade {

	private FeatureCollection featureCollection = new FeatureCollection();
	private List<Feature> featureList = new ArrayList<Feature>();

	public StopsGeoJsonFacade(String id) throws ClassNotFoundException, IOException, SQLException, NamingException {
		super(id);
		for (Elevator elevator : super.data) {
			featureList.add(new Elevator2Feature().getAsObject(elevator));
		}
		featureCollection.addAll(featureList);
	}

	@Override
	public String getData() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
