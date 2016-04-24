package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kitt.Database;
import kitt.ImportExport;

public class RemoveRecipeGui extends ImportExportGui
{
	public RemoveRecipeGui( JPanel masterPanel )
	{
		masterPanel.add( jpPanel, "remove");
	}
	
	protected JLabel addLabel()
    {
    	return new JLabel("Remove Recipes");
    }
	
	protected String[] getData( JButton btnConfirm )
	{
		ImportExport export = new ImportExport();
	    String[] text = export.getExportList().split( "\r\n" );
	    
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
	    					RemoveRecipeGui rem = new RemoveRecipeGui( jpContent );
	    					cardLayout.show( jpContent, "remove" );
	    				}
	    			}
	    });
	    
	    return text;
	}
	
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
