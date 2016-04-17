package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import kitt.ImportExport;

public class ImportExportGui extends KITTGUI  implements ItemListener
{
    protected ArrayList<JCheckBox> boxes;
    protected ArrayList<Integer> recIDs;
    
    /**
     * Create the application.
     */
    public ImportExportGui() 
    {
        initialize();
    }
    
    protected void addPanelData()
    {
    	JPanel panel = new JPanel();
	    jpPanel.add(panel, BorderLayout.CENTER);
	    
	    JButton btnConfirm = new JButton("Confirm");
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBorder( new TitledBorder( new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null), "Recipe List", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null ) ) ;
	    panel_1.setBackground(Color.LIGHT_GRAY);
	    
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
	    			.addGap(309)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
	    				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(btnConfirm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
	    			.addContainerGap(320, Short.MAX_VALUE))
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap(65, Short.MAX_VALUE)
	    			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(108))
	    );
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
	    
	    String[] recipes = getData( btnConfirm );
	    boxes = new ArrayList<JCheckBox>();
	    recIDs = new ArrayList<Integer>();
	    
	    if( recipes == null )
	    {
	    	JLabel noDat = new JLabel( "No data" );
	    	panel_1.add( noDat );
	    }
	    else
	    {
	    	for( int i = 0; i < recipes.length; i++ )
		    {
		    	JCheckBox box = new JCheckBox( recipes[i].split( "\\|" )[1] );
		    	box.setName( recipes[i].split( "\\|" )[0] );
		    	box.setBackground(Color.LIGHT_GRAY);
		    	box.addItemListener(this);
		    	boxes.add( box );
		    	boxes.get( i ).isSelected();
		    	panel_1.add( box );
		    }
	    }
	    
	    panel.setLayout(gl_panel);
    }
    
    protected String[] getData( JButton btnConfirm )
    {
    	return new String[]{ "No data" };
    }
    
    protected JLabel addLabel()
    {
    	return new JLabel( "ImportExport Default, Does nothing" );
    }
    
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		
		//Object source = arg0.getItemSelectable();
		JCheckBox button = (JCheckBox)arg0.getItem();
		
		//int index = boxes.indexOf( source );
		
		if( arg0.getStateChange() ==1 )
		{
			recIDs.add( Integer.parseInt( button.getName() ) );
		}
		else
		{
			recIDs.remove( new Integer( button.getName()  ) );
		}
	}
}

class ExportGui  extends ImportExportGui
{	
	public ExportGui()
	{
		initialize();
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
	    				ImportExport.exportData( recIDs );
	    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully exported.");
	    			}
	    });
	    
	    return text;
	}
}

class ImportGui extends ImportExportGui
{
    public ImportGui()
	{
		initialize();
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
		    				ImportExport.importData( recIDs );
		    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully imported.");
		    			}
		    });
	    
	    return text;
	}
}