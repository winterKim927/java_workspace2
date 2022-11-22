package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoftMove2 extends JFrame {
	JButton bt;
	JPanel p;
	Thread thread;
	double x = 0;
	double y = 50;
	double a = 0.08;
	int targetX;
	int targetY;
	
	
	public SoftMove2() {
		bt = new JButton("이동");
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.yellow);
				g2.fillRect(0, 0, 600, 550);
				
				g2.setColor(Color.red);
				g2.fillOval((int)x, (int)y, 20, 20);
			}
		};
		
		p.setPreferredSize(new Dimension(600, 550));
		thread = new Thread() {
			public void run() {
				while (true) {
					gameLoop();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		
		setLayout(new FlowLayout());
		add(bt);
		add(p);
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				targetX = e.getPoint().x;
				targetY = e.getPoint().y;
			}
		});
	}
	public void tick() {
		x = x+a*(targetX - x);
		y = y+a*(targetY - y);
	}
	
	public void render() {
		p.repaint();
	}
	
	public void gameLoop() {
		tick();
		render();
	}

	public static void main(String[] args) {
		new SoftMove2();
	}

}
