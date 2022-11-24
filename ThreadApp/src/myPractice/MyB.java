package myPractice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyB extends JButton{
	Sg sg;
	public MyB(int index, Sg sg) {
		this.sg = sg; 
		setBorderPainted(false);
		setPreferredSize(new Dimension(30,30));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sg.targetX = -500*index;
			}
		});
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, 30, 30);
		g.setColor(Color.white);
		g.fillOval(0, 0, 30, 30);
	}
}
