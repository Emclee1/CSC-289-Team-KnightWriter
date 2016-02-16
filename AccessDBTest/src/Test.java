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
			st.executeUpdate( "INSERT INTO Recipe " +
					" VALUES( " + id + ", 'FishFishFishFishFishFishFishFi', 'moderatemoderat' ) " ); //Problem with length

			ResultSet rs = st.executeQuery( "SELECT * FROM Recipe" );
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
			ResultSet rs = st.executeQuery( "SELECT rec_ID FROM Recipe" );
			
			while(rs.next())
			{
				if( rs.isLast() )
				{
					id = rs.getInt( "rec_ID" );
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id + 1;
	}
}
