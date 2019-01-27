package de.illilli.opendata.service.publicTransportElevator.jdbc;

import java.sql.Timestamp;

public class InterruptionDTO {

	private String elevatorid;
	private Timestamp time;
	private Timestamp start;
	private Timestamp stop;
	private int period;

	public String getElevatorid() {
		return elevatorid;
	}

	public void setElevatorid(String elevatorid) {
		this.elevatorid = elevatorid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getStop() {
		return stop;
	}

	public void setStop(Timestamp stop) {
		this.stop = stop;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elevatorid == null) ? 0 : elevatorid.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		InterruptionDTO other = (InterruptionDTO) obj;
		if (elevatorid == null) {
			if (other.elevatorid != null)
				return false;
		} else if (!elevatorid.equals(other.elevatorid))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterruptionDTO [elevatorid=" + elevatorid + ", time=" + time + ", start=" + start + ", stop=" + stop
				+ ", period=" + period + "]";
	}

}
