package de.illilli.opendata.service.publicTransportElevator.jdbc;

import com.vividsolutions.jts.geom.Point;

public class ElevatorDTO {

	private String bezeichnung;
	private String id;
	private int haltestellenbereich;
	private String info;
	private Point geom;

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHaltestellenbereich() {
		return haltestellenbereich;
	}

	public void setHaltestellenbereich(int haltestellenbereich) {
		this.haltestellenbereich = haltestellenbereich;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Point getGeom() {
		return geom;
	}

	public void setGeom(Point geom) {
		this.geom = geom;
	}

	@Override
	public String toString() {
		return "ElevatorDTO [bezeichnung=" + bezeichnung + ", id=" + id + ", haltestellenbereich=" + haltestellenbereich
				+ ", info=" + info + ", geom=" + geom + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		result = prime * result + haltestellenbereich;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
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
		ElevatorDTO other = (ElevatorDTO) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (haltestellenbereich != other.haltestellenbereich)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

}
