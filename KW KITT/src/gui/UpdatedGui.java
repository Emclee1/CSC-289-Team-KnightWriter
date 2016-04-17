package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 * @author KnightWriter
 *         
 */
public class UpdatedGui {
    
    Image img;
    Image ratingStar;
    Image ratingStarEmpty;
    
    private JFrame frame;
    private JTextField txtSearch;
    private JTextField textRecipeSelected;
    private JTextField textCourse;
    private JTextField textCusine;
    private JTextField textDiffcuilty;
    private JTextField textPrepTime;
    private JTextField textCookTime;
    private JTextField textTotalTime;
    
    JPanel jpMenu;
    JPanel jpContent;
    JPanel jpWelcome;
    JPanel jpRecipe;
    JPanel jpImport;
    
    JButton btnNewIngredient;
    private JTextField txtContributor;
    private JTextField txtSource;
    private JTextField txtImportStatus;
    private JTextField txtExportStatus;
    
    // jpRecipe Varables
    private String recipe;
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
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdatedGui window = new UpdatedGui();
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
    public UpdatedGui() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        
        // Sets the size of the main window frame
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jpMenu = new JPanel();
        
        jpContent = new JPanel();
        
        jpWelcome = new JPanel();
        
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout
                .setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addContainerGap()
                                .addComponent(jpMenu, GroupLayout.PREFERRED_SIZE, 208,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(jpContent, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(jpContent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addGroup(groupLayout.createSequentialGroup().addContainerGap()
                        .addComponent(jpMenu, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                        .addContainerGap()));
        jpMenu.setLayout(new MigLayout("", "[grow]",
                "[50:n][][][][][][15.00][][15.00][][15.00][][21.00][15.00][15.00][][][][][][][][][][]"));
                
        JLabel lblWelcomeToKITT = new JLabel("Welcome To KITT ");
        lblWelcomeToKITT.setHorizontalAlignment(SwingConstants.CENTER);
        jpMenu.add(lblWelcomeToKITT, "flowx,cell 0 0,alignx center,aligny bottom");
        
        JLabel lblVersion = new JLabel("version 1.0");
        jpMenu.add(lblVersion, "cell 0 1,alignx center,aligny top");
        
        JLabel lblEnterRecipeName = new JLabel("Enter Recipe Name");
        jpMenu.add(lblEnterRecipeName, "cell 0 3,alignx left");
        
        txtSearch = new JTextField();
        jpMenu.add(txtSearch, "flowx,cell 0 4,growx,aligny center");
        txtSearch.setColumns(10);
        
        JButton btnAdvancedSearch = new JButton("Advanced Search");
        
        jpMenu.add(btnAdvancedSearch, "flowx,cell 0 5,growx");
        
        btnNewIngredient = new JButton("New Ingredient");
        btnNewIngredient.setVisible(false);
        
        jpMenu.add(btnNewIngredient, "cell 0 12,growx");
        
        JButton btnRemoveIngredient = new JButton("Remove Ingredient");
        
        jpMenu.add(btnRemoveIngredient, "cell 0 13,grow");
        
        JButton btnImport = new JButton("Import / Export");
        
        jpMenu.add(btnImport, "cell 0 15,growx");
        
        JButton btnHelp = new JButton("Help");
        jpMenu.add(btnHelp, "flowx,cell 0 23");
        
        JButton btnAbout = new JButton("About");
        jpMenu.add(btnAbout, "cell 0 23,alignx right,aligny baseline");
        
        JButton btnSearch = new JButton("Search");
        
        jpMenu.add(btnSearch, "cell 0 4");
        
        jpContent.setLayout(new CardLayout(0, 0));
        jpContent.add(jpWelcome);
        jpWelcome.setLayout(new BorderLayout(0, 0));
        
        JLabel lblPic = new JLabel("");
        jpWelcome.add(lblPic, BorderLayout.CENTER);
        
        img = new ImageIcon(this.getClass().getResource("/food-collage.jpg")).getImage();
        lblPic.setIcon(new ImageIcon(img));
        
        Component verticalStrut_1 = Box.createVerticalStrut(100);
        jpWelcome.add(verticalStrut_1, BorderLayout.SOUTH);
        
        JLabel lblNewLabel_2 = new JLabel("Welcome");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        jpWelcome.add(lblNewLabel_2, BorderLayout.NORTH);
        
        Component horizontalStrut = Box.createHorizontalStrut(100);
        jpWelcome.add(horizontalStrut, BorderLayout.WEST);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(96);
        jpWelcome.add(horizontalStrut_1, BorderLayout.EAST);
        
        jpRecipe = new JPanel();
        jpContent.add(jpRecipe, "name_20316806206533");
        
        JLabel lblRecipe = new JLabel("Recipe");
        lblRecipe.setBounds(205, 11, 81, 34);
        lblRecipe.setFont(new Font("Tahoma", Font.PLAIN, 28));
        
        textRecipeSelected = new JTextField();
        textRecipeSelected.setBounds(290, 21, 309, 29);
        textRecipeSelected.setColumns(10);
        
        JLabel lblCourse = new JLabel("Course ");
        lblCourse.setBounds(249, 71, 37, 14);
        
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
        textPrepTime.setBounds(517, 68, 32, 20);
        textPrepTime.setColumns(10);
        
        textCookTime = new JTextField();
        textCookTime.setBounds(517, 94, 32, 20);
        textCookTime.setColumns(10);
        
        textTotalTime = new JTextField();
        textTotalTime.setBounds(517, 120, 32, 20);
        textTotalTime.setColumns(10);
        
        JLabel lblPrepTime = new JLabel("Prep Time");
        lblPrepTime.setBounds(461, 71, 47, 14);
        
        JLabel lblCookTime = new JLabel("Cook Time");
        lblCookTime.setBounds(461, 97, 49, 14);
        
        JLabel lblTotalTime = new JLabel("Total Time ");
        lblTotalTime.setBounds(461, 123, 52, 14);
        
        JComboBox comboBoxServings = new JComboBox();
        comboBoxServings.setBounds(290, 164, 29, 22);
        
        JLabel lblSettings = new JLabel("Servings");
        lblSettings.setBounds(245, 168, 41, 14);
        
        JLabel lblPersionalRating = new JLabel("Persional Rating");
        lblPersionalRating.setBounds(329, 168, 77, 14);
        
        JButton btnRandomRecipe = new JButton("Random Recipe ");
        
        jpMenu.add(btnRandomRecipe, "cell 0 7,growx");
        
        JButton btnMyFavroates = new JButton("My Favroates");
        
        jpMenu.add(btnMyFavroates, "cell 0 9,growx");
        
        JButton btnNewRecipe = new JButton("New Recipe");
        
        jpMenu.add(btnNewRecipe, "cell 0 11,growx");
        
        // Creates the star rating system
        
        JButton btnStar1 = new JButton("");
        btnStar1.setBounds(424, 154, 33, 32);
        btnStar1.setBorderPainted(false);
        btnStar1.setContentAreaFilled(false);
        ratingStar = new ImageIcon(this.getClass().getResource("/ratingStar.png")).getImage();
        ratingStarEmpty = new ImageIcon(this.getClass().getResource("/ratingStarEmpty.png"))
                .getImage();
        btnStar1.setIcon(new ImageIcon(ratingStar));
        btnStar1.setBackground(Color.WHITE);
        
        JButton btnStar2 = new JButton("");
        btnStar2.setBounds(463, 154, 41, 32);
        btnStar2.setContentAreaFilled(false);
        btnStar2.setBorderPainted(false);
        btnStar2.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar3 = new JButton("");
        btnStar3.setBounds(510, 154, 35, 32);
        btnStar3.setContentAreaFilled(false);
        btnStar3.setBorderPainted(false);
        btnStar3.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar5 = new JButton("");
        btnStar5.setBounds(590, 154, 41, 32);
        btnStar5.setContentAreaFilled(false);
        btnStar5.setBorderPainted(false);
        btnStar5.setIcon(new ImageIcon(ratingStar));
        
        JButton btnStar4 = new JButton("");
        btnStar4.setBounds(551, 154, 33, 32);
        btnStar4.setContentAreaFilled(false);
        btnStar4.setBorderPainted(false);
        btnStar4.setIcon(new ImageIcon(ratingStar));
        
        JPanel jpNutritionFacts = new JPanel();
        jpNutritionFacts.setBounds(4, 11, 196, 310);
        jpNutritionFacts.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        jpNutritionFacts.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(236, 227, 706, 415);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(4, 339, 197, 303);
        
        JLabel lblNewLabel_3 = new JLabel("Cusine ");
        lblNewLabel_3.setBounds(251, 97, 35, 14);
        
        JLabel lblNewLabel_4 = new JLabel("Difficuilty");
        lblNewLabel_4.setBounds(242, 123, 44, 14);
        
        txtContributor = new JTextField();
        txtContributor.setBounds(675, 68, 267, 20);
        txtContributor.setText("Contributor");
        txtContributor.setColumns(10);
        
        txtSource = new JTextField();
        txtSource.setBounds(675, 120, 267, 20);
        txtSource.setText("Source");
        txtSource.setColumns(10);
        
        JLabel lblContributor = new JLabel("Contributor");
        lblContributor.setBounds(675, 48, 55, 14);
        
        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(675, 100, 33, 14);
                        
        JTextPane txtpnIngredients = new JTextPane();
        txtpnIngredients.setEditable(false);
        txtpnIngredients.setText("Ingredients");
        scrollPane_1.setViewportView(txtpnIngredients);
        
        JTextPane txtpnRecipe = new JTextPane();
        txtpnRecipe.setEditable(false);
        txtpnRecipe.setText("Recipe");
        scrollPane.setViewportView(txtpnRecipe);
        
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
                                                lblServingSizeValue, GroupLayout.PREFERRED_SIZE, 6,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addComponent(lblCalories)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblCaloriesValue, GroupLayout.PREFERRED_SIZE,
                                                6, GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(lblPolyFatValue, GroupLayout.PREFERRED_SIZE,
                                                5, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addComponent(txtpnSodium, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 128,
                                                Short.MAX_VALUE)
                                        .addComponent(lblSodiumValue, GroupLayout.PREFERRED_SIZE, 6,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addComponent(txtpnTotalCarbohydrate,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 60,
                                                Short.MAX_VALUE)
                                        .addComponent(lblTotalCarbohydrateValue,
                                                GroupLayout.PREFERRED_SIZE, 6,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtpnDietaryFiber, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 106,
                                                Short.MAX_VALUE)
                                        .addComponent(lblDietaryFiberValue,
                                                GroupLayout.PREFERRED_SIZE, 5,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtpnTransFat, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 121,
                                                Short.MAX_VALUE)
                                        .addComponent(lblTransFatValue, GroupLayout.PREFERRED_SIZE,
                                                5, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addComponent(txtpnCholesterol, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 106,
                                                Short.MAX_VALUE)
                                        .addComponent(lblCholesterolValue,
                                                GroupLayout.PREFERRED_SIZE, 6,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblProtien, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 119,
                                                Short.MAX_VALUE)
                                        .addComponent(lblProtienValues, GroupLayout.PREFERRED_SIZE,
                                                6, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_jpNutritionFacts.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtpnSugars, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 133,
                                                Short.MAX_VALUE)
                                        .addComponent(lblSugarsValue, GroupLayout.PREFERRED_SIZE, 5,
                                                GroupLayout.PREFERRED_SIZE)))
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
        frame.getContentPane().setLayout(groupLayout);
        
        btnStarActionListner(btnStar1, btnStar2, btnStar3, btnStar5, btnStar4);
        jpRecipe.setLayout(null);
        jpRecipe.add(jpNutritionFacts);
        jpRecipe.add(scrollPane_1);
        jpRecipe.add(scrollPane);
        jpRecipe.add(lblNewLabel_4);
        jpRecipe.add(lblNewLabel_3);
        jpRecipe.add(lblRecipe);
        jpRecipe.add(lblCourse);
        jpRecipe.add(lblSettings);
        jpRecipe.add(textCourse);
        jpRecipe.add(textCusine);
        jpRecipe.add(textDiffcuilty);
        jpRecipe.add(comboBoxServings);
        jpRecipe.add(lblPersionalRating);
        jpRecipe.add(btnStar1);
        jpRecipe.add(lblCookTime);
        jpRecipe.add(lblPrepTime);
        jpRecipe.add(lblTotalTime);
        jpRecipe.add(textTotalTime);
        jpRecipe.add(textCookTime);
        jpRecipe.add(textPrepTime);
        jpRecipe.add(txtSource);
        jpRecipe.add(txtContributor);
        jpRecipe.add(lblContributor);
        jpRecipe.add(lblSource);
        jpRecipe.add(btnStar2);
        jpRecipe.add(btnStar3);
        jpRecipe.add(btnStar4);
        jpRecipe.add(btnStar5);
        jpRecipe.add(textRecipeSelected);
        
        jpImport = new JPanel();
        jpContent.add(jpImport, "name_625307882240");
        jpImport.setLayout(new MigLayout("", "[71px,grow]",
                "[14px][14px][][23px][14px][23px][][][][][][][][][]"));
                
        JLabel lblImport = new JLabel("Import");
        lblImport.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jpImport.add(lblImport, "cell 0 2,alignx left,aligny top");
        
        txtImportStatus = new JTextField();
        txtImportStatus.setText("Import Status");
        jpImport.add(txtImportStatus, "cell 0 3,growx");
        txtImportStatus.setColumns(10);
        
        JButton btnImportBrowse = new JButton("Browse");
        
        jpImport.add(btnImportBrowse, "cell 0 4,alignx left,aligny top");
        
        JLabel lblExport = new JLabel("Export ");
        lblExport.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jpImport.add(lblExport, "cell 0 12,alignx left,aligny top");
        
        txtExportStatus = new JTextField();
        txtExportStatus.setText("Export Status");
        txtExportStatus.setColumns(10);
        jpImport.add(txtExportStatus, "cell 0 13,growx");
        
        JButton btnExportBrowse = new JButton("Browse");
        
        jpImport.add(btnExportBrowse, "cell 0 14");
        
        importActionListner(btnImportBrowse, btnExportBrowse);
        btnMenuActionListners(btnAdvancedSearch, btnNewIngredient, btnImport, btnSearch,
                btnRandomRecipe, btnMyFavroates, btnNewRecipe, btnRemoveIngredient);
                
    }
    
    private void displayRecipeScreen() {
        jpWelcome.setVisible(false);
        jpRecipe.setVisible(true);
        btnNewIngredient.setVisible(true);
        jpImport.setVisible(false);
        
    }
    
    /**
     * 
     */
    private void displayImportScreen() {
        jpWelcome.setVisible(false);
        jpRecipe.setVisible(false);
        btnNewIngredient.setVisible(false);
        jpImport.setVisible(true);
        
    }
    
    /**
     * @param btnAdvancedSearch
     * @param btnNewIngredient
     * @param btnImport
     * @param btnSearch
     * @param btnRandomRecipe
     * @param btnMyFavroates
     * @param btnNewRecipe
     * @param btnRemoveIngredient
     */
    private void btnMenuActionListners(JButton btnAdvancedSearch, JButton btnNewIngredient,
            JButton btnImport, JButton btnSearch, JButton btnRandomRecipe, JButton btnMyFavroates,
            JButton btnNewRecipe, JButton btnRemoveIngredient) {
        btnAdvancedSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        btnNewIngredient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayImportScreen();
            }
        });
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        btnRandomRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                displayRecipeScreen();
            }
        });
        
        btnMyFavroates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRecipeScreen();
            }
        });
        
        btnNewRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRecipeScreen();
            }
        });
        
        btnRemoveIngredient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
    }
    
    /**
     * @param btnImportBrowse
     */
    private void importActionListner(JButton btnImportBrowse, JButton btnExportBrowse) {
        btnImportBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(jpImport);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtImportStatus.setText("Selected file: " + selectedFile.getAbsolutePath());
                }
                
            }
        });
        
        btnExportBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    
    /**
     * Action listeners for rating buttons
     * 
     * @param btnStar1
     * @param btnStar2
     * @param btnStar3
     * @param btnStar5
     * @param btnStar4
     */
    private void btnStarActionListner(JButton btnStar1, JButton btnStar2, JButton btnStar3,
            JButton btnStar5, JButton btnStar4) {
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
    
    /**
     * Populates Nutrition Facts Label
     */
    public void populateNutritionFacts() {
        lblServingSizeValue.setText(servingSize + "g");
        lblCaloriesValue.setText(calories + "g");
        lblTotalFatvaule.setText(totalFat + "g");
        lblSaturatedFatValue.setText(saturatedFat + "g");
        lblPolyFatValue.setText(polyFat + "g");
        lblTransFatValue.setText(transFat + "g");
        lblCholesterolValue.setText(cholesterol + "g");
        lblSodiumValue.setText(sodium + "g");
        lblTotalCarbohydrateValue.setText(totalCarbohydrate + "g");
        lblDietaryFiberValue.setText(dietaryFiber + "g");
        lblSugarsValue.setText(sugars + "g");
        lblProtienValues.setText(protien + "g");
    }
    
    protected void simpleSerch() {
        txtSearch.getText();
        
    }
}
