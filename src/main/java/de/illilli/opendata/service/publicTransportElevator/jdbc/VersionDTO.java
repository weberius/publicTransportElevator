package de.illilli.opendata.service.publicTransportElevator.jdbc;

public class VersionDTO {

	private String h2version;
	private String h2gisversion;

	public String getH2version() {
		return h2version;
	}

	public void setH2version(String h2version) {
		this.h2version = h2version;
	}

	public String getH2gisversion() {
		return h2gisversion;
	}

	public void setH2gisversion(String h2gisversion) {
		this.h2gisversion = h2gisversion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((h2gisversion == null) ? 0 : h2gisversion.hashCode());
		result = prime * result + ((h2version == null) ? 0 : h2version.hashCode());
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
		VersionDTO other = (VersionDTO) obj;
		if (h2gisversion == null) {
			if (other.h2gisversion != null)
				return false;
		} else if (!h2gisversion.equals(other.h2gisversion))
			return false;
		if (h2version == null) {
			if (other.h2version != null)
				return false;
		} else if (!h2version.equals(other.h2version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VersionDTO [h2version=" + h2version + ", h2gisversion=" + h2gisversion + "]";
	}

}
