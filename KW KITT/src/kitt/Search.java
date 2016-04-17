package kitt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Search 
{
	protected ResultSet results;
	
	public Search(){}
	
	public Search( String search )
	{
		advSearch( search, "Rec" );
	}
	
	public void advSearch( String search, String type )
	{
		int value;
		String statement = "SELECT rec_ID, rec_name From Recipe WHERE ";
		
		if( search == null )
		{
			results = null;
			return;
		}
		
		if( type.equals( "Rec" ) )
		{
			try
			{
				Integer.parseInt( search );
				results = null;
				return;
			}
			catch( NumberFormatException e ){}
		}
		
		try 
		{
			if( type.equals( "Rec" ) )
			{
				statement += String.format( "rec_name LIKE '*%s*'", search );
			}
			else if( type.equals( "Ing" ) )
			{
				statement = String.format("SELECT rec_ID From Ing_Line WHERE ing_ID = %d",  Integer.parseInt( search ) );
				results = Database.st.executeQuery( statement );
				
				statement = "SELECT rec_ID, rec_name From Recipe WHERE rec_ID IN (";
				while( results.next() )
				{
					statement += results.getInt( 1 ) + ( ( results.isLast() ) ? ")" : ", " ); 
				}
			}
			else if( type.equals( "Dif" ) )
			{
				statement += String.format("difficulty = '%s'", search);
			}
			else if( type.equals( "Course" ) )
			{
				statement += String.format("course = '%s'", search);
			}
			else
			{
				value = Integer.valueOf( search );
				statement += String.format("course = %d", value);
			}
			
			results = Database.st.executeQuery( statement );
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getResults()
	{
		ArrayList<String> text = new ArrayList<String>();
		
		try 
		{
			if( !results.isBeforeFirst() || results == null )
			{
				return null;
				//return new String[]{"No results"};
			}
			
			int i = 1;
			while( results.next() )
			{
				String newRes = String.format( ( "%d|%d. %s" ), results.getInt(1), i, results.getString(2) );
				text.add( newRes );
				i++;
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  text;
	}
	
	public ArrayList<String> getFavorites()
	{
		ArrayList<String> text = new ArrayList<String>();
		ResultSet fav;
		
		try 
		{
			fav = Database.st.executeQuery( "SELECT rec_name From Recipe Where favorite = 1" );
			
			if( !fav.isBeforeFirst() || fav == null )
			{
				return null;
			}
			
			int i = 1;
			while( fav.next() )
			{
				text.add( i + ". " + fav.getString(1) );
				i++;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return  text;
	}
}