package tracker.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

	private static final String PATH = "./res/";
	private static final String DB_NAME = "todo";
	private static DBController instance = new DBController();

	private Connection conn;

	private DBController() {

	}

	/**
	 * Oeffnen der Datenbankverbindung
	 * 
	 * @return Verbindung zur Datenbank
	 */
	public Connection openConnection() {
		try {
			if (this.conn == null || this.conn.isClosed()) {
				System.out.println("Establishing connection to database");
				this.conn = DriverManager.getConnection("jdbc:h2:" + PATH + DB_NAME);
				if (!this.conn.isClosed()) {
					System.out.println("Connected to database");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.conn;
	}

	/**
	 * Schliessen der Datenbankverbindung
	 */
	public void closeConnenction() {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
				System.out.println("Connection to database closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBController getInstance() {
		return instance;
	}

}
