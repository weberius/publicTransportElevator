package de.illilli.opendata.service.publicTransportElevator.jdbc;

public class StoringResult {

	private int inserted = 0;
	private int updated = 0;
	private int skipped = 0;

	public int getInserted() {
		return inserted;
	}

	public void addInserted(int inserted) {
		this.inserted += inserted;
	}

	public int getUpdated() {
		return updated;
	}

	public void addUpdated(int updated) {
		this.updated += updated;
	}

	public int getSkipped() {
		return skipped;
	}

	public void addSkipped(int skipped) {
		this.skipped += skipped;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inserted;
		result = prime * result + skipped;
		result = prime * result + updated;
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
		StoringResult other = (StoringResult) obj;
		if (inserted != other.inserted)
			return false;
		if (skipped != other.skipped)
			return false;
		if (updated != other.updated)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoringResult [inserted=" + inserted + ", updated=" + updated + ", skipped=" + skipped + "]";
	}

}
