import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestOracleDb 
{
	public static void main(String args[]) 
	{
		loadDb();
	}
	
	public static void loadDb()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection( "jdbc:oracle:thin:@vaaaldez.us.to:1521:xe", "knightwriter", "knightwriter" );
			Statement st = con.createStatement();
			
			try
			{
				st.execute( "CREATE TABLE Recipe_test( rec_ID integer, rec_name varchar(30), difficulty varchar(15), PRIMARY KEY (rec_ID) )" );
			} catch (SQLException e)
			{
				//System.out.println("Recipe_test already exist");
			}
			
			int id = getLastID( st );
			
			st.executeUpdate( "INSERT INTO Recipe_test " +
					" VALUES( " + id + ", 'fish" + id + "', 'moderate" + id + "' )" );
			
			ResultSet rs = st.executeQuery( "SELECT * FROM Recipe_test" );
			
			while(rs.next())
			{
				System.out.println(rs.getString( "rec_ID" ) + " " + rs.getString( "rec_name" ) + " " + rs.getString( "difficulty" ) );
			}
			
			con.commit();
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public static int getLastID( Statement st )
	{
		int id = -1;
		
		try 
		{
			ResultSet rs = st.executeQuery( "SELECT rec_ID FROM Recipe_test ORDER BY rec_ID DESC" );
			if (rs != null)
			{
				rs.next();
				id = rs.getInt( "rec_ID" );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id + 1;
	}
	
}