package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.SelectDao;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.converter.Elevator2Insert;
import de.illilli.opendata.service.publicTransportElevator.converter.Url2ElevatorList;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectVersion;
import de.illilli.opendata.service.publicTransportElevator.jdbc.ShowTables;
import de.illilli.opendata.service.publicTransportElevator.jdbc.TableNamesDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.VersionDTO;
import de.illilli.opendata.service.publicTransportElevator.model.Elevator;

@WebListener
public class Initialize implements ServletContextListener {

	private Connection connection;

	@Override
	public void contextInitialized(ServletContextEvent event) {

		// Do your thing during webapp's startup.

		System.out.println("#### initialize ####");

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.connection = ConnectionFactory.getConnection();
			Statement st = connection.createStatement();

			String sqlFileName = "/sql/init.sql";
			InputStream inputStream = Initialize.class.getResourceAsStream(sqlFileName);
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

			// initialize Database
			URL url = this.getClass().getClassLoader().getResource("fahrtreppen.json");
			Converter<List<Elevator>, URL> converter = new Url2ElevatorList();
			List<Elevator> elevatorList = converter.getAsObject(url);

			// load values to database
			for (Elevator elevator : elevatorList) {
				st.execute(new Elevator2Insert().getAsObject(elevator));
			}

		} catch (ClassNotFoundException | SQLException | NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("#### up and running ####");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do your thing during webapp's shutdown.
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
