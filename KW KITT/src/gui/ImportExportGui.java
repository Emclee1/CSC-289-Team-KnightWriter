package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import kitt.ImportExport;

import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImportExportGui 
{
    protected JFrame frame;
    protected JTextField textSearch;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ImportExportGui window = new ImportExportGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the application.
     */
    public ImportExportGui() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {  
    }
}

class ExportGui extends ImportExportGui
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
	
	private void initialize()
	{
		frame = new JFrame();
	    frame.setBounds(100, 100, 1200, 700);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel jpMenu = new JPanel();
	    
	    JPanel jpContent = new JPanel();
	    
	    JPanel jpExport = new JPanel();
	    
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
	    
	    JLabel lblNewLabel = new JLabel("Welcome To KITT ");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    jpMenu.add(lblNewLabel, "flowx,cell 0 0,alignx center,aligny bottom");
	    
	    JLabel lblVersion = new JLabel("version 1.0");
	    jpMenu.add(lblVersion, "cell 0 1,alignx center,aligny top");
	    
	    JLabel lblNewLabel_1 = new JLabel("Enter Recipe Name");
	    jpMenu.add(lblNewLabel_1, "cell 0 3,alignx center");
	    
	    textSearch = new JTextField();
	    jpMenu.add(textSearch, "flowx,cell 0 4,growx,aligny center");
	    textSearch.setColumns(10);
	    
	    JButton btnSearch = new JButton("Search");
	    jpMenu.add(btnSearch, "cell 0 4");
	    
	    JButton btnNewButton = new JButton("Advanced Search");
	    jpMenu.add(btnNewButton, "cell 0 5,alignx left");
	    
	    JButton btnNewButton_1 = new JButton("Random Recipe ");
	    jpMenu.add(btnNewButton_1, "cell 0 7,growx");
	    
	    JButton btnNewButton_2 = new JButton("My Favroates");
	    jpMenu.add(btnNewButton_2, "cell 0 9,growx");
	    
	    JButton btnNewButton_3 = new JButton("New Recipe");
	    jpMenu.add(btnNewButton_3, "cell 0 11,growx");
	    
	    JButton btnNewButton_4 = new JButton("New Ingredient");
	    jpMenu.add(btnNewButton_4, "cell 0 12,growx");
	    
	    JButton btnNewButton_5 = new JButton("Import");
	    jpMenu.add(btnNewButton_5, "cell 0 15,growx");
	    
	    JButton btnNewButton_6 = new JButton("Export");
	    jpMenu.add(btnNewButton_6, "cell 0 16,growx");
	    
	    JButton btnHelp = new JButton("Help");
	    jpMenu.add(btnHelp, "flowx,cell 0 23");
	    
	    JButton btnAbout = new JButton("About");
	    jpMenu.add(btnAbout, "cell 0 23,alignx right,aligny baseline");
	    jpContent.setLayout(new CardLayout(0, 0));
	    jpContent.add(jpExport);
	    jpExport.setLayout(new BorderLayout(0, 0));
	    
	    Component verticalStrut_1 = Box.createVerticalStrut(100);
	    jpExport.add(verticalStrut_1, BorderLayout.SOUTH);
	    
	    JLabel lblNewLabel_2 = new JLabel("Export");
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    jpExport.add(lblNewLabel_2, BorderLayout.NORTH);
	    
	    Component horizontalStrut = Box.createHorizontalStrut(100);
	    jpExport.add(horizontalStrut, BorderLayout.WEST);
	    
	    Component horizontalStrut_1 = Box.createHorizontalStrut(96);
	    jpExport.add(horizontalStrut_1, BorderLayout.EAST);
	    
	    JPanel panel = new JPanel();
	    jpExport.add(panel, BorderLayout.CENTER);
	    
	    JTextPane txtpnRecipes = new JTextPane();
	    txtpnRecipes.setBackground(Color.LIGHT_GRAY);
	    
	    txtpnRecipes.setBorder( new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Recipes", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 255, 0)) );
	    ImportExport export = new ImportExport();
	    String text = export.getExportList();
	    txtpnRecipes.setText( text );
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    JButton btnExport = new JButton("Confirm");
	    
	    btnExport.addMouseListener
	    (
	    		new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0)
	    			{
	    				ImportExport.exportData();
	    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully exported.");
	    			}
	    });
	    
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(154)
	    					.addComponent(txtpnRecipes, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(309)
	    					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap(158, Short.MAX_VALUE))
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(33)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
	    				.addComponent(txtpnRecipes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(108))
	    );
	    panel.setLayout(gl_panel);
	    frame.getContentPane().setLayout(groupLayout);
	}
}

class ImportGui extends ImportExportGui
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
	
	private void initialize()
	{
		frame = new JFrame();
	    frame.setBounds(100, 100, 1200, 700);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel jpMenu = new JPanel();
	    
	    JPanel jpContent = new JPanel();
	    
	    JPanel jpImport = new JPanel();
	    
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
	    
	    JLabel lblNewLabel = new JLabel("Welcome To KITT ");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    jpMenu.add(lblNewLabel, "flowx,cell 0 0,alignx center,aligny bottom");
	    
	    JLabel lblVersion = new JLabel("version 1.0");
	    jpMenu.add(lblVersion, "cell 0 1,alignx center,aligny top");
	    
	    JLabel lblNewLabel_1 = new JLabel("Enter Recipe Name");
	    jpMenu.add(lblNewLabel_1, "cell 0 3,alignx center");
	    
	    textSearch = new JTextField();
	    jpMenu.add(textSearch, "flowx,cell 0 4,growx,aligny center");
	    textSearch.setColumns(10);
	    
	    JButton btnSearch = new JButton("Search");
	    jpMenu.add(btnSearch, "cell 0 4");
	    
	    JButton btnNewButton = new JButton("Advanced Search");
	    jpMenu.add(btnNewButton, "cell 0 5,alignx left");
	    
	    JButton btnNewButton_1 = new JButton("Random Recipe ");
	    jpMenu.add(btnNewButton_1, "cell 0 7,growx");
	    
	    JButton btnNewButton_2 = new JButton("My Favroates");
	    jpMenu.add(btnNewButton_2, "cell 0 9,growx");
	    
	    JButton btnNewButton_3 = new JButton("New Recipe");
	    jpMenu.add(btnNewButton_3, "cell 0 11,growx");
	    
	    JButton btnNewButton_4 = new JButton("New Ingredient");
	    jpMenu.add(btnNewButton_4, "cell 0 12,growx");
	    
	    JButton btnNewButton_5 = new JButton("Import");
	    jpMenu.add(btnNewButton_5, "cell 0 15,growx");
	    
	    JButton btnNewButton_6 = new JButton("Export");
	    jpMenu.add(btnNewButton_6, "cell 0 16,growx");
	    
	    JButton btnHelp = new JButton("Help");
	    jpMenu.add(btnHelp, "flowx,cell 0 23");
	    
	    JButton btnAbout = new JButton("About");
	    jpMenu.add(btnAbout, "cell 0 23,alignx right,aligny baseline");
	    jpContent.setLayout(new CardLayout(0, 0));
	    jpContent.add(jpImport);
	    jpImport.setLayout(new BorderLayout(0, 0));
	    
	    Component verticalStrut_1 = Box.createVerticalStrut(100);
	    jpImport.add(verticalStrut_1, BorderLayout.SOUTH);
	    
	    JLabel lblNewLabel_2 = new JLabel("Import");
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    jpImport.add(lblNewLabel_2, BorderLayout.NORTH);
	    
	    Component horizontalStrut = Box.createHorizontalStrut(100);
	    jpImport.add(horizontalStrut, BorderLayout.WEST);
	    
	    Component horizontalStrut_1 = Box.createHorizontalStrut(96);
	    jpImport.add(horizontalStrut_1, BorderLayout.EAST);
	    
	    JPanel panel = new JPanel();
	    jpImport.add(panel, BorderLayout.CENTER);
	    
	    JTextPane txtpnRecipes = new JTextPane();
	    txtpnRecipes.setBackground(Color.LIGHT_GRAY);
	    
	    txtpnRecipes.setBorder( new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Recipes", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 255, 0)) );
	    ImportExport imp = new ImportExport();
	    String text = imp.getImportList();
	    txtpnRecipes.setText( text );
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    JButton btnExport = new JButton("Confirm");
	    
	    btnExport.addMouseListener
	    (
	    		new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent arg0)
	    			{
	    				ImportExport.importData();
	    				JOptionPane.showMessageDialog( frame, "Recipes and Ingredients successfully imported.");
	    			}
	    });
	    
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(154)
	    					.addComponent(txtpnRecipes, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(309)
	    					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap(158, Short.MAX_VALUE))
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(33)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
	    				.addComponent(txtpnRecipes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(108))
	    );
	    panel.setLayout(gl_panel);
	    frame.getContentPane().setLayout(groupLayout);
	}
}