package statement;

import java.sql.*;


public class BatchUpdates {

	/*
	 * Example for batch updating the SQLServer database
	 * sqljdbc4.jar is loaded
	 * sqljdbc_auth.dll is copied to project folder
	 */
	
	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		PreparedStatement query = null;
		ResultSet result = null;
		String sql = "INSERT INTO Customers (CustomerID, CompanyName) VALUES (?, ?)";

		try{
		    query = connection.prepareStatement(sql);
		    
		    query.setString(1, "00001");
		    query.setString(2, "Company A");
		    query.addBatch();

		    query.setString(1, "00002");
		    query.setString(2, "Company B");
		    query.addBatch();

		    query.executeBatch();
		    query.close();
		    
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) result.close();
			if (query != null) query.close();
			if (connection != null) connection.close();
		}
		
		// delete from Customers where  CustomerID = '00001' OR CustomerId = '00002'
	}

}
