package statement;
import java.sql.*;

/*
 * Example for query the SQLServer database via preparedStatement
 * sqljdbc4.jar is loaded
 * sqljdbc_auth.dll is copied to project folder
 */

public class StmtMain {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		PreparedStatement query = null;
		ResultSet result = null;
		String sql = "SELECT ContactName, Country FROM Customers WHERE City = ? AND Country = ?";
		
		try {
			query = connection.prepareStatement(sql);
			// Insert Parameters into a PreparedStatement
			query.setString(1, "Kirkland");
			query.setString(2, "USA");
	
			result = query.executeQuery();
			while (result.next()){
				System.out.println(result.getString(1) + " " + result.getString(2));
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
