package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoftMove extends JFrame{
	JPanel p;
	JButton bt;
	Thread th;
	double x, y, targetX, targetY;
	public static final double a = 0.1;
	
	public SoftMove() {
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, 600, 600);
				g2.setColor(Color.yellow);
				g2.fillRect(0, 0, 600, 600);
				g2.setColor(Color.RED);
				g2.fillOval((int)x, (int)y, 40, 40);
			}
		};
		p.setBackground(Color.yellow);
		bt = new JButton("이동");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th.start();
			}
		});
		targetX = 500;
		targetY = 300;
		
		th = new Thread() {
			public void run() {
				while (true) {
					move();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		add(bt, BorderLayout.NORTH);
		add(p);
		
		setBounds(100,100,600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void move() {
		x = x+a*(targetX-x);
		y = y+a*(targetY-y);
		p.repaint();
	}
	
	public static void main(String[] args) {
		new SoftMove();
	}
}
