import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Import_ExportTest 
{
	static Connection con;
	static Statement st;
	
	public static void main(String args[]) throws SQLException, IOException
	{
		Scanner in = new Scanner(System.in);
		boolean close = false;
		File csv = new File( System.getProperty("user.dir") + "\\src\\data.csv" );
		connectToDB();
		
		while (!close)
		{
			System.out.println( "Enter 1 to add new test row\nEnter 2 to export data\nEnter 3 to import data\nEnter 4 to display the Recipe table\nEnter anything else to exit: " );
			switch(in.nextInt())
			{
				case 1:
					addTestRec();
					break;
				case 2:
					exportRecs( csv );
					break;
				case 3:
					importRecs( csv );
					break;
				case 4:
					displayRecs();
					break;
				default:
					close = true;
					break;
			}
		}
		
		in.close();
		con.commit();
		con.close();
	}
	
	public static void connectToDB()
	{
		try 
		{
			con = DriverManager.getConnection( "jdbc:ucanaccess://" + System.getProperty("user.dir") + "\\src\\KITT 20R.accdb" );
			st = con.createStatement();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static int getLastID()
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
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return id + 1;
	}
	
	public static void addTestRec() throws SQLException
	{
		int id = getLastID( );
		System.out.println( "Last id + 1 = " + id );
		
		st.executeUpdate( "INSERT INTO Recipe " +
			" VALUES( " + id + ", 'fish" + id + "', 'moderate" + id + "' , 11, 11, 11, 11, null, 'Entree', 'Italian', 'Test Rec', 'user', null)" );
	}
	
	public static void importRecs( File csv ) throws IOException, SQLException
	{
		BufferedReader reader;
		
		try 
		{
			reader = new BufferedReader( new FileReader( csv ) );
		} 
		catch (FileNotFoundException e)
		{
			System.out.println( "No data file exists" );
			return;
		}
		
		ResultSet rSet = st.executeQuery( "SELECT source FROM Recipe WHERE source IS NULL" );
		rSet.next();
		
		boolean firstln = true;
		List<String> columns = new ArrayList<String>();
		ArrayList<String[]> rows = new ArrayList<String[]>();
		for( String line = reader.readLine(); line != null; line = reader.readLine() )
		{
			if( firstln )
			{
				columns = Arrays.asList( line.split( ";" ) );
				firstln = false;
			}
			else
			{
				rows.add( line.split( ";" ) );
			}
		}
		
		for( int i = 0; i < rows.size(); i++ )
		{
			String row = "INSERT INTO Recipe  VALUES( " + getLastID() + ",";
			for( int j = 1; j < columns.size(); j++ )
			{
				if( j == 1 || j == 2 || j == 8 || j == 9 || j == 10 || j == 11)
				{
					row += "'" + rows.get(i)[j] + ( (  j == columns.size() - 1 )  ? "')" : "'," ) ;
				}
				else
				{
					try
					{
						int numb = Integer.parseInt( rows.get(i)[j] );
						row += numb + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
					}
					catch( NumberFormatException e)
					{
						row +=  rows.get(i)[j] + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
					}
				}
			}
			System.out.println( "\nColumns Size: " + columns.size() + "\nRow : " + row );
			st.executeUpdate( row );
		}
		
		ResultSet rs = st.executeQuery( "SELECT * FROM Recipe"
				+ " WHERE contributor IN ('user', null)" );
		
		System.out.println("\nImported Recipes:");
		while(rs.next())
		{
			for( int i = 1; i <= columns.size(); i++ )
			{
				System.out.print(rs.getString( i )  + " " );
			}
			
			System.out.println();
		}
		System.out.println();
		
		reader.close();
	}
	
	public static void exportRecs( File csv ) throws IOException, SQLException
	{
		ResultSet rs = st.executeQuery( "SELECT * FROM Recipe"
				+ " WHERE contributor IN ('user', null)" );
		
		if( !rs.isBeforeFirst() )
		{
			System.out.println( "\nNo data to export\n" );
			return;
		}
		
		PrintWriter writer = new PrintWriter(csv);
		ResultSetMetaData meta = rs.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + ";";
		}
		
		writer.println( columns );
		System.out.println( "\nExported Recipes:" );
		while( rs.next() )
		{
			String row = "";
			for( int i = 1; i <= numCol; i++ )
			{
				row += rs.getString(i) + ";";
			}
			writer.println( row );
			
			for( int i = 1; i <= numCol; i++ )
			{
				System.out.print(rs.getString( i )  + " " );
			}
			System.out.println();
		}
		System.out.println();
		writer.close();
	}
	
	public static void displayRecs() throws SQLException
	{
		displayRecs( "SELECT * FROM Recipe" );
	}
	
	public static void displayRecs( String statement ) throws SQLException
	{
		ResultSet rs = st.executeQuery( statement );
		
		System.out.println("\nRecipe List:");
		while(rs.next())
		{
			for( int i = 1; i < 10; i++ )
			{
				System.out.print(rs.getString( i )  + " " );
			}
			
			System.out.println();
		}
		System.out.println();
	}
}
