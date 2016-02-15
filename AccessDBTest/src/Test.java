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
			con = DriverManager.getConnection("jdbc:ucanaccess://" + System.getProperty("user.dir") + "\\src\\Recipe_test.accdb");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Recipe");
			
			while(rs.next())
			{
				System.out.println(rs.getString("rec_ID") + " " + rs.getString("rec_name") + " " + rs.getString("difficulty"));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
