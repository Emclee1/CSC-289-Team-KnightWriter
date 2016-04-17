package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import kitt.Database;

public class RecipeGui extends KITTGUI
{
	Image img;
    Image ratingStar;
    Image ratingStarEmpty;
    
    protected JFrame frame;
    protected JTextField textSearch;
    protected JPanel jpPanel;
    protected JPanel jpContent;
    protected Database db;
    
    private JTextField txtSearch;
    private JTextField textRecipeSelected;
    private JTextField textCourse;
    private JTextField textCusine;
    private JTextField textDiffcuilty;
    private JTextField textPrepTime;
    private JTextField textCookTime;
    private JTextField textTotalTime;
    
    JPanel jpMenu;
    //JPanel jpContent;
    JPanel jpWelcome;
    JPanel jpRecipe;
    JPanel jpImport;
    
    JButton btnNewIngredient;
    private JTextField txtContributor;
    private JTextField txtSource;
    
    // jpRecipe Varables
    private String course;
    private String difficulity;
    private int propTime;
    private int cookTime;
    private int totalTime;
    private String contributor;
    private String source;
    ArrayList<String> ingredients = new ArrayList<String>();
    StringBuilder instructions = new StringBuilder();
    
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
    JLabel lblServingSizeValue;
    JLabel lblCaloriesValue;
    JLabel lblTotalFatvaule;
    JLabel lblSaturatedFatValue;
    JLabel lblPolyFatValue;
    JLabel lblTransFatValue;
    JLabel lblCholesterolValue;
    JLabel lblSodiumValue;
    JLabel lblTotalCarbohydrateValue;
    JLabel lblDietaryFiberValue;
    JLabel lblSugarsValue;
    JLabel lblProtienValues;
    
    private ResultSet recipe;
    private ResultSet ings;
	
	public RecipeGui()
	{
		this( new JPanel(), 1080 );
	}
	
	public RecipeGui( JPanel masterPanel, int id )
	{
		try 
		{
			recipe = Database.st.executeQuery( "SELECT * From Recipe WHERE rec_ID = " + id );
			recipe.next();
			
			String statement = String.format(  "SELECT ing_id From Ing_Line WHERE rec_ID = %d", id);
			ings = Database.st.executeQuery( statement );
			
			 statement = "SELECT * From Ingredient WHERE ing_id IN (";
			 while( ings.next() )
				{
					statement += ings.getInt( 1 ) + ( ( ings.isLast() ) ? ")" : ", " );
				}
			 
			ings = Database.st.executeQuery( statement );
			
			initialize();
			setValues( recipe, ings );
			masterPanel.add( jpPanel, "recipe");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}	

	 
	protected void addPanelData()
	{
		jpPanel = new JPanel();
		jpPanel.setLayout(null);
		
		JLabel lblRecipe = new JLabel("Recipe");
        lblRecipe.setBounds(251, 11, 81, 34);
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
        textPrepTime.setBounds(517, 68, 48, 20);
        textPrepTime.setColumns(10);
        
        textCookTime = new JTextField();
        textCookTime.setBounds(517, 94, 48, 20);
        textCookTime.setColumns(10);
        
        textTotalTime = new JTextField();
        textTotalTime.setBounds(517, 120, 48, 20);
        textTotalTime.setColumns(10);
        
        JLabel lblPrepTime = new JLabel("Prep Time");
        lblPrepTime.setBounds(443, 71, 65, 14);
        
        JLabel lblCookTime = new JLabel("Cook Time");
        lblCookTime.setBounds(443, 97, 67, 14);
        
        JLabel lblTotalTime = new JLabel("Total Time ");
        lblTotalTime.setBounds(443, 123, 70, 14);
        
        JComboBox comboBoxServings = new JComboBox();
        comboBoxServings.setBounds(320, 176, 41, 22);
        
        JLabel lblSettings = new JLabel("Servings");
        lblSettings.setBounds(251, 180, 65, 14);
        
        JLabel lblPersionalRating = new JLabel("Persional Rating");
        lblPersionalRating.setBounds(390, 180, 103, 14);
        
        addNutritionLabel();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(236, 227, 706, 415);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(4, 339, 197, 303);
        
        JLabel lblNewLabel_3 = new JLabel("Cuisine ");
        lblNewLabel_3.setBounds(221, 98, 50, 14);
        
        JLabel lblNewLabel_4 = new JLabel("Difficuilty");
        lblNewLabel_4.setBounds(221, 123, 65, 14);
        
        txtContributor = new JTextField();
        txtContributor.setBounds(675, 68, 267, 20);
        txtContributor.setColumns(10);
        
        txtSource = new JTextField();
        txtSource.setBounds(675, 120, 267, 20);
        txtSource.setColumns(10);
        
        JLabel lblContributor = new JLabel("Contributor");
        lblContributor.setBounds(675, 48, 81, 14);
        
        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(675, 100, 55, 14);
                        
        txtpnIngredients = new JTextPane();
        txtpnIngredients.setEditable(false);
        scrollPane_1.setViewportView(txtpnIngredients);
        
        txtpnRecipe = new JTextPane();
        txtpnRecipe.setEditable(false);
        txtpnRecipe.setText("Recipe");
        scrollPane.setViewportView(txtpnRecipe);
        
        jpPanel.add(scrollPane_1);
        jpPanel.add(scrollPane);
        jpPanel.add(lblNewLabel_4);
        jpPanel.add(lblNewLabel_3);
        jpPanel.add(lblRecipe);
        jpPanel.add(lblCourse);
        jpPanel.add(lblSettings);
        jpPanel.add(textCourse);
        jpPanel.add(textCusine);
        jpPanel.add(textDiffcuilty);
        jpPanel.add(comboBoxServings);
        jpPanel.add(lblPersionalRating);
        jpPanel.add(lblCookTime);
        jpPanel.add(lblPrepTime);
        jpPanel.add(lblTotalTime);
        jpPanel.add(textTotalTime);
        jpPanel.add(textCookTime);
        jpPanel.add(textPrepTime);
        jpPanel.add(txtSource);
        jpPanel.add(txtContributor);
        jpPanel.add(lblContributor);
        jpPanel.add(lblSource);
        jpPanel.add(textRecipeSelected);
	}
	
	JTextPane txtpnIngredients;
	JTextPane txtpnRecipe;
	
	private void setValues( ResultSet recipe, ResultSet ingsOrig )
	{
		ResultSet ings = ingsOrig;
		
		try
		{
			textRecipeSelected.setText( recipe.getString( 2 ) == null ? "No data" : recipe.getString( 2 ) );
			textDiffcuilty.setText( recipe.getString( 3 ) == null ? "No data" : recipe.getString( 3 ) );
			textPrepTime.setText( recipe.getString( 4 ) == null ? "No data" : recipe.getString( 4 ) );
			textCookTime.setText( recipe.getString( 5 ) == null ? "No data" : recipe.getString( 5 ) );
			textTotalTime.setText( recipe.getString( 6 ) == null ? "No data" : recipe.getString( 6 ) );
			addStars( recipe.getString( 7 ) == null ? 0 : recipe.getInt( 7 ) );
			textCourse.setText( recipe.getString( 9 ) == null ? "No data" : recipe.getString( 9 ) );
			textCusine.setText( recipe.getString( 10 ) == null ? "No data" : recipe.getString( 10 ) );
			txtContributor.setText( recipe.getString( 12 ) == null ? "No data" : recipe.getString( 12 ) );
			txtSource.setText( recipe.getString( 13 ) == null ? "No data" : recipe.getString( 13 ) );
			txtSource.updateUI();
			
			lblServingSizeValue.setText( recipe.getString( 7 ) );
			
			String[] instructions = recipe.getString( 11 ).split( "</div>\r\n\r\n<div>&nbsp;</div>\r\n\r\n<div>" );
			String instr = "";
			for( int i = 0; i < instructions.length; i++ )
			{
				instr += "\n\u2022 Step " + ( i + 1 ) + ". " + instructions[i].replaceAll( "</div>\r\n\r\n<div>&nbsp;</div>", "").replaceAll( "&nbsp;", "").replaceAll( "<div>", "" ).replaceAll( "</div>", "" ) + "\n";
			}
			txtpnRecipe.setText( instr );
			
			String ingredients = "";
			double totfat = 0.0;
			double satfat = 0.0;
			double monofat = 0.0;//No Monofat
			double polyfat = 0.0;
			double transfat = 0.0;
			int chol = 0;
			int cal = 0;
			int sodium = 0;
			double carb = 0.0;
			double fiber = 0.0;
			double sugar = 0.0;
			double protein = 0.0;
			
			while( ings.next() )
			{
				ingredients += ings.getString( 2 ) + " " + ings.getString( 3 ) + " " + ings.getString( 4 ) + "\n";
				
				cal += ings.getString( 5 ) == null ? 0 : ings.getInt( 5 );
				totfat +=  ings.getString( 6 ) == null ? 0 : ings.getDouble( 6 );
				satfat += ings.getString( 7 ) == null ? 0 : ings.getDouble( 7 );
				monofat += ings.getString( 8 ) == null ? 0 : ings.getDouble( 8 );
				polyfat += ings.getString( 9 ) == null ? 0 : ings.getDouble( 9 );
				transfat +=ings.getString( 10 ) == null ? 0 : ings.getDouble( 10 );
				chol += ings.getString( 11 ) == null ? 0 : ings.getInt( 11 );
				sodium += ings.getString( 12 ) == null ? 0 : ings.getInt( 12 );
				carb += ings.getString( 13 ) == null ? 0 : ings.getDouble( 13 );
				fiber += ings.getString( 14 ) == null ? 0 : ings.getDouble( 14 );
				sugar += ings.getString( 15 ) == null ? 0 : ings.getDouble( 15 );
				protein += ings.getString( 16 ) == null ? 0 : ings.getDouble( 16 );
			}
			txtpnIngredients.setText( ingredients );
			
			
			lblTotalFatvaule.setText( String.format( "%.2f", totfat ) );
	        lblSaturatedFatValue.setText( String.format( "%.2f",satfat ) );
	        lblPolyFatValue.setText( String.format( "%.2f", polyfat ) );
	        lblTransFatValue.setText( String.format( "%.2f",transfat ) );
	        lblDietaryFiberValue.setText( String.format( "%.2f", fiber ) );
	        lblSugarsValue.setText( String.format( "%.2f", sugar ) );
	        lblCholesterolValue.setText( String.valueOf( chol ) );
	        lblSodiumValue.setText( String.valueOf( sodium ) );
	        lblTotalCarbohydrateValue.setText( String.format( "%.2f", carb ) );
	        lblProtienValues.setText( String.format( "%.2f", protein ) );
	        lblCaloriesValue.setText( String.valueOf( cal ) );
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        
        jpPanel.add(btnStar1);
        jpPanel.add(btnStar2);
        jpPanel.add(btnStar3);
        jpPanel.add(btnStar4);
        jpPanel.add(btnStar5);
        
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
        	break;
        }
	}
	
	
	private void btnStarActionListner(JButton btnStar1, JButton btnStar2, JButton btnStar3,
            JButton btnStar5, JButton btnStar4)
    {
        btnStar1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStar2.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar3.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                
            }
        });
        
        btnStar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                
            }
        });
        
        btnStar3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStarEmpty));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                
            }
        });
        
        btnStar4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStar));
                btnStar5.setIcon(new ImageIcon(ratingStarEmpty));
                
            }
        });
        
        btnStar5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStar2.setIcon(new ImageIcon(ratingStar));
                btnStar3.setIcon(new ImageIcon(ratingStar));
                btnStar4.setIcon(new ImageIcon(ratingStar));
                btnStar5.setIcon(new ImageIcon(ratingStar));
                
            }
        });
    }
	
	private void addNutritionLabel()
	{
		 JPanel jpNutritionFacts = new JPanel();
	     jpNutritionFacts.setBounds(4, 11, 196, 310);
	     jpNutritionFacts.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	     jpNutritionFacts.setBackground(Color.WHITE);
	     
	     JTextPane txtpnNutritionFacts = new JTextPane();
	     txtpnNutritionFacts.setFont(new Font("Tahoma", Font.BOLD, 18));
	     txtpnNutritionFacts.setText("Nutrition Facts");
	        
	        JTextPane txtpnTest = new JTextPane();
	        txtpnTest.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnTest.setText("Serving Size");
	        
	        JTextPane txtpnTotalFat = new JTextPane();
	        txtpnTotalFat.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnTotalFat.setText("Total Fat");
	        
	        JTextPane txtpnSaturatedFat = new JTextPane();
	        txtpnSaturatedFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnSaturatedFat.setText("Saturated Fat");
	        
	        JTextPane txtpnTransFat = new JTextPane();
	        txtpnTransFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnTransFat.setText("Trans Fat");
	        
	        JTextPane txtpnCholesterol = new JTextPane();
	        txtpnCholesterol.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnCholesterol.setText("Cholesterol");
	        
	        JTextPane txtpnSodium = new JTextPane();
	        txtpnSodium.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnSodium.setText("Sodium");
	        
	        JTextPane txtpnTotalCarbohydrate = new JTextPane();
	        txtpnTotalCarbohydrate.setFont(new Font("Tahoma", Font.BOLD, 11));
	        txtpnTotalCarbohydrate.setText("Total Carbohydrate");
	        
	        JTextPane txtpnDietaryFiber = new JTextPane();
	        txtpnDietaryFiber.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnDietaryFiber.setText("Dietary Fiber");
	        
	        JTextPane txtpnSugars = new JTextPane();
	        txtpnSugars.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        txtpnSugars.setText("Sugars");
	        
	        JTextPane lblProtien = new JTextPane();
	        lblProtien.setFont(new Font("Tahoma", Font.BOLD, 11));
	        lblProtien.setText("Protien");
	        
	        JLabel lblCalories = new JLabel("Calories");
	        
	        JLabel lblPolyFat = new JLabel(" Poly  Fat");
	        lblPolyFat.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblTotalFatvaule = new JLabel("g");
	        
	        lblSaturatedFatValue = new JLabel("g");
	        lblSaturatedFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblPolyFatValue = new JLabel("g");
	        lblPolyFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblTransFatValue = new JLabel("g");
	        lblTransFatValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblDietaryFiberValue = new JLabel("g");
	        lblDietaryFiberValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblSugarsValue = new JLabel("g");
	        lblSugarsValue.setFont(new Font("Tahoma", Font.PLAIN, 9));
	        
	        lblCholesterolValue = new JLabel("g");
	        
	        lblSodiumValue = new JLabel("g");
	        
	        lblTotalCarbohydrateValue = new JLabel("g");
	        
	        lblProtienValues = new JLabel("g");
	        
	        lblCaloriesValue = new JLabel("g");
	        
	        lblServingSizeValue = new JLabel("g");
	        
	        GroupLayout gl_jpNutritionFacts = new GroupLayout(jpNutritionFacts);
	        gl_jpNutritionFacts.setHorizontalGroup(gl_jpNutritionFacts
	                .createParallelGroup(Alignment.LEADING)
	                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	                                .addComponent(txtpnNutritionFacts, GroupLayout.PREFERRED_SIZE,
	                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnTest, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(
	                                                lblServingSizeValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(lblCalories)
	                                        .addPreferredGap(ComponentPlacement.RELATED)
	                                        .addComponent(lblCaloriesValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnTotalFat, GroupLayout.PREFERRED_SIZE, 63,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 113,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblTotalFatvaule))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap()
	                                        .addComponent(txtpnSaturatedFat, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 104,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblSaturatedFatValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap().addComponent(lblPolyFat)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 125,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblPolyFatValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnSodium, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 128,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblSodiumValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnTotalCarbohydrate,
	                                                GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 60,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblTotalCarbohydrateValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap()
	                                        .addComponent(txtpnDietaryFiber, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 106,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblDietaryFiberValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap()
	                                        .addComponent(txtpnTransFat, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 121,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblTransFatValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnCholesterol, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 106,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblCholesterolValue))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap()
	                                        .addComponent(lblProtien, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 119,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblProtienValues))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addContainerGap()
	                                        .addComponent(txtpnSugars, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED, 133,
	                                                Short.MAX_VALUE)
	                                        .addComponent(lblSugarsValue)))
	                        .addContainerGap()));
	        gl_jpNutritionFacts.setVerticalGroup(gl_jpNutritionFacts
	                .createParallelGroup(Alignment.LEADING)
	                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                        .addComponent(txtpnNutritionFacts, GroupLayout.PREFERRED_SIZE,
	                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnTest, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED)
	                                        .addGroup(gl_jpNutritionFacts
	                                                .createParallelGroup(Alignment.BASELINE)
	                                                .addComponent(lblCalories)
	                                                .addComponent(lblCaloriesValue)))
	                                .addComponent(lblServingSizeValue))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(txtpnTotalFat, GroupLayout.PREFERRED_SIZE, 15,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(ComponentPlacement.RELATED)
	                                        .addComponent(txtpnSaturatedFat, GroupLayout.PREFERRED_SIZE,
	                                                GroupLayout.DEFAULT_SIZE,
	                                                GroupLayout.PREFERRED_SIZE)
	                                        .addGap(2)
	                                        .addGroup(gl_jpNutritionFacts
	                                                .createParallelGroup(Alignment.BASELINE)
	                                                .addComponent(lblPolyFat).addComponent(
	                                                        lblPolyFatValue, GroupLayout.PREFERRED_SIZE,
	                                                        11, GroupLayout.PREFERRED_SIZE)))
	                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
	                                        .addComponent(lblTotalFatvaule)
	                                        .addPreferredGap(ComponentPlacement.RELATED)
	                                        .addComponent(lblSaturatedFatValue)))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(txtpnTransFat, GroupLayout.PREFERRED_SIZE,
	                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(lblTransFatValue, GroupLayout.PREFERRED_SIZE, 11,
	                                        GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(lblCholesterolValue).addComponent(txtpnCholesterol,
	                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	                                        GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(lblSodiumValue).addComponent(txtpnSodium,
	                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	                                        GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	                                .addComponent(lblTotalCarbohydrateValue, Alignment.TRAILING)
	                                .addComponent(txtpnTotalCarbohydrate, Alignment.TRAILING,
	                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	                                        GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.LEADING)
	                                .addComponent(txtpnDietaryFiber, Alignment.TRAILING,
	                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
	                                        GroupLayout.PREFERRED_SIZE)
	                                .addComponent(lblDietaryFiberValue, Alignment.TRAILING,
	                                        GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(txtpnSugars, GroupLayout.PREFERRED_SIZE,
	                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(lblSugarsValue, GroupLayout.PREFERRED_SIZE, 11,
	                                        GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(ComponentPlacement.RELATED)
	                        .addGroup(gl_jpNutritionFacts.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(lblProtien, GroupLayout.PREFERRED_SIZE,
	                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(lblProtienValues))
	                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	        jpNutritionFacts.setLayout(gl_jpNutritionFacts);
	        
	        jpPanel.add(jpNutritionFacts);
	}
}
