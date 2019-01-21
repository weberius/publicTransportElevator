package de.illilli.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {

		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:syntax", "sa", "sa");
		return conn;

	}
}
