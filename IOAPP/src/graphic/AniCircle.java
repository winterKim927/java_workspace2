package graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AniCircle extends JFrame implements ActionListener{
	JButton bt;
	XCanvas p_center;
	
	public AniCircle() {
		bt = new JButton("움직이기");
		p_center = new XCanvas();
		
		add(bt, BorderLayout.NORTH);
		add(p_center);
		
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		move();
	}
	
	public void move() {
		p_center.x += 10;
		p_center.y += 10;
		
		p_center.repaint();
				
	}
	
	public static void main(String[] args) {
		new AniCircle();
	}
	

}
