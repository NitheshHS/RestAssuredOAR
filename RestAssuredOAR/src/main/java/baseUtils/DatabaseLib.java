package baseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * 
 * @author Nitheesha
 *
 */
public class DatabaseLib {
	static Connection conn=null;
	static Statement stmt=null;
	/**
	 * Used to register the driver and create a connection with the database
	 * @throws Throwable
	 */
	public static void getConnection() throws Throwable {
		Driver regdriver=new Driver();
		DriverManager.registerDriver(regdriver);
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	}
	/**
	 * Closes the connection with Database
	 * @throws Throwable
	 */
	public static void clodeDBConnection() throws Throwable {
		conn.close();
	}
	/**
	 * To get the data from database 
	 * @param query
	 * @param columnIndex
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public static String executeQuery(String query, int columnIndex,String expectedData) throws Throwable {
		stmt=conn.createStatement();
		ResultSet result=stmt.executeQuery(query);
		boolean flag=false;
		while(result.next()) {
			if(result.getString(columnIndex).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if(flag==true) {
			return expectedData;
		}
		else
		{
			return "Project is not created";
		}
	}

}
