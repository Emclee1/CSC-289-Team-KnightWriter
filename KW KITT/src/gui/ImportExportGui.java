package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class ImportExportGui  implements ItemListener
{
    protected JFrame frame;
    protected JTextField textSearch;
    protected ArrayList<JCheckBox> boxes;
    protected ArrayList<Boolean> boxSel;
    
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
    public ImportExportGui() 
    {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    protected void initialize()
	{
		frame = new JFrame();
	    frame.setBounds(100, 100, 1200, 700);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel jpMenu = new JPanel();
	    
	    JPanel jpContent = new JPanel();
	    
	    JPanel jpPanel = new JPanel();
	    
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
	    jpContent.add(jpPanel);
	    jpPanel.setLayout(new BorderLayout(0, 0));
	    
	    Component verticalStrut_1 = Box.createVerticalStrut(100);
	    jpPanel.add(verticalStrut_1, BorderLayout.SOUTH);
	    
	    JLabel lblNewLabel_2 = addLabel();
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    jpPanel.add(lblNewLabel_2, BorderLayout.NORTH);
	    
	    Component horizontalStrut = Box.createHorizontalStrut(100);
	    jpPanel.add(horizontalStrut, BorderLayout.WEST);
	    
	    Component horizontalStrut_1 = Box.createHorizontalStrut(96);
	    jpPanel.add(horizontalStrut_1, BorderLayout.EAST);
	    
	    JPanel panel = new JPanel();
	    jpPanel.add(panel, BorderLayout.CENTER);
	    
	    JButton btnConfirm = new JButton("Confirm");
	    String[] text = getData( btnConfirm );
	    
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
	    
	    boxes = new ArrayList<JCheckBox>();
	    boxSel = new ArrayList<Boolean>();
	    
	    for( int i = 0; i < text.length; i++ )
	    {
	    	JCheckBox box = new JCheckBox( text[i] );
	    	box.setBackground(Color.LIGHT_GRAY);
	    	box.addItemListener(this);
	    	boxes.add( box );
	    	boxSel.add( false );
	    	panel_1.add( box );
	    }
	    
	    panel.setLayout(gl_panel);
	    frame.getContentPane().setLayout(groupLayout);
	}
    
    protected String[] getData( JButton btnConfirm )
    {
    	return new String[2];
    }
    
    protected JLabel addLabel()
    {
    	return new JLabel();
    }
    
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		Object source = arg0.getItemSelectable();
		
		int index = boxes.indexOf( source );
		if( index != -1 )
		{
			if( arg0.getStateChange() ==1 )
			{
				boxSel.set(index,true);
			}
			else
			{
				boxSel.set(index,false);
			}
		}
	}
}