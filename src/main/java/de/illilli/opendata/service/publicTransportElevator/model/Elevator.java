package de.illilli.opendata.service.publicTransportElevator.model;

public class Elevator {

	private String bezeichnung;
	private String id;
	private String haltestellenbereich;
	private String info;
	private Geometrie geometrie;

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

	public String getHaltestellenbereich() {
		return haltestellenbereich;
	}

	public void setHaltestellenbereich(String haltestellenbereich) {
		this.haltestellenbereich = haltestellenbereich;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Geometrie getGeometrie() {
		return geometrie;
	}

	public void setGeometrie(Geometrie geometrie) {
		this.geometrie = geometrie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + ((haltestellenbereich == null) ? 0 : haltestellenbereich.hashCode());
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
		Elevator other = (Elevator) obj;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} else if (!bezeichnung.equals(other.bezeichnung))
			return false;
		if (haltestellenbereich == null) {
			if (other.haltestellenbereich != null)
				return false;
		} else if (!haltestellenbereich.equals(other.haltestellenbereich))
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

	@Override
	public String toString() {
		return "Elevator [bezeichnung=" + bezeichnung + ", id=" + id + ", haltestellenbereich=" + haltestellenbereich
				+ ", info=" + info + "]";
	}

}
