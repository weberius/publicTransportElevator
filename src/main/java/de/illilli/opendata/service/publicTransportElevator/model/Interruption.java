package de.illilli.opendata.service.publicTransportElevator.model;

import java.util.Date;

public class Interruption {

	private int id;
	private String elevatorid;
	private Date start;
	private Date stop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getElevatorid() {
		return elevatorid;
	}

	public void setElevatorid(String elevatorid) {
		this.elevatorid = elevatorid;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elevatorid == null) ? 0 : elevatorid.hashCode());
		result = prime * result + id;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((stop == null) ? 0 : stop.hashCode());
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
		Interruption other = (Interruption) obj;
		if (elevatorid == null) {
			if (other.elevatorid != null)
				return false;
		} else if (!elevatorid.equals(other.elevatorid))
			return false;
		if (id != other.id)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (stop == null) {
			if (other.stop != null)
				return false;
		} else if (!stop.equals(other.stop))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interruption [id=" + id + ", elevatorid=" + elevatorid + ", start=" + start + ", stop=" + stop + "]";
	}

}
