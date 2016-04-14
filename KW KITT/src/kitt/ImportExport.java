package kitt;

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

public class ImportExport 
{
	static Connection con;
	static Statement st;
	static File csv;
	
	public ImportExport()
	{
		csv = new File( System.getProperty("user.dir") + "\\src\\data.csv" );
		connectToDB();
	}
	
	public String getExportList()
	{
		String list = "";
		ResultSet recipes;
		
		try 
		{
			recipes = st.executeQuery( "SELECT * FROM Recipe"
					+ " WHERE contributor IN ('user', null)" );
			
			if( !recipes.isBeforeFirst() )
			{
				return "No data to export";
			}
			
			while( recipes.next() )
			{
				//list += "\u2022" + recipes.getString(2) + "\r\n";
				list += recipes.getString(2) + "\r\n";
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String getImportList()
	{
		String list = "";
		BufferedReader reader;
		boolean firstln = true;
		
		try 
		{
			reader = new BufferedReader( new FileReader( csv ) );
			for( String line = reader.readLine(); line != null; line = reader.readLine() )
			{
				if( firstln )
				{
					firstln = false;
				}
				else if( line.contains( "Ing_Line" ) )
				{
					break;
				}
				else
				{
					list += line.split( "\\|" )[1] + "\n" ;
				}
			}
			
			reader.close();
		} 
		catch (FileNotFoundException e)
		{
			return "No data file exists";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void connectToDB()
	{
		try 
		{
			con = DriverManager.getConnection( "jdbc:ucanaccess://" + System.getProperty("user.dir") + "\\KITT 20R.accdb" );
			st = con.createStatement();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void closeDB()
	{
		try {
			con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void importData( ArrayList<Boolean> selected )
	{
		BufferedReader reader;
		
		try 
		{
			reader = new BufferedReader( new FileReader( csv ) );
		} 
		catch (FileNotFoundException e)
		{
			return;
		}
		
		boolean firstln = true;
		boolean recs = true;
		List<String> columnsRec = new ArrayList<String>();
		List<String> columnsIng = new ArrayList<String>();
		ArrayList<String[]> rowsRec = new ArrayList<String[]>();
		ArrayList<String[]> rowsIng = new ArrayList<String[]>();
		List<Integer> ids;
		
		try 
		{
			for( String line = reader.readLine(); line != null; line = reader.readLine() )
			{
				if( firstln )
				{
					if( recs )
					{
						columnsRec = Arrays.asList( line.split( "\\|" ) );
					}
					else
					{
						columnsIng = Arrays.asList( line.split( "\\|" ) );
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
					rowsRec.add( line.split( "\\|" ) );
				}
				
				else
				{
					rowsIng.add( line.split( "\\|" ) );
				}
			}
			
			ids = importRec( columnsRec, rowsRec, rowsIng, selected );
			importIng( ids, columnsIng, rowsIng );
			
			reader.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<Integer> importRec( List<String> columns, ArrayList<String[]> rows, ArrayList<String[]> rowsIng, ArrayList<Boolean> selected ) throws SQLException
	{
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for( int i = 0; i < rows.size(); i++ )
		{
			int id = getLastID() + 1;
			for( int k = 0; k < rowsIng.size(); k++ )
			{
				if( rows.get(i)[0].equals( rowsIng.get(k)[0] ) )
				{
					if( selected.get( i ) )
					{
						ids.add( id );
					}
					else
					{
						ids.add( -1 );
					}
				}
			}
			
			if( selected.get( i ) )
			{
				String row = "INSERT INTO Recipe  VALUES( " + id + ",";
						
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
							row += "'" + rows.get(i)[j].replaceAll( "@", "' + CHAR(13)+CHAR(10) + '" ) + ( (  j == columns.size() - 1 )  ? "')" : "'," ) ;
						}
					}
				}
				st.executeUpdate( row );
			}
		}
		
		return ids;
	}
	
	private static void importIng( List<Integer> ids, List<String> columns, ArrayList<String[]> rows )
	{
		for( int i = 0; i < rows.size(); i++ )
		{
			if( ids.get( i ) != -1 )
			{
				int id = ids.get( i );
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
				}
				try
				{
					st.executeUpdate( row );
				}
				catch( SQLException e ){}
			}
		}
			
	}

	public static void exportData( ArrayList<Boolean> selected )
	{
		try
		{
			ResultSet recipes = st.executeQuery( "SELECT * FROM Recipe"
					+ " WHERE contributor IN ('user', null)" );
			
			if( !recipes.isBeforeFirst() || !selected.contains(true) )
			{
				return;
			}
			
			PrintWriter writer = new PrintWriter(csv);
			exportRec( recipes, writer, selected );
			exportIng( writer, selected );
			
			writer.close();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void exportRec( ResultSet recipes, PrintWriter writer, ArrayList<Boolean> selected ) throws SQLException
	{
		ResultSetMetaData meta = recipes.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + "|";
		}
		
		writer.println( columns );
		
		int j = 0;
		while( recipes.next() )
		{
			if( selected.get( j ) )
			{
				String row = "";
				for( int i = 1; i <= numCol; i++ )
				{
					row += recipes.getString(i) + "|";
				}
				writer.println( row.replaceAll( "\r\n\r\n<div>&nbsp;</div>\r\n\r\n", "@" ).replaceAll( "<div>", "" ).replaceAll( "</div>", "" ) );
			}
			j++;
		}
	}
	
	public static void exportIng( PrintWriter writer, ArrayList<Boolean> selected ) throws SQLException
	{
		ResultSet recId = st.executeQuery( "SELECT rec_ID FROM Recipe"
				+ " WHERE contributor IN ('user', null)" );
		
		String statement = "SELECT * FROM Ing_Line WHERE rec_ID IN (" ;
		
		int j = 0;
		while( recId.next() )
		{
			if( selected.get( j ) )
			{
				if( j == selected.lastIndexOf( true ) )
				{
					statement += recId.getString(1) + ")";
				}
				else
				{
					statement += recId.getString(1) + ", ";
				}
			}
			j++;
		}
		
		ResultSet ingList = st.executeQuery( statement );
		
		ResultSetMetaData meta = ingList.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + "|";
		}
		
		writer.println("Ing_Line");
		writer.println( columns );
		
		while( ingList.next() )
		{
			String row = "";
			for( int i = 1; i <= numCol; i++ )
			{
				row += ingList.getString(i) + "|";
			}
			writer.println( row );
			
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
