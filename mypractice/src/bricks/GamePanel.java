package bricks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Ball ball;
	Bar bar;
	Thread loopTh;
	ArrayList<Bricks> bList = new ArrayList();
	//Bricks[] bList = new Bricks[25];
	public GamePanel() {
		createBall();
		createBar();
		createBricks();
		
		setPreferredSize(new Dimension(600,700));
		setBackground(Color.yellow);
		loopTh = new Thread() {
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
		loopTh.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 600, 700);
		g2.setColor(Color.yellow);
		g2.fillRect(0, 0, 600, 700);
		ball.render(g2);
		bar.render(g2);
		for (int i = 0; i < bList.size(); i++) {
			bList.get(i).render(g2);
		}
	}
	
	public void createBall() {
		ball = new Ball(this, 250, 400, 30, 30, 7, -5);
	}
	
	public void createBar() {
		bar = new Bar(this, 250, 600, 100, 20, 10, 0);
	}
	
	public void createBricks() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Bricks br = new Bricks(this, 50+50*i, 20+20*i, 50, 20, 0, 0);
				bList.add(br);
			}
		}
	}
	
	
	
	public void gameLoop() {
		ball.tick();
		bar.tick();
		for (int i = 0; i < bList.size(); i++) {
			bList.get(i).tick();
		}
		repaint();
	}
}
