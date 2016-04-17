package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Gui {
    
    Image img;
    
    JPanel firstPage = new JPanel();
    JPanel recipeDisplay = new JPanel();
    
    private JFrame frame;
    private JTextField textField;
    private JTextField textCourse;
    private JTextField textCusine;
    private JTextField textDifficulty;
    private JTextField textPrepTime;
    private JTextField textCookTime;
    private JTextField textTotalTime;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui();
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
    public Gui() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        createJFrame();
        
        createFrontPage();
        
        createRecipeDisplay();
    }
    
    /**
     * 
     */
    private void createRecipeDisplay() {
        
        frame.getContentPane().add(recipeDisplay, "name_19391874059958");
        
        JLabel lblRecipe = new JLabel("Recipe:");
        lblRecipe.setFont(new Font("Tahoma", Font.PLAIN, 26));
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnNewSearch = new JButton("New Search");
        
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        textCourse = new JTextField();
        textCourse.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Cuisine:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        textCusine = new JTextField();
        textCusine.setColumns(10);
        
        JLabel lblDifficuty = new JLabel("Difficuty:");
        lblDifficuty.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        textDifficulty = new JTextField();
        textDifficulty.setColumns(10);
        
        JLabel lblPrepTime = new JLabel("Prep Time:");
        lblPrepTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JLabel lblCookTime = new JLabel("Cook Time:");
        lblCookTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JLabel lblTotalTime = new JLabel("Total Time: ");
        lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        textPrepTime = new JTextField();
        textPrepTime.setColumns(10);
        
        textCookTime = new JTextField();
        textCookTime.setColumns(10);
        
        textTotalTime = new JTextField();
        textTotalTime.setColumns(10);
        
        JLabel lblServings = new JLabel("Servings:");
        lblServings.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JComboBox comboServings = new JComboBox();
        
        JLabel lblPersonalRating = new JLabel("Personal Rating:");
        lblPersonalRating.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        JScrollPane scrollPane = new JScrollPane();
        
        JScrollPane scrollPane_1 = new JScrollPane();
        
        JButton btnSaveChanges = new JButton("Save Changes");
        
        JButton btnPrintPreview = new JButton("Print Preview");
        GroupLayout gl_recipeDisplay = new GroupLayout(recipeDisplay);
        gl_recipeDisplay.setHorizontalGroup(gl_recipeDisplay.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_recipeDisplay.createSequentialGroup()
                        .addGroup(gl_recipeDisplay.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_recipeDisplay.createSequentialGroup().addContainerGap()
                                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 215,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 38,
                                                Short.MAX_VALUE)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_recipeDisplay.createSequentialGroup().addGap(145)
                                        .addGroup(gl_recipeDisplay
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_recipeDisplay.createSequentialGroup()
                                                        .addComponent(lblRecipe,
                                                                GroupLayout.PREFERRED_SIZE, 91,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(textField,
                                                                GroupLayout.PREFERRED_SIZE, 199,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18))
                                                .addGroup(gl_recipeDisplay.createSequentialGroup()
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.TRAILING)
                                                                .addComponent(lblCourse)
                                                                .addGroup(gl_recipeDisplay
                                                                        .createParallelGroup(
                                                                                Alignment.LEADING,
                                                                                false)
                                                                        .addComponent(lblNewLabel)
                                                                        .addComponent(lblDifficuty,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(lblServings,
                                                                                Alignment.TRAILING)))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.LEADING, false)
                                                                .addComponent(comboServings, 0,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(textCourse,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        57, Short.MAX_VALUE)
                                                                .addComponent(textDifficulty,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        57, Short.MAX_VALUE)
                                                                .addComponent(textCusine,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        57, Short.MAX_VALUE))
                                                        .addGap(41)
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.LEADING)
                                                                .addComponent(lblPersonalRating,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        104,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(gl_recipeDisplay
                                                                        .createSequentialGroup()
                                                                        .addGroup(gl_recipeDisplay
                                                                                .createParallelGroup(
                                                                                        Alignment.LEADING,
                                                                                        false)
                                                                                .addComponent(
                                                                                        lblTotalTime,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        lblCookTime,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        lblPrepTime,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED)
                                                                        .addGroup(gl_recipeDisplay
                                                                                .createParallelGroup(
                                                                                        Alignment.LEADING)
                                                                                .addComponent(
                                                                                        textTotalTime,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        44,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(
                                                                                        textCookTime,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        44,
                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(
                                                                                        textPrepTime,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        44,
                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(
                                                                                ComponentPlacement.RELATED,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)))
                                                        .addPreferredGap(
                                                                ComponentPlacement.RELATED)))
                                        .addGroup(gl_recipeDisplay
                                                .createParallelGroup(Alignment.LEADING)
                                                .addComponent(btnNewSearch,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(gl_recipeDisplay
                                                        .createParallelGroup(Alignment.TRAILING,
                                                                false)
                                                        .addComponent(btnPrintPreview,
                                                                Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(btnSaveChanges,
                                                                Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)))))
                        .addContainerGap()));
        gl_recipeDisplay.setVerticalGroup(gl_recipeDisplay.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_recipeDisplay.createSequentialGroup().addContainerGap()
                        .addGroup(gl_recipeDisplay.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblRecipe, GroupLayout.PREFERRED_SIZE, 41,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNewSearch))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_recipeDisplay
                                .createParallelGroup(
                                        Alignment.TRAILING)
                                .addGroup(
                                        gl_recipeDisplay.createSequentialGroup()
                                                .addGroup(
                                                        gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.BASELINE)
                                                                .addComponent(lblCourse)
                                                                .addComponent(textCourse,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblPrepTime).addComponent(
                                                        textPrepTime, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_recipeDisplay
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_recipeDisplay.createSequentialGroup()
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.BASELINE)
                                                                .addComponent(lblNewLabel)
                                                                .addComponent(textCusine,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(
                                                                ComponentPlacement.UNRELATED)
                                                        .addComponent(lblDifficuty))
                                                .addGroup(gl_recipeDisplay.createSequentialGroup()
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.BASELINE)
                                                                .addComponent(lblCookTime)
                                                                .addComponent(textCookTime,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnSaveChanges))
                                                        .addPreferredGap(
                                                                ComponentPlacement.UNRELATED)
                                                        .addGroup(gl_recipeDisplay
                                                                .createParallelGroup(
                                                                        Alignment.BASELINE)
                                                                .addComponent(lblTotalTime)
                                                                .addComponent(textTotalTime,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnPrintPreview)))))
                                .addComponent(textDifficulty, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_recipeDisplay.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_recipeDisplay.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblServings)
                                        .addComponent(comboServings, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblPersonalRating))
                        .addGroup(gl_recipeDisplay.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_recipeDisplay.createSequentialGroup().addGap(22)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359,
                                                Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(
                                        gl_recipeDisplay.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrollPane_1,
                                                        GroupLayout.PREFERRED_SIZE, 222,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(25)))));
                                                
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setLineWrap(true);
        scrollPane_1.setViewportView(textArea_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);
        recipeDisplay.setLayout(gl_recipeDisplay);
    }
    
    /**
     * 
     */
    private void createFrontPage() {
        
        frame.getContentPane().add(firstPage, "name_19389336481818");
        firstPage.setLayout(new MigLayout("", "[grow]", "[50][100px][::100px][::100px]"));
        
        JLabel knightWriterLogo = new JLabel("");
        firstPage.add(knightWriterLogo, "cell 0 0,grow");
        
        setImage();
        knightWriterLogo.setIcon(new ImageIcon(img));
        
        JLabel lblNewLabel_1 = new JLabel("Nutrition Finder");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
        firstPage.add(lblNewLabel_1, "cell 0 1,alignx center,growy");
        
        JButton btnFeaturedRecipe = new JButton("Featured Recipe");
        btnFeaturedRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstPage.setVisible(false);
                recipeDisplay.setVisible(true);
            }
        });
        firstPage.add(btnFeaturedRecipe, "flowx,cell 0 2,growx");
        
        JButton favoriteRecipes = new JButton("Favorite Recipes");
        favoriteRecipes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstPage.setVisible(false);
                recipeDisplay.setVisible(true);
            }
        });
        firstPage.add(favoriteRecipes, "flowx,cell 0 3,growx");
        
        JButton btnMyRecipe = new JButton("My Recipe");
        btnMyRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                firstPage.setVisible(false);
                recipeDisplay.setVisible(true);
                
            }
        });
        firstPage.add(btnMyRecipe, "cell 0 3,growx");
    }
    
    /**
     * 
     */
    private void createJFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        JMenuItem mntmClsoe = new JMenuItem("Close");
        mnFile.add(mntmClsoe);
        
        JMenu mnview = new JMenu("View");
        menuBar.add(mnview);
        
        JMenuItem mntmMainPage = new JMenuItem("Main Page");
        mntmMainPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipeDisplay.setVisible(false);
                firstPage.setVisible(true);
            }
        });
        mnview.add(mntmMainPage);
        
        JMenuItem mntmRecipePage = new JMenuItem("Recipe Page");
        mnview.add(mntmRecipePage);
        
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
    }
    
    /**
     * @return
     */
    private void setImage() {
        img = new ImageIcon(this.getClass().getResource("/KnightWriterLogo.jpg")).getImage();
        
    }
}
