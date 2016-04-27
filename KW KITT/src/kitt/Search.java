package kitt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import gui.RecipeGui;

///////////////////////////////////////////////////////////////////////
//
// Filename: Search.java
//
// Description:
// Creates SQL statements to search the database
//
/////////////////////////////////////////////////////////////////////////
public class Search 
{
	protected ResultSet results;
	protected String type;
	protected String search;
	
	public Search(){}
	
	public Search( String search )
	{
		advSearch( search, "Rec" );
	}
	
	public void advSearch( String search, String type )
	{
		this.type = type;
		this.search = search;
		String statement = "SELECT rec_ID, rec_name From Recipe WHERE ";
		
		if( search == null )
		{
			results = null;
			return;
		}
		
		if( type.equals( "Rec" ) || type.equals( "Rec" ) )
		{
			try
			{
				Integer.parseInt( search );
				results = null;
				return;
			}
			catch( NumberFormatException e ){}
		}
		
		if( type.equals( "Cal" ) )
		{
			if( !StringUtils.isNumeric( search ) )
			{
				results = null;
				return;
			}
		}
		
		try 
		{
			if( type.equals( "Rec" ) )
			{
				statement += String.format( "rec_name LIKE '*%s*'", search );
			}
			else if( type.equals( "Ing" ) )
			{
				statement = String.format( "SELECT ing_ID from Ingredient WHERE ing_name LIKE '*%s*'", search);
				results = Database.st.executeQuery( statement );
				
				if( !results.isBeforeFirst() )
				{
					results = null;
					return;
				}
				
				statement = "SELECT rec_ID From Ing_Line WHERE ing_ID IN ( ";
				while( results.next() )
				{
					statement += results.getInt( 1 ) + ( ( results.isLast() ) ? " )" : ", " ); 
				}
				
				results = Database.st.executeQuery( statement );
				
				if( !results.isBeforeFirst() )
				{
					results = null;
					return;
				}
				
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
				statement = "SELECT rec_ID, rec_name From Recipe";
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
		String newRes = "";
		
		try 
		{
			if( results == null || !results.isBeforeFirst() )
			{
				return null;
			}
			
			int i = 1;
			while( results.next() )
			{
				if( type.equals( "Cal" ) )
				{
					RecipeGui rec = new RecipeGui( results.getInt( 1 ) );
					double cal = rec.getCalories();
					
					if( cal <= Double.parseDouble( search ) )
					{
						newRes = String.format( ( "%d|%d. %s" ), results.getInt(1), i, results.getString(2) );
						text.add( newRes );
						i++;
					}
				}
				else
				{
					newRes = String.format( ( "%d|%d. %s" ), results.getInt(1), i, results.getString(2) );
					text.add( newRes );
					i++;
				}
			}
		} catch (SQLException e) 
		{
			return null;
		}
		
		return  text;
	}
	
	public static ArrayList<String> getFavorites()
	{
		ArrayList<String> text = new ArrayList<String>();
		ResultSet fav;
		
		try 
		{
			fav = Database.st.executeQuery( "SELECT rec_ID, rec_name From Recipe Where favorite = favorite" );
			
			if( !fav.isBeforeFirst() || fav == null )
			{
				return null;
			}
			
			int i = 1;
			while( fav.next() )
			{
				String result = String.format( ( "%d|%d. %s" ), fav.getInt(1), i, fav.getString(2) );
				text.add( result );
				i++;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return  text;
	}
}