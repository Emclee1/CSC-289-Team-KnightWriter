package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeGui extends KITTGUI
{
	public WelcomeGui(){}
	
	public WelcomeGui( JPanel masterPanel )
	{
		initialize();
		masterPanel.add( jpPanel, "welcome");
	}
	
	protected JLabel addLabel()
    {
    	return new JLabel("Welcome");
    }
	
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