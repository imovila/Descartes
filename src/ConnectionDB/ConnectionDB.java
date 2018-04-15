package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static String username = "";
	private static String password = "";
	private static String service = "localhost";
	private static String url = "jdbc:oracle:thin:";
	private static Connection conn;
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String value) {
		username = value;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String value) {
		url = value;
	}
	
	public static String getPassword() {
		return password;
	}

	public static void setPassword(String value) {
		password = value;
	}

	public static String getService() {
		return service;
	}

	public static void setService(String value) {
		service = value;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection connection) {
		conn = connection;
	}
	
	public static boolean SetDBConnection() throws SQLException {
		try{
			setConn(DriverManager.getConnection(getUrl()+getUsername()+"/"+getPassword()+"@"+getService()));
			return true;
		} 
		catch (SQLException ex){
			System.out.println("\n\t\tConnection error:\n\t\t" + ex.getMessage());
			setConn(null);	
			return false;
		}
	}

	public static void logOut() throws SQLException{
		getConn().close();
	}

}