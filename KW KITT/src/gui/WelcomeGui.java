package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WelcomeGui extends KITTGUI
{
	public WelcomeGui()
	{
		initialize();
	}
	
	public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WelcomeGui window = new WelcomeGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	protected JLabel addLabel()
    {
    	return new JLabel("Welcome");
    }
	
	protected void addPanelData()
	{
		JLabel lblPic = new JLabel("");
        jpPanel.add(lblPic, BorderLayout.CENTER);
        
        Image img;
        img = new ImageIcon( WelcomeGui.class.getResource("/food-collage.jpg") ).getImage();
        lblPic.setIcon(new ImageIcon(img));
	}
}