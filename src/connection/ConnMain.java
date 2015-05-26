package connection;
import java.sql.*;
/*
 * Example for connection to SQLServer via JDBC Driver Manager
 * sqljdbc4.jar is loaded
 * sqljdbc_auth.dll is copied to project folder
 */
public class ConnMain {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1. [Optional for java 6 or later] Loading the JDBC driver
		// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// 2. Open the connection
		String url = "jdbc:sqlserver://localhost:1433";
		String user = "sa";
		String password = "password";
		
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; integratedSecurity = true; DatabaseName = northwind");
		// Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement query = null;
		ResultSet result = null;
		try {
			query = connection.prepareStatement("SELECT GETDATE() DATETIME");
			result = query.executeQuery();

			while (result.next()) {
				System.out.println("Database Date/Time return: " + result.getString("DATETIME"));
			}
			result.close();
			query.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) result.close();
			if (query != null) query.close();
			if (connection != null) connection.close();
		}
	}
}
