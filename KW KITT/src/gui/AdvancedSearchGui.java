package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import kitt.Database;
import kitt.ComboItem;
import kitt.Search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class AdvancedSearchGui extends KITTGUI implements ActionListener
{
    protected JSplitPane splitPane;
    protected JPanel lPanel;
    private ButtonGroup btnMenu;
    private JTextField searchField;
    private JComboBox<ComboItem> comboBox;
    private Search advSearch;
    private JPanel rPanel;
	private JScrollPane scrollPane;
    
    
    public AdvancedSearchGui( JPanel masterPanel, CardLayout cardLayout )
	{
		initialize();
		addPanelData( masterPanel, cardLayout );
		masterPanel.add( jpPanel, "advSearch" );
	}
	
	protected void addPanelData()
	{
		addPanelData( new JPanel(), new CardLayout() );
	}
	
	protected void addPanelData( JPanel masterPanel, CardLayout cardLayout )
	{
    	advSearch = new Search();
    	splitPane = new JSplitPane();
        jpPanel.add(splitPane, BorderLayout.CENTER);
        
        lPanel = new JPanel();
        splitPane.setDividerLocation( 220 );
        splitPane.setLeftComponent(lPanel);
        
        JPanel radioPanel = createRadioPanel();
        //searchField.setVisible( false );
        
        JButton btnConfirm = new JButton("View Matching Recipes");
        btnConfirm.addMouseListener(new MouseAdapter() 
        {
        	@Override
        	public void mouseClicked(MouseEvent arg0)
        	{
        		rPanel.removeAll();
        		performSearch( masterPanel, cardLayout );
        		scrollPane.setViewportView( rPanel );
        	}
        });
        JButton btnClear = new JButton("Clear Results");
        btnClear.addMouseListener(new MouseAdapter() 
        {
        	@Override
        	public void mouseClicked(MouseEvent arg0)
        	{
        		rPanel.removeAll();
        		scrollPane.setViewportView( rPanel );
        	}
        });
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        GroupLayout gl_panel = new GroupLayout(lPanel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        			.addGap(32)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(radioPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(layeredPane, Alignment.LEADING)
        				.addComponent(btnClear, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnConfirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        			.addContainerGap(256, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(63)
        			.addComponent(radioPanel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(171, Short.MAX_VALUE))
        );
        
        searchField = new JTextField();
        searchField.setBounds(0, 0, 169, 28);
        layeredPane.add(searchField);
        searchField.setColumns(10);
        
        comboBox = new JComboBox<ComboItem>();
        comboBox.setBackground( Color.WHITE );
        comboBox.setVisible( false );
        layeredPane.setLayer(comboBox, 1);
        comboBox.setBounds(0, 0, 169, 28);
        layeredPane.add(comboBox);
        lPanel.setLayout(gl_panel);
        
        rPanel = new JPanel();
        scrollPane = new JScrollPane( rPanel );
        rPanel.setBackground(Color.WHITE);
        
        splitPane.setRightComponent(scrollPane);
	}
	
	public JPanel createRadioPanel()
    {
    	JPanel radioPanel = new JPanel();
    	radioPanel.setBorder( new TitledBorder( new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null), "Search By", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null ) ) ;
    	
    	btnMenu = new ButtonGroup( );
    	
		JRadioButton btnRec = new JRadioButton( "Recipe Name" );
		btnRec.setSelected(true);
		btnRec.setActionCommand( "Rec" );
		btnRec.addActionListener( this );
		JRadioButton btnIng = new JRadioButton( "Ingredient" );
		btnIng.setActionCommand( "Ing" );
		btnIng.addActionListener( this );
		JRadioButton btnCourse = new JRadioButton( "Course" );
		btnCourse.setActionCommand( "Course" );
		btnCourse.addActionListener( this );
		JRadioButton btnCal = new JRadioButton( "Calories" );
		btnCal.setActionCommand( "Cal" );
		btnCal.addActionListener( this );
		JRadioButton btnDif = new JRadioButton( "Difficulty" );
		btnDif.setActionCommand( "Dif" );
		btnDif.addActionListener( this );
		
		btnMenu.add( btnRec );
		btnMenu.add( btnIng );
		btnMenu.add( btnCourse );
		btnMenu.add( btnCal );
		btnMenu.add( btnDif );
		
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		
		radioPanel.add( btnRec );
		radioPanel.add( btnIng );
		radioPanel.add( btnCourse );
		radioPanel.add( btnCal );
		radioPanel.add( btnDif );
		
		return radioPanel;
    }
	
	protected JLabel addLabel()
    {
    	return new JLabel("Advanced Search");
    }
	
	public void performSearch( JPanel masterPanel, CardLayout cardLayout )
    {
    	String search = "";
		
		if( comboBox.isVisible() )
		{
			search = ( (ComboItem)comboBox.getSelectedItem() ).getID();
			
		}
		else
		{
			search = searchField.getText();
		}
		
    	 advSearch.advSearch( search, btnMenu.getSelection().getActionCommand() );
    	 
    	 ResultGui results = new ResultGui( true, masterPanel, cardLayout, advSearch.getResults() );
    	 rPanel = results.getRPanel();
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String text = arg0.getActionCommand();
		ResultSet set;
		
		comboBox.removeAllItems();
		
		if( text.equals( "Rec" ) || text.equals( "Cal" ) )
		{
			searchField.setVisible( true );
			comboBox.setVisible( false );
			return;
		}
		else
		{
			try 
			{
				if( text.equals( "Ing" ) )
				{
					set = Database.st.executeQuery( "SELECT ing_ID, ing_name From Ingredient ORDER BY ing_name ASC" );
				}
				else if( text.equals( "Course" ) )
				{
					set = Database.st.executeQuery( "SELECT course From Recipe GROUP BY course" );
				}
				else
				{
					set = Database.st.executeQuery( "SELECT difficulty From Recipe GROUP BY difficulty" );
				}
				
				while( set.next() )
				{
					String result = "";
					for( int i = 1; i <= set.getMetaData().getColumnCount(); i++ )
					{
						result += set.getString( i ) + "|";
					}
					ComboItem item = new ComboItem( result );
					
					comboBox.addItem( item );
				}
				
				searchField.setVisible( false );
				comboBox.setVisible( true );
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

class ResultGui extends KITTGUI
{
	private JPanel panel;
	private JScrollPane scrollPane;
	
	public ResultGui()
	{
		initialize();
	}

	public ResultGui( Boolean advSearch, JPanel masterPanel, CardLayout cardLayout, ArrayList<String> results )
	{
		initialize();
		addResults( masterPanel, cardLayout, results );
		
		if( !advSearch )
		{
			masterPanel.add( jpPanel, "result");
		}
	}
	
	protected JLabel addLabel()
    {
    	return new JLabel("Search Results");
    }
	
	protected void addPanelData()
	{
		 panel = new JPanel();
		 scrollPane = new JScrollPane( panel );
		 panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
	     panel.setBackground(Color.WHITE);
	     
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    jpPanel.add( scrollPane );
	}
	
	protected void addResults( JPanel masterPanel, CardLayout cardLayout,ArrayList<String> results )
	{
	     panel.removeAll();
	     
	    if( results != null )
	    {
	    	for( String result : results )
   	 		{
   	 			JButton button = new JButton( result.split( "\\|" )[1] );
   	 			button.setName( result.split( "\\|" )[0] );
   	 			button.addMouseListener(new MouseAdapter() {
	 	    	@Override
	 	    	public void mouseClicked(MouseEvent arg0) 
	 	    	{
	 	    		RecipeGui rec = new RecipeGui( masterPanel, Integer.parseInt( button.getName() ) ) ;
	 	    		cardLayout.show(  masterPanel, "recipe");
	 	    	}
	 	    });
   	 			button.setHorizontalAlignment(SwingConstants.LEFT);
   	 			button.setForeground(Color.BLUE);
   	 			button.setOpaque(false);
   	 			button.setContentAreaFilled(false);
   	 			button.setBorderPainted(false);
   	 			panel.add( button );
   	 		}	
   	 	}
	    else
	    {
	    	JButton button = new JButton( "No Results" );
	 		button.setName( "No matching results" );
	 		button.setHorizontalAlignment(SwingConstants.LEFT);
	 		button.setOpaque(false);
	 		button.setContentAreaFilled(false);
	 		button.setBorderPainted(false);
	 		panel.add( button );
	    }
	    
	    scrollPane.setViewportView( panel );
	}
	
	public JPanel getRPanel()
	{
		return panel;
	}
}