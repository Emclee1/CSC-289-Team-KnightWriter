package kitt;

import java.util.ArrayList;
import java.util.Arrays;

///////////////////////////////////////////////////////////////////////
//
//Filename: ComboItem.java
//
// Description:
// Creates an item to return a string or integer based on the combo box
//
/////////////////////////////////////////////////////////////////////////

public class ComboItem 
{
	private String name;
	private String  id;
	private ArrayList<String> fullNames = new ArrayList<String>( Arrays.asList( "cheese", "ginger", "pasta", "tomato", "potato", "pineapple", "pineapple", "red wine", "beef") );
	
	public ComboItem( String result )
	{
		id = result.split( "\\|" )[0];
		name = result.split( "\\|" )[0];
		
		if( result.split( "\\|" ).length > 1 )
		{
			String text =result.split( "\\|" )[1] ;
			boolean full = false;
			
			for( String name : fullNames )
			{
				if( text.contains( name ) )
				{
					full = true;
				}
			}
			
			name = Character.toUpperCase( text.charAt( 0 ) ) + ( full ? text.substring( 1 ) : text.split( "," )[ 0 ].substring( 1 ) );
		}
	}

	public String getID()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
