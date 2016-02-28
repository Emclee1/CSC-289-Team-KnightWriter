package teamknightrider.com;

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

public class UpdatedGui {
    
    Image img;
    
    private JFrame frame;
    private JTextField textSearch;
    
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
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jpMenu = new JPanel();
        
        JPanel jpContent = new JPanel();
        
        JPanel jpWelcome = new JPanel();
        
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
        
        JButton btnExport = new JButton("Export");
        jpMenu.add(btnExport, "cell 0 16,growx");
        
        JButton btnHelp = new JButton("Help");
        jpMenu.add(btnHelp, "flowx,cell 0 23");
        
        JButton btnAbout = new JButton("About");
        jpMenu.add(btnAbout, "cell 0 23,alignx right,aligny baseline");
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
        frame.getContentPane().setLayout(groupLayout);
    }
}
