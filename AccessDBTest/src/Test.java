import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test 
{
	public static void main(String args[]) 
	{
		loadDb();
	}
	
	public static void loadDb()
	{    
		Connection con;
		try 
		{
			con = DriverManager.getConnection( "jdbc:ucanaccess://" + System.getProperty("user.dir") + "\\src\\Recipe_test.accdb" );
			Statement st = con.createStatement();
			int id = getLastID( st );
			
			try
			{
				st.execute( "CREATE TABLE Rec2( rec_ID COUNTER PRIMARY KEY, rec_name text(30), difficulty text(15) )" );
			} catch (SQLException e) 
			{
				System.out.println("rec2 already exist");
			}
			
			st.executeUpdate( "INSERT INTO Rec2 " +
					" VALUES( " + id + ", 'fish" + id + "', 'moderate" + id + "' )" );//appends the id number to each column
			
			ResultSet rs = st.executeQuery( "SELECT * FROM Rec2" );
			
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
	}
	
	public static int getLastID( Statement st )
	{
		int id = -1;
		
		try 
		{
			ResultSet rs = st.executeQuery( "SELECT rec_ID FROM Rec2" );
			
			while(rs.next())
			{
				if( rs.isLast() )
				{
					id = rs.getInt( "rec_ID" );
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return id + 1;
	}
}
