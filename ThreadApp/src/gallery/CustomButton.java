package gallery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class CustomButton extends JButton {
	int width;
	int height;
	public CustomButton(int width, int height) {
		setBorderPainted(false);
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillOval(0, 0, width, height);
	}
}
