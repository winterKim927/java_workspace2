package homework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton extends JButton {
	int width;
	int height;
	int index;
	ShurekGallery shurekGallery;
	public CustomButton(ShurekGallery shurekGallery, int index, int width, int height) {
		//setBorderPainted(false);
		this.shurekGallery = shurekGallery;
		this.index = index;
		this.width = width;
		this.height = height;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shurekGallery.targetX = index*-500;
			}
		});
		setPreferredSize(new Dimension(width, height));
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.red);
		g.fillOval(0, 0, width, height);
	}
}
