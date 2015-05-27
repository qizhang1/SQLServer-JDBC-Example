package statement;

import java.sql.*;

public class Transaction {

	/*
	 * Example for making atomic transaction
	 */
	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		try{
		    connection.setAutoCommit(false);
		    Statement statement1 = null;
		    try{
		        statement1 = connection.createStatement();
		        statement1.executeUpdate( 
		        	"UPDATE Customers SET CompanyName = 'Company 1' where CustomerID = '00001'");
		    } finally {
		        if(statement1 != null) {
		            statement1.close();
		        }
		    }

		    Statement statement2 = null;
		    try{
		        statement2 = connection.createStatement();
		        statement2.executeUpdate(
		            "UPDATE Customers SET CompanyName = 'Company 2' where CustomerID = '00002'");
		    } finally {
		        if(statement2 != null) {
		            statement2.close();
		        }
		    }
		    connection.commit(); // commit at the end of transaction
		} catch(Exception e) {
		    connection.rollback();
		} finally {
		    if(connection != null) {
		        connection.close();
		    }
		}
	}

}
