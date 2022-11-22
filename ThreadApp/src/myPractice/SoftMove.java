package myPractice;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoftMove extends JFrame{
	JPanel p;
	Thread th;
	double x = 10.0;
	double y = 10.0;
	double targetX, targetY;
	public static final double a = 0.08;
	public SoftMove() {
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, 500, 500);
				g2.setColor(Color.yellow);
				g2.fillRect(0, 0, 500, 500);
				
				g2.setColor(Color.red);
				g2.fillOval((int)x, (int)y, 30, 30);
				repaint();
			}
		};
		
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				targetX = e.getPoint().x;
				targetY = e.getPoint().y;
			}
		});
		th = new Thread() {
			public void run() {
				while (true) {
					moveBall();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
		add(p);
		

		setBounds(100,100,500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void moveBall() {
		x = x + a*(targetX - x);
		y = y + a*(targetY - y);
	}

	public static void main(String[] args) {
		new SoftMove();
	}

}
