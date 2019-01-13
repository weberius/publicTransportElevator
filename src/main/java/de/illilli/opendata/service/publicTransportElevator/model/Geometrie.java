package de.illilli.opendata.service.publicTransportElevator.model;

public class Geometrie {

	public Geometrie() {

	}

	/**
	 * Eindeutige ID des Datensatzes
	 */
	private String id;

	/**
	 * Die Elevator-ID
	 */
	private String elevatorid;

	/**
	 * Type des geometrischen Objektes
	 */
	private String type;

	/**
	 * Latitude des geometrischen Objektes
	 */
	private double lat;

	/**
	 * Longitude des geometrischen Objektes
	 */
	private double lng;

	/**
	 * Projektion:
	 * <a href="http://spatialreference.org/ref/epsg/wgs-84/">spatialreference.org:
	 * EPSG:4326</a>
	 */
	private String epsg = "epsg:4326";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getElevatorid() {
		return elevatorid;
	}

	public void setElevatorid(String elevatorid) {
		this.elevatorid = elevatorid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getEpsg() {
		return epsg;
	}

	public void setEpsg(String epsg) {
		this.epsg = epsg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elevatorid == null) ? 0 : elevatorid.hashCode());
		result = prime * result + ((epsg == null) ? 0 : epsg.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometrie other = (Geometrie) obj;
		if (elevatorid == null) {
			if (other.elevatorid != null)
				return false;
		} else if (!elevatorid.equals(other.elevatorid))
			return false;
		if (epsg == null) {
			if (other.epsg != null)
				return false;
		} else if (!epsg.equals(other.epsg))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Geometrie [id=" + id + ", elevatorid=" + elevatorid + ", type=" + type + ", lat=" + lat + ", lng=" + lng
				+ ", epsg=" + epsg + "]";
	}

}
