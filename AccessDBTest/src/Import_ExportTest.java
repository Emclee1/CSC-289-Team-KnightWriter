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
	static File csv;
	
	public static void main(String args[]) throws SQLException, IOException
	{
		Scanner in = new Scanner(System.in);
		boolean close = false;
		csv = new File( System.getProperty("user.dir") + "\\src\\data.csv" );
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
					exportData();
					break;
				case 3:
					importData();
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
		return id;
	}
	
	public static void addTestRec() throws SQLException
	{
		int id = getLastID( ) + 1;
		
		st.executeUpdate( String.format( "INSERT INTO Recipe "
				+ "VALUES( %d, 'fish%d', 'moderate%d' , 11, 11, 11, 11, null, 'Entree', 'Italian', 'Test Recipe', 'user', null)", id, id, id ) );
		
		id = getLastID( );
		
		st.executeUpdate( String.format( "INSERT INTO Ing_Line "
				+ "VALUES( %d, 1059, 6, 'unit' )", id ) );
		st.executeUpdate( String.format( "INSERT INTO Ing_Line "
				+ "VALUES( %d, 1064, .5, 'cup' )", id ) );
		st.executeUpdate( String.format( "INSERT INTO Ing_Line "
				+ "VALUES( %d, 1065, 2.5, 'lb' )", id ) );
		
		System.out.println("\nTest Recipe & Ingredients Added\n");
	}
	
	public static void importData() throws IOException, SQLException
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
		
		boolean firstln = true;
		boolean recs = true;
		List<String> columnsRec = new ArrayList<String>();
		List<String> columnsIng = new ArrayList<String>();
		ArrayList<String[]> rowsRec = new ArrayList<String[]>();
		ArrayList<String[]> rowsIng = new ArrayList<String[]>();
		
		for( String line = reader.readLine(); line != null; line = reader.readLine() )
		{
			if( firstln )
			{
				if( recs )
				{
					columnsRec = Arrays.asList( line.split( ";" ) );
				}
				else
				{
					columnsIng = Arrays.asList( line.split( ";" ) );
				}
				firstln = false;
			}
			else if( line.equals( "Ing_Line" ) )
			{
				recs = false;
				firstln = true;
			}
			else if( recs )
			{
				rowsRec.add( line.split( ";" ) );
			}
			
			else
			{
				rowsIng.add( line.split( ";" ) );
			}
			//System.out.println("Line is: " + line);
		}
		
		//System.out.println( "Columns: " + columnsRec.size() + "\nRows: " + rowsRec.size() );
		
		List<Integer> ids = importRec( columnsRec, rowsRec, rowsIng );
		importIng( ids, columnsIng, rowsIng );
		
		reader.close();
	}
	
	private static List<Integer> importRec( List<String> columns, ArrayList<String[]> rows, ArrayList<String[]> rowsIng ) throws SQLException
	{
		String impRecs = "";
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for( int i = 0; i < rows.size(); i++ )
		{
			int id = getLastID() + 1;
			for( int k = 0; k < rowsIng.size(); k++ )
			{
				if( rows.get(i)[0].equals( rowsIng.get(k)[0] ) )
				{
					ids.add( id );
				}
			}
			String row = "INSERT INTO Recipe  VALUES( " + id + ",";
			impRecs += id + " "; 
					
			for( int j = 1; j < columns.size(); j++ )
			{
				try
				{
					int numb = Integer.parseInt( rows.get(i)[j] );
					row += numb + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
				}
				catch( NumberFormatException e)
				{
					if( rows.get(i)[j].matches("null") )
					{
						row +=  rows.get(i)[j] + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
					}
					else
					{
						row += "'" + rows.get(i)[j] + ( (  j == columns.size() - 1 )  ? "')" : "'," ) ;
					}
				}
				impRecs += rows.get(i)[j] + " ";
			}
			impRecs += "\n";
			st.executeUpdate( row );
		}
		
		System.out.println("\nImported Recipes:");
		System.out.println( impRecs );
		
		return ids;
	}
	
	private static void importIng( List<Integer> ids, List<String> columns, ArrayList<String[]> rows )
	{
		String impIngs = "";
		
		for( int i = 0; i < rows.size(); i++ )
		{
			int id = ids.get( i );
			impIngs += id + " ";
			String row = "INSERT INTO Ing_Line VALUES( " + id + ",";
			
			for( int j = 1; j < columns.size(); j++ )
			{
				try
				{
					int numb = Integer.parseInt( rows.get(i)[j] );
					row += numb + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
				}
				catch( NumberFormatException e)
				{
					row += "'" + rows.get(i)[j] + ( (  j == columns.size() - 1 )  ? "')" : "'," ) ;
				}
				impIngs += rows.get(i)[j] + " ";
			}
				impIngs += "\n";
				try
				{
					st.executeUpdate( row );
				}
				catch( SQLException e )
				{}
		}
		
		System.out.println("\nImported Ing_List:");
		System.out.println( impIngs + "\n");
	}

	public static void exportData() throws SQLException, FileNotFoundException
	{
		ResultSet recipes = st.executeQuery( "SELECT * FROM Recipe"
				+ " WHERE contributor IN ('user', null)" );
		
		if( !recipes.isBeforeFirst() )
		{
			System.out.println( "\nNo data to export\n" );
			return;
		}
		
		PrintWriter writer = new PrintWriter(csv);
		exportRec( recipes, writer );
		exportIng( writer );
		
		writer.close();
	}
	
	public static void exportRec( ResultSet recipes, PrintWriter writer ) throws SQLException
	{
		ResultSetMetaData meta = recipes.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + ";";
		}
		
		writer.println( columns );
		System.out.println( "\nExported Recipes:" );
		while( recipes.next() )
		{
			String row = "";
			for( int i = 1; i <= numCol; i++ )
			{
				row += recipes.getString(i) + ";";
			}
			writer.println( row );
			
			for( int i = 1; i <= numCol; i++ )
			{
				System.out.print(recipes.getString( i )  + " " );
			}
			System.out.println();
		}
	}
	
	public static void exportIng( PrintWriter writer ) throws SQLException
	{
		ResultSet recId = st.executeQuery( "SELECT rec_ID FROM Recipe"
				+ " WHERE contributor IN ('user', null)" );
		
		String statement = "SELECT * FROM Ing_Line WHERE rec_ID IN (" ;
		
		while( recId.next() )
		{
			if( recId.isLast() )
			{
				statement += recId.getString(1) + ")";
			}
			else
			{
				statement += recId.getString(1) + ", ";
			}
		}
		
		ResultSet ingList = st.executeQuery( statement );
		
		ResultSetMetaData meta = ingList.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + ";";
		}
		
		writer.println("Ing_Line");
		writer.println( columns );
		
		System.out.println( "\nExported Ing_List:" );
		while( ingList.next() )
		{
			String row = "";
			for( int i = 1; i <= numCol; i++ )
			{
				row += ingList.getString(i) + ";";
			}
			writer.println( row );
			
			for( int i = 1; i <= numCol; i++ )
			{
				System.out.print( ingList.getString( i )  + " " );
			}
			System.out.println();
		}
		System.out.println();
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
