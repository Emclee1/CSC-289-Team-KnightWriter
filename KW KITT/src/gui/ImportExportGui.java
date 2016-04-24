package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import kitt.Database;
import kitt.ImportExport;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class ImportExportGui extends KITTGUI  implements ItemListener
{
	protected JFrame frame;
    protected JTextField textSearch;
    protected JPanel jpPanel;
    protected JPanel jpContent;
    protected CardLayout cardLayout = new CardLayout(0, 0);
	
    protected void initialize()
	{
    	Database db = new Database();
    	frame = new JFrame();
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        JPanel jpMenu = new JPanel();
         
         jpContent = new JPanel();
         
        jpPanel = new JPanel();
         
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        		groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                		.addComponent(jpMenu, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                		.addPreferredGap(ComponentPlacement.RELATED)
                		.addComponent(jpContent, GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                		.addContainerGap())
         );
        groupLayout.setVerticalGroup(
        		groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(jpMenu, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addComponent(jpContent, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
         );
        jpMenu.setLayout(new MigLayout("", "[grow]", "[50:n][][][][][][15.00][][15.00][][15.00][][21.00][15.00][15.00][][][][][][][][][][]"));
         
        JLabel lblWelcome = new JLabel("Welcome To KITT ");
 	    lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
 	    jpMenu.add(lblWelcome, "flowx,cell 0 0,alignx center,aligny bottom");
 	    
 	    JLabel lblVersion = new JLabel("version 1.0");
 	    jpMenu.add(lblVersion, "cell 0 1,alignx center,aligny top");
 	    
 	    JLabel lblRecName = new JLabel("Enter Recipe Name");
 	    jpMenu.add(lblRecName, "cell 0 3,alignx center");
 	    
 	    textSearch = new JTextField();
 	    jpMenu.add(textSearch, "flowx,cell 0 4,growx,aligny center");
 	    textSearch.setColumns(10);
 	    
 	    JButton btnSearch = new JButton("Search");
 	    listener( btnSearch );
 	    jpMenu.add(btnSearch, "cell 0 4");
 	    
 	    JButton btnAdvSearch = new JButton("Advanced Search");
 	    listener( btnAdvSearch );  
 	    jpMenu.add(btnAdvSearch, "cell 0 5,alignx left");
 	    
 	    JButton btnRand = new JButton("Random Recipe ");
 	    listener( btnRand );
 	    jpMenu.add(btnRand, "cell 0 7,growx");
 	    
 	    JButton btnFav = new JButton("My Favorites");
 	    listener( btnFav );
 	    jpMenu.add(btnFav, "cell 0 9,growx");
 	    
 	    JButton btnNewRec = new JButton("New Recipe");
 	    listener( btnNewRec );
 	    jpMenu.add(btnNewRec, "cell 0 11,growx");
 	    
 	    JButton btnIngredient = new JButton("New Ingredient");
 	    listener( btnIngredient );
 	    jpMenu.add(btnIngredient, "cell 0 12,growx");
 	    
 	    JButton btnImport = new JButton("Import");
 	    listener( btnImport );
 	    jpMenu.add(btnImport, "cell 0 15,growx");
 	    
 	    JButton btnExport = new JButton("Export");
 	    listener( btnExport );
 	    jpMenu.add(btnExport, "cell 0 16,growx");
 	    
 	    JButton btnHelp = new JButton("Help");
 	    listener( btnHelp );
 	    jpMenu.add(btnHelp, "flowx,cell 0 23");
 	    
 	    JButton btnAbout = new JButton("About");
 	    listener( btnHelp );
 	    jpMenu.add(btnAbout, "cell 0 23,alignx right,aligny baseline");
        jpContent.setLayout( cardLayout );
        
        frame.getContentPane().setLayout(groupLayout);
        
        jpPanel = new JPanel();
    	jpContent.add(jpPanel, "jpPanel");
        jpPanel.setLayout(new BorderLayout(0, 0));
         
        Component verticalStrut_1 = Box.createVerticalStrut(100);
        jpPanel.add(verticalStrut_1, BorderLayout.SOUTH);
         
        JLabel lblDataLbl = addLabel();
        lblDataLbl.setHorizontalAlignment(SwingConstants.CENTER);
        lblDataLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
        jpPanel.add(lblDataLbl, BorderLayout.NORTH);
         
        Component horizontalStrut = Box.createHorizontalStrut(100);
        jpPanel.add(horizontalStrut, BorderLayout.WEST);
         
        Component horizontalStrut_1 = Box.createHorizontalStrut(96);
        jpPanel.add(horizontalStrut_1, BorderLayout.EAST);
        
        addPanelData();
     }
    
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
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds( scrollPane.getBounds() );
	    scrollPane.setViewportView(panel_1);
	    
	    panel_1.setBackground(Color.LIGHT_GRAY);
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
	public ExportGui( JPanel masterPanel )
	{
		initialize();
		masterPanel.add( jpPanel, "export");
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
    public ImportGui( JPanel masterPanel )
	{
		initialize();
		masterPanel.add( jpPanel, "import");
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