package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.io.IOUtils;
import org.h2gis.functions.io.geojson.GeoJsonRead;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.InsertDao;
import de.illilli.jdbc.SelectDao;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.converter.Dto2Elevator;
import de.illilli.opendata.service.publicTransportElevator.converter.Elevator2Insert;
import de.illilli.opendata.service.publicTransportElevator.converter.Url2ElevatorList;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ElevatorDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InsertElevator;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectElevator;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectVersion;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ShowTables;
import de.illilli.opendata.service.publicTransportElevator.jdbc.TableNamesDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.VersionDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

public class Main {

	private Connection connection;

	@Before
	public void setupDB() throws Exception {

		Class.forName("org.h2.Driver");
		// connection = DriverManager.getConnection("jdbc:h2:mem:syntax", "sa", "sa");
		this.connection = ConnectionFactory.getConnection();
		// initialize Database
		Statement st = connection.createStatement();

		String sqlFileName = "/sql/init.sql";
		InputStream inputStream = Main.class.getResourceAsStream(sqlFileName);
		String sql = IOUtils.toString(inputStream);
		st.execute(sql);

		// check for tableNames
		List<TableNamesDTO> tableNames = new SelectListDao<TableNamesDTO>(new ShowTables(), connection).execute();
		for (TableNamesDTO tableName : tableNames) {
			System.out.println(tableName);
		}
		// check for version
		VersionDTO version = new SelectDao<VersionDTO>(new SelectVersion(), connection).execute();
		System.out.println(version);

	}

	@After
	public void closeBD() {
		DbUtils.closeQuietly(connection);
	}

	@Test
	@Ignore
	public void testWithDBUtils() throws IOException, SQLException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Converter<List<Elevator>, URL> converter = new Url2ElevatorList();
		List<Elevator> elevatorList = converter.getAsObject(url);

		for (Elevator elevator : elevatorList) {
			new InsertDao(new InsertElevator(elevator), connection).execute();
		}

	}

	@Test
	@Ignore
	public void testReadFile() throws IOException, SQLException {
		String fileName = "/Users/wolfram/Workspaces/PublicDepartCologne/publicTransportElevator/src/test/resources/elevators.geojson";
		String tableReference = "elevator";
		GeoJsonRead.readGeoJson(this.connection, fileName, tableReference);
	}

	@Test
	public void testWithStatementExecute() throws SQLException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Converter<List<Elevator>, URL> converter = new Url2ElevatorList();
		List<Elevator> elevatorList = converter.getAsObject(url);

		Statement st = connection.createStatement();

		for (Elevator elevator : elevatorList) {
			st.execute(new Elevator2Insert().getAsObject(elevator));
		}

		ResultSet rs = st.executeQuery("SELECT bezeichnung from elevator");
		while (rs.next()) {
			System.out.println("bezeichnung = '" + rs.getString("bezeichnung") + "'");
		}
	}

	@Test
	public void testReadWithDBUtils() throws SQLException, IOException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
		Converter<List<Elevator>, URL> converter = new Url2ElevatorList();
		List<Elevator> elevatorList = converter.getAsObject(url);

		Statement st = connection.createStatement();
		// load values to database
		for (Elevator elevator : elevatorList) {
			st.execute(new Elevator2Insert().getAsObject(elevator));
		}
		// read from database
		List<ElevatorDTO> dtoList = new SelectListDao<ElevatorDTO>(new SelectElevator(), connection).execute();
		for (ElevatorDTO dto : dtoList) {
			Elevator elevator = new Dto2Elevator().getAsObject(dto);
			System.out.println(elevator);
		}

	}
}
