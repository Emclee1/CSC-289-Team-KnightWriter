package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kitt.ImportExport;

public class ExportGui  extends ImportExportGui
{	
	public ExportGui()
	{
		initialize();
	}
	
	public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExportGui window = new ExportGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	protected JLabel addLabel()
    {
    	return new JLabel("Export");
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
	    				ImportExport.exportData( boxSel );
	    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully exported.");
	    			}
	    });
	    
	    return text;
	}
}