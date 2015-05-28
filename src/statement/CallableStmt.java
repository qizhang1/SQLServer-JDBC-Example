package statement;

import java.sql.*;

/*
 * Example for call stored procedure in SQLServer database via callableStatement
 * sqljdbc4.jar is loaded
 * sqljdbc_auth.dll is copied to project folder 
 */
public class CallableStmt {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; username=sa; password=password; DatabaseName = northwind");
		ResultSet result = null;
		CallableStatement cstmt = null;
		try{
			// a stored procedure 
			cstmt = connection.prepareCall("{call [Customers By City](?)}");
			cstmt.setString(1, "Kirkland");
			result = cstmt.executeQuery();
			while (result.next()){
				System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " " + result.getString(4));
			}
			result.close();
			cstmt.close();
			
			// a stored procedure return OUT parameters.
			cstmt = connection.prepareCall("{call CustOrderTotal(?, ?)}");
			cstmt.setString(1, "BOLID");			
			cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);			
			cstmt.execute(); // use execute() method if no resultSet is returned
			System.out.println("BOLID Order Total: " + cstmt.getDouble(2));
			cstmt.close();			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) result.close();
			if (cstmt != null) cstmt.close();
			if (connection != null) connection.close();
		}	
	}

}
