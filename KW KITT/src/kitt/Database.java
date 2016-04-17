package kitt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	public static Connection con;
	public static Statement st;
	
	public Database()
	{
		try 
		{
			con = DriverManager.getConnection( "jdbc:ucanaccess://" + System.getProperty("user.dir") + "\\KITT Recipes.accdb" );
			st = con.createStatement();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
