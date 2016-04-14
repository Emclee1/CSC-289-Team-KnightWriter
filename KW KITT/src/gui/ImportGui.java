package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kitt.ImportExport;

public class ImportGui extends ImportExportGui
{
    public ImportGui()
	{
		initialize();
	}
	
	public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ImportGui window = new ImportGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	protected JLabel addLabel()
    {
    	return new JLabel("Import");
    }
	
	protected String[] getData( JButton btnConfirm )
	{
		 ImportExport imp = new ImportExport();
		 String[] text = imp.getImportList().split( "\n" );
	    
		 btnConfirm.addMouseListener
		    (
		    		new MouseAdapter() {
		    			@Override
		    			public void mouseClicked(MouseEvent arg0)
		    			{
		    				ImportExport.importData( boxSel );
		    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully imported.");
		    			}
		    });
	    
	    return text;
	}
}