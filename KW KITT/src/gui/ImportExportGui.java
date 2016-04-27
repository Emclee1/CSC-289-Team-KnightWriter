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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import kitt.ImportExport;
import javax.swing.JScrollPane;

///////////////////////////////////////////////////////////////////////
//
// Filename: ImportExportGui.java
//
// Description:
// The program takes user input and a criteria to retrieve
// all relevant search results
//
/////////////////////////////////////////////////////////////////////////
public class ImportExportGui extends KITTGUI  implements ItemListener
{
    protected ArrayList<JCheckBox> boxes;
    protected ArrayList<Integer> recIDs;
    
    protected JPanel panel;
    protected JPanel panel_1;
    protected JButton btnConfirm;
    
///////////////////////////////////////////////////////////////////////
//
// Function: addPanelData                                    
//
// Description:
// Overrides the default protected addPanelData to create the Gui as
// a panel that displays available import/export recipes.
//
///////////////////////////////////////////////////////////////////////
    protected void addPanelData()
    {
    	panel = new JPanel();
	    jpPanel.add(panel, BorderLayout.CENTER);
	    
	    btnConfirm = new JButton("Confirm");
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBorder( new TitledBorder( new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null), "Recipe List", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null ) ) ;
	    
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(309)
	    					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(233)
	    					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap(232, Short.MAX_VALUE))
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(56)
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(87))
	    );
	    
	    panel_1 = new JPanel();
	    panel_1.setBounds( scrollPane.getBounds() );
	    scrollPane.setViewportView(panel_1);
	    
	    panel_1.setBackground(Color.LIGHT_GRAY);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
	    
	    String[] recipes = getData();
	    boxes = new ArrayList<JCheckBox>();
	    recIDs = new ArrayList<Integer>();
	    
	    if( recipes == null || recipes[0].equals( "No data" ))
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
    
///////////////////////////////////////////////////////////////////////
//
// Function: getData                         
//
// Description:
// Retrieves the data that will be displayed on this panel
//
///////////////////////////////////////////////////////////////////////
    protected String[] getData()
    {
    	return new String[]{ "No data" };
    }
    
///////////////////////////////////////////////////////////////////////
//
// Function: addLabel                        
//
// Description:
// Overrides the default protected addLabel to display the Import/Export title.
//
///////////////////////////////////////////////////////////////////////
    protected JLabel addLabel()
    {
    	return new JLabel( "ImportExport Default, Does nothing" );
    }
    
///////////////////////////////////////////////////////////////////////
//
// Function: itemStateChanged                    
//
// Description:
// Listener that adds any checked boxes to list of ids to search
//
///////////////////////////////////////////////////////////////////////
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		JCheckBox button = (JCheckBox)arg0.getItem();
		
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
///////////////////////////////////////////////////////////////////////
//
// Function: Constructor                                
//
// Description:
// Initializes the class and adds itself to the master layout.
//
///////////////////////////////////////////////////////////////////////
	public ExportGui( JPanel masterPanel )
	{
		masterPanel.add( jpPanel, "export");
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: addLabel                        
//
// Description:
// Overrides the default protected addLabel to display the Export title.
//
///////////////////////////////////////////////////////////////////////
	protected JLabel addLabel()
    {
    	return new JLabel("Export");
    }
	
///////////////////////////////////////////////////////////////////////
//
// Function: getData                         
//
// Description:
// Retrieves the list of recipes that can be exported
//
///////////////////////////////////////////////////////////////////////
	protected String[] getData()
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
	    				for( JCheckBox box : boxes )
	    				{
	    					box.setSelected( false );
	    				}
	    			}
	    });
	    
	    return text;
	}
}

class ImportGui extends ImportExportGui
{
///////////////////////////////////////////////////////////////////////
//
// Function: Constructor                                
//
// Description:
// Initializes the class and adds itself to the master layout.
//
///////////////////////////////////////////////////////////////////////
    public ImportGui( JPanel masterPanel )
	{
		masterPanel.add( jpPanel, "import");
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: addLabel                        
//
// Description:
// Overrides the default protected addLabel to display the Import title.
//
///////////////////////////////////////////////////////////////////////
	protected JLabel addLabel()
    {
    	return new JLabel("Import");
    }
	
///////////////////////////////////////////////////////////////////////
//
// Function: getData                         
//
// Description:
// Retrieves the list of recipes that can be imported
//
///////////////////////////////////////////////////////////////////////
	protected String[] getData()
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
		    				for( JCheckBox box : boxes )
		    				{
		    					box.setSelected( false );
		    				}
		    			}
		    });
	    
	    return text;
	}
}