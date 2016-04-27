package gui;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kitt.Database;
import kitt.ImportExport;

///////////////////////////////////////////////////////////////////////
//
// Filename: RemoveRecipeGui.java
//
// Description:
// Removes the selected recipes from the database
//
/////////////////////////////////////////////////////////////////////////
public class RemoveRecipeGui extends ImportExportGui
{
	public RemoveRecipeGui( JPanel masterPanel, CardLayout masterLayout )
	{
		masterPanel.add( jpPanel, "remove");
		addListener( masterPanel, masterLayout );
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: addLabel                        
//
// Description:
// Overrides the default protected addLabel to display the Remove title.
//
///////////////////////////////////////////////////////////////////////
	protected JLabel addLabel()
    {
    	return new JLabel("Remove Recipes");
    }
	
///////////////////////////////////////////////////////////////////////
//
// Function: getData                         
//
// Description:
// Retrieves the list of available reposes
//
///////////////////////////////////////////////////////////////////////
	protected String[] getData()
	{
		ImportExport export = new ImportExport();
	    String[] text = export.getExportList().split( "\r\n" );
	    
	    return text;
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: addListener                
//
// Description:
// Adds a listener for the confirm button to execute changes.
//
///////////////////////////////////////////////////////////////////////
	protected void addListener(JPanel masterPanel, CardLayout masterLayout  )
	{
		btnConfirm.addMouseListener
	    (
	    		new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0)
	    			{
	    				if(  JOptionPane.showConfirmDialog( frame, "Are you sure you want to remove these recipes?", "Save Changes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
	    				{
	    					removeRecs();
	    					JOptionPane.showMessageDialog( frame, "Recipes Successfully Removed");
	    					RemoveRecipeGui rem = new RemoveRecipeGui( masterPanel, masterLayout );
	    					masterLayout.show( masterPanel, "remove" );
	    				}
	    			}
	    });
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: getPanel                 
//
// Description:
// Returns the result panel of delete operation
//
// Returns:  
// panel_1 : Gui of the results
//
///////////////////////////////////////////////////////////////////////
	public JPanel getPanel()
	{
		return panel_1;
	}

///////////////////////////////////////////////////////////////////////
//
//Function: removeRecs             
//
// Description:
// Executes the delete operation
//
///////////////////////////////////////////////////////////////////////
	protected void removeRecs()
	{
		String statement = "DELETE FROM Ing_Line WHERE rec_ID IN ( ";
		for( int i = 0; i < recIDs.size(); i++ )
		{
			statement += recIDs.get( i ) + ( i == recIDs.size() - 1 ? " )" : ", " );
		}
		
		try 
		{
			Database.st.execute( statement );
			statement = statement.replace( "Ing_Line", "Recipe" );
			Database.st.execute( statement );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
