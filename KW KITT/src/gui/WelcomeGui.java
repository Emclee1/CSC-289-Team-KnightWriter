package gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

///////////////////////////////////////////////////////////////////////
//
//Filename: WelcomGui.java      
//
// Description:
// Creates KITT's welcome screen
//
/////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
//
//Class: AdvancdSearchGui                              
//
// Description:
// This displays the welcome screen
//
// List of member functions--
//
// addPanelData
// addLabel
//
///////////////////////////////////////////////////////////////////////
public class WelcomeGui extends KITTGUI
{
	public WelcomeGui(){}
	
///////////////////////////////////////////////////////////////////////
//
// Function: Constructor                                  
//
// Description:
// Initializes and adds itself to the master panel
//
///////////////////////////////////////////////////////////////////////
	public WelcomeGui( JPanel masterPanel )
	{
		initialize();
		masterPanel.add( jpPanel, "welcome");
	}
	
///////////////////////////////////////////////////////////////////////
//
// Function: addLabel
//
// Description:
// Overrides the default protected addLabel to show the Welcome title
//
///////////////////////////////////////////////////////////////////////
	protected JLabel addLabel()
    {
    	return new JLabel("Welcome");
    }

///////////////////////////////////////////////////////////////////////
//
// Function: addPanelData                                    
//
// Description:
// Overrides the default protected addPanelData to display the 
// Welcome image
//
///////////////////////////////////////////////////////////////////////
	protected void addPanelData()
	{
		JPanel panel = new JPanel();
		jpPanel.add( panel );
		JLabel lblPic = new JLabel("");
        panel.add(lblPic, BorderLayout.CENTER);
        
        frame.pack();
        
        Image img;
        img = new ImageIcon( WelcomeGui.class.getResource("/food-collage.jpg") ).getImage();
        lblPic.setIcon(new ImageIcon(img.getScaledInstance( panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT ) ) );
	}
}