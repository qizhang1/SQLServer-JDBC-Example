package metadata;

import java.sql.*;

/*
 * Example for obtaining a all table names using DatabaseMetaData
 * sqljdbc4.jar is loaded
 * sqljdbc_auth.dll is copied to project folder 
 */

public class Metadata {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		DatabaseMetaData dm = connection.getMetaData();
		
		String   catalog          = null;
		String   schemaPattern    = null;
		String   tableNamePattern = null;
		String[] types            = {"TABLE"};

		ResultSet result = null;
		try {
			result = dm.getTables(catalog, schemaPattern, tableNamePattern, types );	
			while(result.next()) {
			    String tableName = result.getString(3);
			    System.out.println(tableName);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) result.close();
			if (connection != null) connection.close();
		}	
	}

}
