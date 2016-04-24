package kitt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportExport 
{
	static File csv;
	
	public ImportExport()
	{
		csv = new File( System.getProperty("user.dir") + "\\data.csv" );
	}
	
	public String getExportList()
	{
		String list = "";
		ResultSet recipes;
		
		try 
		{
			recipes = Database.st.executeQuery( "SELECT * FROM Recipe" );
			
			if( !recipes.isBeforeFirst() )
			{
				return "No data to export";
			}
			
			while( recipes.next() )
			{
				list += recipes.getString( 1 ) + "|" + recipes.getString( 2 ) + "\r\n";
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
					list += line.split( "\\|" )[0] + "|" + line.split( "\\|" )[1] + "\n" ;
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
	
	public static void importData( ArrayList<Integer> recIDs )
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
		
		List<String> columnsRec = new ArrayList<String>();
		List<String> columnsIng = new ArrayList<String>();
		ArrayList<String[]> rowsRec = new ArrayList<String[]>();
		ArrayList<String[]> rowsIng = new ArrayList<String[]>();
		List<Integer> newIDs;
		
		try 
		{
			boolean firstln = true;
			boolean recs = true;
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
			
			newIDs = importRec( columnsRec, rowsRec, rowsIng, recIDs );
			importIng( newIDs, columnsIng, rowsIng );
			
			reader.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<Integer> importRec( List<String> columns, ArrayList<String[]> rowsRec, ArrayList<String[]> rowsIng, ArrayList<Integer> recIDs ) throws SQLException
	{
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for( int i = 0; i < rowsRec.size(); i++ )
		{
			int id = getLastID() + 1;
			for( int k = 0; k < rowsIng.size(); k++ )
			{
				if( rowsRec.get(i)[0].equals( rowsIng.get(k)[0] ) )
				{
					if( recIDs.contains( Integer.parseInt( rowsRec.get(i)[0] ) ) )
					{
						ids.add( id );
					}
					else
					{
						ids.add( -1 );
					}
				}
			}
			
			if( recIDs.contains( Integer.parseInt( rowsRec.get(i)[0] ) ) )
			{
				String row = "INSERT INTO Recipe  VALUES( " + id + ",";
						
				for( int j = 1; j < columns.size(); j++ )
				{
					try
					{
						int numb = Integer.parseInt( rowsRec.get(i)[j] );
						row += numb + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
					}
					catch( NumberFormatException e)
					{
						if( rowsRec.get(i)[j].matches("null") )
						{
							row +=  rowsRec.get(i)[j] + ( (  j == columns.size() - 1 )  ? ")" : "," ) ;
						}
						else
						{
							row += "'" + rowsRec.get(i)[j].replaceAll( "@", "</div>\r\n\r\n<div>&nbsp;</div>\r\n\r\n<div>")
									.replaceAll( "#", "</div>\r\n\r\n<div>&nbsp;</div>")
									.replaceAll( "\\^", "&nbsp;" ).replaceAll( "\\*", "<div>" )
									.replaceAll( "~", "</div>" )
									+ ( (  j == columns.size() - 1 )  ? "')" : "'," ) ;
						}
					}
				}
				Database.st.executeUpdate( row );
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
					Database.st.executeUpdate( row );
				}
				catch( SQLException e ){}
			}
		}
			
	}

	public static void exportData( ArrayList<Integer> recIDs )
	{
		try
		{
			if( recIDs == null )
			{
				return;
			}
			
			String statement = "SELECT * FROM Recipe Where rec_ID IN (" ;
			for( int i = 0; i < recIDs.size(); i++ )
			{
				statement += recIDs.get(i) + ( ( i == recIDs.size() - 1 ) ? ")" : ", " );
			}
			
			ResultSet recipes = Database.st.executeQuery( statement );
			if( !recipes.isBeforeFirst() )
			{
				return;
			}
			
			PrintWriter writer = new PrintWriter(csv);
			exportRec( recipes, writer );
			exportIng( writer, recIDs );
			
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
	
	public static void exportRec( ResultSet recipes, PrintWriter writer ) throws SQLException
	{
		ResultSetMetaData meta = recipes.getMetaData();
		int numCol = meta.getColumnCount();
		
		String columns = "";
		for( int i = 1; i <= numCol; i ++ )
		{
			columns += meta.getColumnName(i) + "|";
		}
		
		writer.println( columns );
		
		while( recipes.next() )
		{
			String row = "";
			for( int i = 1; i <= numCol; i++ )
			{
				row += recipes.getString(i) + "|";
			}
			writer.println( row.replaceAll( "</div>\r\n\r\n<div>&nbsp;</div>\r\n\r\n<div>", "@").replaceAll( "</div>\r\n\r\n<div>&nbsp;</div>", "#").replaceAll( "&nbsp;", "^").replaceAll( "<div>", "*" ).replaceAll( "</div>", "~" ) );
		}
	}
	
	public static void exportIng( PrintWriter writer, ArrayList<Integer> recIDs ) throws SQLException
	{
		String statement = "SELECT * FROM Ing_Line WHERE rec_ID IN (" ;
		
		for( int i = 0; i < recIDs.size(); i++ )
		{
			statement += recIDs.get(i) + ( ( i == recIDs.size() - 1 ) ? ")" : ", " );
		}
		
		ResultSet ingList = Database.st.executeQuery( statement );
		
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
			ResultSet rs = Database.st.executeQuery( "SELECT rec_ID FROM Recipe" );
			
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
		ResultSet rs = Database.st.executeQuery( statement );
		
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
