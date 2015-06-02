package metadata;

import java.sql.*;

/*
 * Example for obtaining a all columns in a resultSet using ResultSetMetaData
 */
public class ResultSetMetadata {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		PreparedStatement query = null;

		try {
			query = conn.prepareStatement("SELECT * FROM [ORDER DETAILS]");
			ResultSet rs = query.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();            
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i < numColumns + 1; i++) {
            	System.out.println(rsmd.getColumnName(i));
            }
          
            rs.close();
            query.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
