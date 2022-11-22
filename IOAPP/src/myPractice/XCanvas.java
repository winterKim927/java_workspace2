package myPractice;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class XCanvas extends JFrame{
	JPanel p;
	
	public XCanvas() {
		p = new JPanel();
		add(p);
		
		setBounds(400,100,200,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setColor(Color color) {
		p.setBackground(color);
	}

}
