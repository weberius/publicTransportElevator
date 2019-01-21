package de.illilli.opendata.service.publicTransportElevator.jdbc;

public class TableNamesDTO {

	private String table_name;
	private String table_schema;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_schema() {
		return table_schema;
	}

	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((table_name == null) ? 0 : table_name.hashCode());
		result = prime * result + ((table_schema == null) ? 0 : table_schema.hashCode());
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
		TableNamesDTO other = (TableNamesDTO) obj;
		if (table_name == null) {
			if (other.table_name != null)
				return false;
		} else if (!table_name.equals(other.table_name))
			return false;
		if (table_schema == null) {
			if (other.table_schema != null)
				return false;
		} else if (!table_schema.equals(other.table_schema))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TableNamesDTO [table_name=" + table_name + ", table_schema=" + table_schema + "]";
	}

}
