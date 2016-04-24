package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import kitt.Calculate;
import kitt.ComboItem;
import kitt.Database;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecipeGui extends KITTGUI
{
	Image img;
    Image ratingStar;
    Image ratingStarEmpty;
    
    private JTextField textRecipeSelected;
    private JTextField textCourse;
    private JTextField textCusine;
    private JTextField textDiffcuilty;
    private JTextField textPrepTime;
    private JTextField textCookTime;
    private JTextField textTotalTime;
    
    JButton btnNewIngredient;
    private JTextField txtContributor;
    private JTextField txtSource;
    
    // Nutrition facts variables
    int servingSize;
    int calories;
    int totalFat;
    int saturatedFat;
    int polyFat;
    int transFat;
    int cholesterol;
    int sodium;
    int totalCarbohydrate;
    int sugars;
    int protien;
    int dietaryFiber;
    
    JTextField lblServingSizeValue;
    JTextField lblCaloriesValue;
    JTextField lblTotalFatvaule;
    JTextField lblSaturatedFatValue;
    JTextField lblPolyFatValue;
    JTextField lblTransFatValue;
    JTextField lblCholesterolValue;
    JTextField lblSodiumValue;
    JTextField lblTotalCarbohydrateValue;
    JTextField lblDietaryFiberValue;
    JTextField lblSugarsValue;
    JTextField lblProtienValues;
    
    private ResultSet recipe;
    private ResultSet ings;
    private ResultSet ing_totals;
    private int rec_ID;
	
    private ArrayList<JTextField> values;
    
    
    protected JFrame frame;
    protected JTextField textSearch;
    protected JPanel jpPanel;
    private JPanel jpPanel_1;
    protected JPanel jpContent;
    protected CardLayout cardLayout = new CardLayout(0, 0);
    
    public RecipeGui( int id )
    {
    	this( false, (JPanel)null, id );
    }
    
    public RecipeGui( JPanel masterPanel, int id )
	{
    	this( true, masterPanel, id );
	}
    
	public RecipeGui( Boolean add, JPanel masterPanel, int id )
	{
		try 
		{
			recipe = Database.st.executeQuery( "SELECT * From Recipe WHERE rec_ID = " + id );
			recipe.next();
			
			String statement = String.format(  "SELECT * From Ing_Line WHERE rec_ID = %d", id);
			ing_totals = Database.st.executeQuery( statement );
			ResultSet totals = Database.st.executeQuery( statement );
			
			 statement = "SELECT * From Ingredient WHERE ing_id IN (";
			 while( totals.next() )
			 {
					statement += totals.getInt( 2 ) + ( ( totals.isLast() ) ? ")" : ", " );
			 }
			 
			ings = Database.st.executeQuery( statement );
			
			initialize();
			rec_ID = recipe.getInt( 1 );
			
			setValues( recipe, ings, ing_totals );
			if( add )
			{
				masterPanel.add( jpPanel_1, "recipe");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}	
	
	JComboBox<ComboItem> comboBoxServings;
	protected void addPanelData()
	{
		values = new ArrayList<JTextField>();
		
		jpPanel_1 = new JPanel();
		jpPanel_1.setLayout(null);
		
		JLabel lblRecipe = new JLabel("Recipe");
        lblRecipe.setBounds(221, 11, 86, 34);
        lblRecipe.setFont(new Font("Tahoma", Font.PLAIN, 28));
        
        textRecipeSelected = new JTextField();
        textRecipeSelected.setBounds(336, 21, 309, 29);
        textRecipeSelected.setColumns(10);
        
        JLabel lblCourse = new JLabel("Course ");
        lblCourse.setBounds(221, 72, 50, 14);
        
        textCourse = new JTextField();
        textCourse.setBounds(290, 68, 86, 20);
        textCourse.setColumns(10);
        
        textCusine = new JTextField();
        textCusine.setBounds(290, 94, 86, 20);
        textCusine.setColumns(10);
        
        textDiffcuilty = new JTextField();
        textDiffcuilty.setBounds(290, 120, 86, 20);
        textDiffcuilty.setColumns(10);
        
        textPrepTime = new JTextField();
        textPrepTime.setBounds(517, 68, 65, 20);
        textPrepTime.setColumns(10);
        
        textCookTime = new JTextField();
        textCookTime.setBounds(517, 94, 65, 20);
        textCookTime.setColumns(10);
        
        textTotalTime = new JTextField();
        textTotalTime.setBounds(517, 120, 65, 20);
        textTotalTime.setColumns(10);
        
        JLabel lblPrepTime = new JLabel("Prep Time");
        lblPrepTime.setBounds(443, 71, 65, 14);
        
        JLabel lblCookTime = new JLabel("Cook Time");
        lblCookTime.setBounds(443, 97, 67, 14);
        
        JLabel lblTotalTime = new JLabel("Total Time ");
        lblTotalTime.setBounds(443, 123, 70, 14);
        
        comboBoxServings = new JComboBox<ComboItem>();
        comboBoxServings.setBackground( Color.WHITE );
        comboBoxServings.setBounds(311, 176, 50, 22);
        for( int i = 1; i < 11; i++ )
        {
        	comboBoxServings.addItem( new ComboItem( String.valueOf( i ) ) );
        }
        comboBoxServings.setSelectedIndex( 0 );
        
        comboBoxServings.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	updateNutritionLabel();
            }
        });
        
        JLabel lblSettings = new JLabel("Servings");
        lblSettings.setBounds(251, 180, 65, 14);
        
        JLabel lblPersionalRating = new JLabel("Persional Rating");
        lblPersionalRating.setBounds(390, 180, 103, 14);
        
        addNutritionLabel();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(248, 227, 706, 415);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(4, 339, 234, 303);
        
        JLabel lblNewLabel_3 = new JLabel("Cuisine ");
        lblNewLabel_3.setBounds(221, 98, 50, 14);
        
        JLabel lblNewLabel_4 = new JLabel("Difficuilty");
        lblNewLabel_4.setBounds(221, 123, 65, 14);
        
        txtContributor = new JTextField();
        txtContributor.setBounds(674, 83, 267, 20);
        txtContributor.setColumns(10);
        
        txtSource = new JTextField();
        txtSource.setBounds(674, 135, 267, 20);
        txtSource.setColumns(10);
        
        JLabel lblContributor = new JLabel("Contributor");
        lblContributor.setBounds(674, 63, 81, 14);
        
        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(674, 115, 55, 14);
                        
        txtpnIngredients = new JTextPane();
        txtpnIngredients.setEditable(false);
        scrollPane_1.setViewportView(txtpnIngredients);
        
        txtpnRecipe = new JTextPane();
        txtpnRecipe.setEditable(false);
        txtpnRecipe.setText("Recipe");
        scrollPane.setViewportView(txtpnRecipe);
        
        JButton btnEditRecipe = new JButton("Edit Recipe");
		btnEditRecipe.setBounds(762, 21, 109, 29);
		jpPanel_1.add(btnEditRecipe);
		
		JButton btnSaveRecipe = new JButton("Save Recipe");
		btnSaveRecipe.setVisible( false );
		btnSaveRecipe.setBounds(685, 21, 117, 29);
		jpPanel_1.add(btnSaveRecipe);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVisible( false );
		jpPanel_1.add(btnCancel);
		btnCancel.setBounds(824, 21, 91, 29);
		
		btnFavorite = new JButton("Favorite");
		btnFavorite.setBounds(762, 168, 138, 48);
		jpPanel_1.add(btnFavorite);
        
		addButtonListeners( btnEditRecipe, btnSaveRecipe, btnCancel, btnFavorite );
		
        jpPanel_1.add(scrollPane_1);
        jpPanel_1.add(scrollPane);
        jpPanel_1.add(lblNewLabel_4);
        jpPanel_1.add(lblNewLabel_3);
        jpPanel_1.add(lblRecipe);
        jpPanel_1.add(lblCourse);
        jpPanel_1.add(lblSettings);
        jpPanel_1.add(textCourse);
        jpPanel_1.add(textCusine);
        jpPanel_1.add(textDiffcuilty);
        jpPanel_1.add(comboBoxServings);
        jpPanel_1.add(lblPersionalRating);
        jpPanel_1.add(lblCookTime);
        jpPanel_1.add(lblPrepTime);
        jpPanel_1.add(lblTotalTime);
        jpPanel_1.add(textTotalTime);
        jpPanel_1.add(textCookTime);
        jpPanel_1.add(textPrepTime);
        jpPanel_1.add(txtSource);
        jpPanel_1.add(txtContributor);
        jpPanel_1.add(lblContributor);
        jpPanel_1.add(lblSource);
        jpPanel_1.add(textRecipeSelected);
        
        
        values.add( textRecipeSelected );
		values.add( textDiffcuilty );
        values.add( textPrepTime );
        values.add( textCookTime );
        values.add( textTotalTime );
        values.add( lblServingSizeValue );
        //rating
		values.add( textCourse );
        values.add( textCusine );
        //values.add( txtpnRecipe );
        values.add( txtContributor );
        values.add( txtSource );
        
        //values.add( txtpnIngredients );
        values.add( lblTotalFatvaule );
        values.add( lblSaturatedFatValue );
        values.add( lblPolyFatValue );
        values.add( lblTransFatValue );
        values.add( lblDietaryFiberValue );
        values.add( lblSugarsValue );
        values.add( lblCholesterolValue );
        values.add( lblSodiumValue );
        values.add( lblTotalCarbohydrateValue );
        values.add( lblProtienValues );
        values.add( lblCaloriesValue );
        
	}
	
	private void addButtonListeners(JButton btnEditRecipe, JButton btnSaveRecipe, JButton btnCancel,
			JButton btnFavorite)
	{
		btnEditRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSaveRecipe.setVisible( true );
				btnCancel.setVisible( true );
				btnEditRecipe.setVisible( false );
				for( int i = 0; i < values.size(); i++ )
				{
					values.get( i ).setEditable( true );
					if( i > 9 )
					{
						values.get( i ).setBorder( new LineBorder( Color.BLACK ) );
					}
				}
				txtpnRecipe.setEditable( true );
				txtpnIngredients.setEditable( true );
				jpPanel.updateUI();
			}
		});
		
		btnSaveRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(  JOptionPane.showConfirmDialog( frame, "Press yes to save changes", "Save Changes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
				{
					saveRecipe();
					btnCancel.doClick();
					JOptionPane.showMessageDialog( frame, "Saved Recipe Successfully");
				}
			}
		});
		
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSaveRecipe.setVisible( false );
				btnCancel.setVisible( false );
				btnEditRecipe.setVisible( true );
				for( int i = 0; i < values.size(); i++ )
				{
					values.get( i ).setEditable( false );
					if( i > 9 )
					{
						values.get( i ).setBorder( null );
					}
				}
				txtpnRecipe.setEditable( false );
				txtpnIngredients.setEditable( false );
				jpPanel.updateUI();
			}
		});
		
		btnEditRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSaveRecipe.setVisible( true );
				btnCancel.setVisible( true );
				btnEditRecipe.setVisible( false );
				for( JTextField value : values )
				{
					value.setEditable( true );
				}
			}
		});
		
		btnFavorite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if( btnFavorite.getText().contains( "Add" ) )
				{
					JOptionPane.showMessageDialog( frame, "Added to favorites" );
					try
					{
						Database.st.executeUpdate( "UPDATE Recipe SET favorite = 'favorite' WHERE rec_ID = " + rec_ID );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					btnFavorite.setText( "Unfavorite" );
				}
				else
				{
					JOptionPane.showMessageDialog( frame, "Removed from favorites" );
					try
					{
						Database.st.executeUpdate( "UPDATE Recipe SET favorite = null WHERE rec_ID = " + rec_ID );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					btnFavorite.setText( "Add to Favorites" );
				}
			}
		});
	}

	JTextPane txtpnIngredients;
	JTextPane txtpnRecipe;
	JButton btnFavorite;
	
	double totfat = 0.0;
	double satfat = 0.0;
	double monofat = 0.0;//No Monofat
	double polyfat = 0.0;
	double transfat = 0.0;
	double chol = 0;
	private double cal = 0l;
	double sod = 0;
	double carb = 0.0;
	double fiber = 0.0;
	double sugar = 0.0;
	double protein = 0.0;
	
	private void setValues( ResultSet recipe, ResultSet ingsOrig, ResultSet ing_totals )
	{
		ResultSet ings = ingsOrig;       
		ResultSet totals = ing_totals;
		
		for( JTextField value : values )
		{
			value.setEditable( false );
		}
		
		try
		{
			int j = 0;
			
			for( int i = 2; i <= recipe.getMetaData().getColumnCount(); i++ )
			{
				if( i != 11 )
				{
					if( i == 8 )
					{
						addStars( recipe.getString( i ) == null ? 0 : recipe.getInt( i ) );
					}
					else
					{
						String value = recipe.getString( i ) == null ? "No data" : recipe.getString( i );
						values.get( j ).setText( value );
						j++;
					}
				}
			}
			
			btnFavorite.setText( recipe.getString( 14 ) == null ? "Add to Favorites" : "Unfavorite" );
			
			txtSource.updateUI();			
			
			String[] instructions = recipe.getString( 11 ).split( "</div>\r\n\r\n<div>&nbsp;</div>\r\n\r\n<div>" );
			String instr = "";
			for( int i = 0; i < instructions.length; i++ )
			{
				instr += "\n\u2022 Step " + ( i + 1 ) + ". " + instructions[i].replaceAll( "</div>\r\n\r\n<div>&nbsp;</div>", "").replaceAll( "&nbsp;", "").replaceAll( "<div>", "" ).replaceAll( "</div>", "" ) + "\n";
			}
			txtpnRecipe.setText( instr );
			txtpnRecipe.updateUI();
			
			String ingredients = "";
			while( ings.next() && totals.next() )
			{
				ingredients += ings.getString( 2 ) + " " + totals.getString( 3 ) + " " + totals.getString( 4 ) + "\n";
				
				double ratio = (new Calculate( totals.getDouble( 3 ), totals.getString( 4 ), ings.getDouble( 3 ), ings.getString( 4 ) ) ).getServing();
				cal += ings.getString( 5 ) == null ? 0 : ings.getDouble( 5 ) * ratio;
				totfat +=  ings.getString( 6 ) == null ? 0 : ings.getDouble( 6 ) * ratio;
				satfat += ings.getString( 7 ) == null ? 0 : ings.getDouble( 7 ) * ratio;
				monofat += ings.getString( 8 ) == null ? 0 : ings.getDouble( 8 ) * ratio;
				polyfat += ings.getString( 9 ) == null ? 0 : ings.getDouble( 9 ) * ratio;
				transfat +=ings.getString( 10 ) == null ? 0 : ings.getDouble( 10 ) * ratio;
				chol += ings.getString( 11 ) == null ? 0 : ings.getDouble( 11 ) * ratio;
				sod += ings.getString( 12 ) == null ? 0 : ings.getDouble( 12 ) * ratio;
				carb += ings.getString( 13 ) == null ? 0 : ings.getDouble( 13 ) * ratio;
				fiber += ings.getString( 14 ) == null ? 0 : ings.getDouble( 14 ) * ratio;
				sugar += ings.getString( 15 ) == null ? 0 : ings.getDouble( 15 ) * ratio;
				protein += ings.getString( 16 ) == null ? 0 : ings.getDouble( 16 ) * ratio;
			}
			
			txtpnIngredients.setText( ingredients );
			txtpnIngredients.updateUI();
			updateNutritionLabel();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateNutritionLabel()
	{
		lblTotalFatvaule.setText( String.format( "%.2fg", totfat * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblSaturatedFatValue.setText( String.format( "%.2fg",satfat * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblPolyFatValue.setText( String.format( "%.2fg", polyfat * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblTransFatValue.setText( String.format( "%.2fg",transfat * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblDietaryFiberValue.setText( String.format( "%.2fg", fiber * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblSugarsValue.setText( String.format( "%.2fg", sugar * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblCholesterolValue.setText( String.format( "%.2fmg", chol * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblSodiumValue.setText( String.format( "%.2fmg", sod * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) )  );
        lblTotalCarbohydrateValue.setText( String.format( "%.2fg", carb * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblProtienValues.setText( String.format( "%.2fg", protein * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
        lblCaloriesValue.setText( String.format( "%.2fg", cal * Double.parseDouble( comboBoxServings.getSelectedItem().toString() ) ) );
	}
	
	public double getCalories()
	{
		return cal;
	}
	
	private int saveRecipe()
	{
		int result = 0;
		try
		{
			String statement = "UPDATE Recipe SET ";
			statement += String.format( "rec_name = %s ", values.get( 0 ).getText().contains( "No data" ) || values.get( 0 ).getText() == null ? "null" : "'" + values.get( 0 ).getText() ) + "', ";
			statement += String.format( "difficulty = %s ",  values.get( 1 ).getText().contains( "No data" ) || values.get( 1 ).getText() == null ? "null" : "'" + values.get( 1 ).getText() ) + "', ";
			statement += String.format( "prep_time = %s ",  values.get( 2 ).getText().contains( "No data" ) || values.get( 2 ).getText() == null ? "null" : values.get( 2 ).getText() ) + ", ";
			statement += String.format( "cook_time = %s ",  values.get( 3 ).getText().contains( "No data" ) || values.get( 3 ).getText() == null ? "null" : values.get( 3 ).getText() ) + ", ";
			statement += String.format( "total_time = %s ",  values.get( 4 ).getText().contains( "No data" ) || values.get( 4 ).getText() == null ? "null" : values.get( 4 ).getText() ) + ", ";
			statement += String.format( "servings = %s ",  values.get( 5 ).getText().contains( "No data" ) || values.get( 5 ).getText() == null ? "null" : values.get( 5 ).getText() ) + ", ";
			//statement += String.format( "personal_rating = %s, ",  values.get( 6 ).getText().contains( "No data" ) || values.get( 6 ).getText() == null ? "null" : values.get( 6 ).getText() ) + ", ";
			statement += String.format( "course = %s ",  values.get( 6 ).getText().contains( "No data" ) || values.get( 7 ).getText() == null ? "null" : "'" + values.get( 6 ).getText() ) + "', ";
			statement += String.format( "cuisine = %s ",  values.get( 7 ).getText().contains( "No data" ) || values.get( 8 ).getText() == null ? "null" : "'" + values.get( 7 ).getText() ) + "', ";
			
			String instructions = txtpnRecipe.getText().replaceAll( "\n\n", "</div>\r\n\r\n<div>&nbsp;</div>\r\n\r\n<div>")
					.replaceAll( "^\n\u2022 Step [0-9].", " ")
					.replaceAll( "#", "</div>\r\n\r\n<div>&nbsp;</div>")
					.replaceAll( "\\^", "&nbsp;" ).replaceAll( "\\*", "<div>" )
					.replaceAll( "~", "</div>" ) ;
			
			statement += String.format( "instructions = %s, ",  instructions );
			
			statement += String.format( "contributor = %s ",  values.get( 8 ).getText().contains( "No data" ) || values.get( 9 ).getText() == null ? "null, " : "'" + values.get( 8 ).getText() ) + "', ";
			statement += String.format( "source = %s ",  values.get( 9 ).getText().contains( "No data" ) || values.get( 10 ).getText() == null ? "null, " : "'" + values.get( 9 ).getText() ) + "', ";
			
			statement += " WHERE rec_ID = " + rec_ID;
			result =  Database.st.executeUpdate( statement );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			
		}
		
		return result;
	}
	
	private void addStars( int stars )
	{
		JButton btnStar1 = new JButton("");
        btnStar1.setBounds(485, 166, 33, 32);
        btnStar1.setBorderPainted(false);
        btnStar1.setContentAreaFilled(false);
        ratingStar = new ImageIcon(this.getClass().getResource("/ratingStar.png")).getImage();
        ratingStarEmpty = new ImageIcon(this.getClass().getResource("/ratingStarEmpty.png"))
                .getImage();
        btnStar1.setIcon(new ImageIcon(ratingStar));
        btnStar1.setBackground(Color.WHITE);
        
        JButton btnStar2 = new JButton("");
        btnStar2.setBounds(524, 166, 41, 32);
        btnStar2.setContentAreaFilled(false);
        btnStar2.setBorderPainted(false);
        btnStar2.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar3 = new JButton("");
        btnStar3.setBounds(571, 166, 35, 32);
        btnStar3.setContentAreaFilled(false);
        btnStar3.setBorderPainted(false);
        btnStar3.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar5 = new JButton("");
        btnStar5.setBounds(651, 166, 41, 32);
        btnStar5.setContentAreaFilled(false);
        btnStar5.setBorderPainted(false);
        btnStar5.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar4 = new JButton("");
        btnStar4.setBounds(612, 166, 33, 32);
        btnStar4.setContentAreaFilled(false);
        btnStar4.setBorderPainted(false);
        btnStar4.setIcon(new ImageIcon(ratingStar));
        
        jpPanel_1.add(btnStar1);
        jpPanel_1.add(btnStar2);
        jpPanel_1.add(btnStar3);
        jpPanel_1.add(btnStar4);
        jpPanel_1.add(btnStar5);
        
        btnStarActionListner(btnStar1, btnStar2, btnStar3, btnStar5, btnStar4);
        
        switch( stars )
        {
        case 1:
        	btnStar1.doClick();
        	break;
        case 2:
        	btnStar2.doClick();
        	break;
        case 3:
        	btnStar3.doClick();
        	break;
        case 4:
        	btnStar4.doClick();
        	break;
        case 5:
        	btnStar5.doClick();
        	break;
        default:
        	btnStar1.setIcon(new ImageIcon(ratingStarEmpty));
        	btnStar2.setIcon(new ImageIcon(ratingStarEmpty));
            btnStar3.setIcon(new ImageIcon(ratingStarEmpty));
            btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
            btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
        	break;
        }
	}
	
	private void btnStarActionListner(JButton btnStar1, JButton btnStar2, JButton btnStar3,
            JButton btnStar5, JButton btnStar4)
    {
        btnStar1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnStar1.setIcon(new ImageIcon(ratingStar));
                btnStar2.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar3.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                try 
                {
					Database.st.executeUpdate( "UPDATE Recipe SET personal_rating = 1 WHERE rec_ID = " + rec_ID );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnStar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnStar1.setIcon(new ImageIcon(ratingStar));
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                try 
                {
					Database.st.executeUpdate( "UPDATE Recipe SET personal_rating = 2 WHERE rec_ID = " + rec_ID );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnStar3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnStar1.setIcon(new ImageIcon(ratingStar));
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                try 
                {
					Database.st.executeUpdate( "UPDATE Recipe SET personal_rating = 3 WHERE rec_ID = " + rec_ID );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnStar4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnStar1.setIcon(new ImageIcon(ratingStar));
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStar));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                try 
                {
					Database.st.executeUpdate( "UPDATE Recipe SET personal_rating = 4 WHERE rec_ID = " + rec_ID );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnStar5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	btnStar1.setIcon(new ImageIcon(ratingStar));
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStar));
                btnStar5.setIcon(new ImageIcon(ratingStar));
                try 
                {
					Database.st.executeUpdate( "UPDATE Recipe SET personal_rating = 5 WHERE rec_ID = " + rec_ID );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
	
	private void addNutritionLabel()
	{
		 JPanel jpNutritionFacts = new JPanel();
	     jpNutritionFacts.setBounds(4, 11, 196, 317);
	     jpNutritionFacts.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	     jpNutritionFacts.setBackground(Color.WHITE);
	     
	     JTextPane txtpnNutritionFacts = new JTextPane();
	     txtpnNutritionFacts.setFont(new Font("Tahoma", Font.BOLD, 18));
	     txtpnNutritionFacts.setText("Nutrition Facts");
	        
	        JLabel txtpnTest = new JLabel();
	        txtpnTest.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnTest.setText("Serving Size");
	        
	        JLabel txtpnTotalFat = new JLabel();
	        txtpnTotalFat.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnTotalFat.setText("Total Fat");
	        
	        JLabel txtpnSaturatedFat = new JLabel();
	        txtpnSaturatedFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnSaturatedFat.setText("Saturated Fat");
	        
	        JLabel txtpnTransFat = new JLabel();
	        txtpnTransFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnTransFat.setText("Trans Fat");
	        
	        JLabel txtpnCholesterol = new JLabel();
	        txtpnCholesterol.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnCholesterol.setText("Cholesterol");
	        
	        JLabel txtpnSodium = new JLabel();
	        txtpnSodium.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnSodium.setText("Sodium");
	        
	        JLabel txtpnTotalCarbohydrate = new JLabel();
	        txtpnTotalCarbohydrate.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnTotalCarbohydrate.setText("Total Carbohydrate");
	        
	        JLabel txtpnDietaryFiber = new JLabel();
	        txtpnDietaryFiber.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnDietaryFiber.setText("Dietary Fiber");
	        
	        JLabel txtpnSugars = new JLabel();
	        txtpnSugars.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnSugars.setText("Sugars");
	        
	        JLabel lblProtien = new JLabel();
	        lblProtien.setFont(new Font("Tahoma", Font.BOLD, 11));
	        lblProtien.setText("Protien");
	        
	        JLabel lblCalories = new JLabel( "Calories" );
	        
	        JLabel lblPolyFat = new JLabel( "Poly Fat" );
	        lblPolyFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblTotalFatvaule = new JTextField();
	        lblTotalFatvaule.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblTotalFatvaule.setBorder( null );
	        lblTotalFatvaule.setBackground(Color.WHITE);
	        
	        lblSaturatedFatValue = new JTextField();
	        lblSaturatedFatValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblSaturatedFatValue.setBorder( null );
	        lblSaturatedFatValue.setBackground(Color.WHITE);
	        lblSaturatedFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblPolyFatValue = new JTextField();
	        lblPolyFatValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblPolyFatValue.setBorder( null );
	        lblPolyFatValue.setBackground(Color.WHITE);
	        lblPolyFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblTransFatValue = new JTextField();
	        lblTransFatValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblTransFatValue.setBorder( null );
	        lblTransFatValue.setBackground(Color.WHITE);
	        lblTransFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblDietaryFiberValue = new JTextField();
	        lblDietaryFiberValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblDietaryFiberValue.setBorder( null );
	        lblDietaryFiberValue.setBackground(Color.WHITE);
	        lblDietaryFiberValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblSugarsValue = new JTextField();
	        lblSugarsValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblSugarsValue.setBorder( null );
	        lblSugarsValue.setBackground(Color.WHITE);
	        lblSugarsValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblCholesterolValue = new JTextField();
	        lblCholesterolValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblCholesterolValue.setBorder( null );
	        lblCholesterolValue.setBackground(Color.WHITE);
	        
	        lblSodiumValue = new JTextField();
	        lblSodiumValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblSodiumValue.setBorder( null );
	        lblSodiumValue.setBackground(Color.WHITE);
	        
	        lblTotalCarbohydrateValue = new JTextField();
	        lblTotalCarbohydrateValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblTotalCarbohydrateValue.setBorder( null );
	        lblTotalCarbohydrateValue.setBackground(Color.WHITE);
	        
	        lblProtienValues = new JTextField();
	        lblProtienValues.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblProtienValues.setBorder( null );
	        lblProtienValues.setBackground(Color.WHITE);
	        
	        lblCaloriesValue = new JTextField();
	        lblCaloriesValue.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblCaloriesValue.setBorder( null );
	        lblCaloriesValue.setBackground(Color.WHITE);
	        
	        lblServingSizeValue = new JTextField();
	        lblServingSizeValue.setBorder( null );
	        lblServingSizeValue.setBackground(Color.WHITE);
	        
	        GroupLayout gl_jpNutritionFacts = new GroupLayout(jpNutritionFacts);
	        gl_jpNutritionFacts.setHorizontalGroup(
	        	gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        				.addComponent(txtpnNutritionFacts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        					.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        						.addComponent(lblCalories)
	        						.addComponent(txtpnTotalFat, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        							.addContainerGap()
	        							.addComponent(txtpnSaturatedFat))
	        						.addComponent(txtpnTest)
	        						.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        							.addContainerGap()
	        							.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        								.addComponent(lblPolyFat)
	        								.addComponent(txtpnTransFat)))
	        						.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        							.addContainerGap()
	        							.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        								.addComponent(lblProtien)
	        								.addComponent(txtpnSugars)
	        								.addComponent(txtpnDietaryFiber)))
	        						.addComponent(txtpnTotalCarbohydrate)
	        						.addComponent(txtpnSodium)
	        						.addComponent(txtpnCholesterol))
	        					.addGap(2)
	        					.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        							.addComponent(lblProtienValues, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblSodiumValue, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblDietaryFiberValue, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblTotalCarbohydrateValue, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblTransFatValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblCholesterolValue, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblSugarsValue, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblPolyFatValue, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
	        						.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        							.addComponent(lblServingSizeValue, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	        							.addComponent(lblSaturatedFatValue, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblTotalFatvaule, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
	        							.addComponent(lblCaloriesValue, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))))
	        			.addContainerGap())
	        );
	        gl_jpNutritionFacts.setVerticalGroup(
	        	gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        			.addComponent(txtpnNutritionFacts, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        				.addComponent(lblServingSizeValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(txtpnTest, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblCalories)
	        				.addComponent(lblCaloriesValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        				.addComponent(txtpnTotalFat, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lblTotalFatvaule, 0, 0, Short.MAX_VALUE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(txtpnSaturatedFat)
	        				.addComponent(lblSaturatedFatValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblPolyFat, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lblPolyFatValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(txtpnTransFat)
	        				.addComponent(lblTransFatValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        					.addComponent(txtpnCholesterol)
	        					.addGap(12)
	        					.addComponent(txtpnSodium))
	        				.addGroup(gl_jpNutritionFacts.createSequentialGroup()
	        					.addComponent(lblCholesterolValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(lblSodiumValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(txtpnTotalCarbohydrate)
	        				.addComponent(lblTotalCarbohydrateValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(txtpnDietaryFiber)
	        				.addComponent(lblDietaryFiberValue, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	        				.addComponent(txtpnSugars)
	        				.addComponent(lblSugarsValue, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(lblProtienValues, 0, 0, Short.MAX_VALUE)
	        				.addComponent(lblProtien, Alignment.LEADING))
	        			.addContainerGap(32, Short.MAX_VALUE))
	        );
	        jpNutritionFacts.setLayout(gl_jpNutritionFacts);
	        
	        jpPanel_1.add(jpNutritionFacts);
	}
}
