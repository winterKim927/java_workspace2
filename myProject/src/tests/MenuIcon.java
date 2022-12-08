package tests;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import util.ImageManager;

public class MenuIcon extends JLabel {
	ImageManager im = new ImageManager();
	public MenuIcon() {
		setPreferredSize(new Dimension(30,30));
	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(im.getImage("res/images/menu_line_icon.png"), 0, 0, 30, 30, null);
	}
	
	public void getImg(){
		
	}
}
