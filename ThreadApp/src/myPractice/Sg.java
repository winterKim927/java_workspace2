package myPractice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sg extends JFrame{
	ArrayList<MyB> btarr = new ArrayList<MyB>();
	ArrayList<Image> imgarr = new ArrayList<Image>();
	JPanel p, p2;
	Image image;
	double x;
	int targetX;
	public static final double a = 0.08;
	Thread th;
	public Sg() {
		p2 = new JPanel();
		p2.setBackground(Color.gray);
		p2.setPreferredSize(new Dimension(500,60));
		for (int i = 0; i < 7; i++) {
			MyB bt = new MyB(i, this);
			btarr.add(bt);
			p2.add(bt);
		}
		createImage();
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				for (int i = 0; i < btarr.size(); i++) {
					g2.drawImage(imgarr.get(i), 500*i+(int)x, 0, 500, 340, Sg.this);
				}
			}
		};
		p.setBackground(Color.yellow);
		p.setPreferredSize(new Dimension(500,340));
		th = new Thread() {
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
		th.start();
		
		add(p);
		add(p2, BorderLayout.SOUTH);
		
		setSize(new Dimension(500,400));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void createImage() {
		Class myClass = this.getClass();
		for (int i = 0; i < btarr.size(); i++) {
			URL url = myClass.getClassLoader().getResource("res/shurek/img" + i + ".jpg");
			try {
				Image image = ImageIO.read(url); 
				imgarr.add(image);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void gameLoop() {
		tick();
		render();
	}
	
	public void tick() {
		x = x + a*(targetX - x);
	}
	
	public void render() {
		p.repaint();
	}
	
	public static void main(String[] args) {
		new Sg();
	}
}
