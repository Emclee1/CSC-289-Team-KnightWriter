package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kitt.Search;
import kitt.Database;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;

public class KITTGUI
{
    protected JFrame frame;
    protected JTextField textSearch;
    protected JPanel jpPanel;
    protected JPanel jpContent;
    protected CardLayout cardLayout = new CardLayout(0, 0);
    
    /**
     * Create the application.
     */
    public KITTGUI() 
    {
        initialize();
    }
    
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KITTGUI window = new KITTGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Initialize the contents of the frame.
     */
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
    
    protected JLabel addLabel() { return new JLabel(); }
    
    protected void addPanelData()
    {
    	WelcomeGui welcome = new WelcomeGui( jpContent );
    	cardLayout.show( jpContent, "welcome");
    }
    
    protected void listener( JButton button )
    {
    	switch( button.getText() )
    	{
    		case "Import":
    			button.addMouseListener(new MouseAdapter() {
    	 	    	@Override
    	 	    	public void mouseClicked(MouseEvent arg0) 
    	 	    	{
    	 	    		ImportGui imp = new ImportGui( jpContent );
    	 	    		cardLayout.show( jpContent, "import");
    	 	    	}
    	 	    });
    			break;
    		case "Export":
    			button.addMouseListener(new MouseAdapter() {
    	 	    	@Override
    	 	    	public void mouseClicked(MouseEvent e)
    	 	    	{
    	 	    		ExportGui export = new ExportGui( jpContent );
    	 	    		cardLayout.show( jpContent, "export");
    	 	    	}
    	 	    });
    			break;
    		case "Search":
    			button.addMouseListener(new MouseAdapter() {
    	 	    	@Override
    	 	    	public void mouseClicked(MouseEvent arg0) 
    	 	    	{
    	 	    		Search search = new Search( textSearch.getText() );
    	 	    		ResultGui result = new ResultGui( false, jpContent, cardLayout,  search.getResults() );
    	 	    		cardLayout.show( jpContent, "result");
    	 	    	}
    	 	    });
    			break;
    		case "My Favorites":
    			break;
    		case "Advanced Search":
    			button.addMouseListener(new MouseAdapter() {
    	 	    	@Override
    	 	    	public void mouseClicked(MouseEvent arg0) 
    	 	    	{
    	 	    		AdvancedSearchGui advSearch = new AdvancedSearchGui( jpContent, cardLayout );
    	 	    		cardLayout.show( jpContent, "advSearch");
    	 	    	}
    	 	    });
    			break;
    		case "Random Recipe":
    			break;
    		case "New Recipe":
    			break;
    		case "Help":
    			button.addMouseListener(new MouseAdapter() {
    	 	    	@Override
    	 	    	public void mouseClicked(MouseEvent arg0) 
    	 	    	{
    	 	    		cardLayout.show( jpContent, "welcome");
    	 	    	}
    	 	    });
    		default:
    			break;
    	}
    }
}