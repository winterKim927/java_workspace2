package game.shooting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Thread loopThread; // 게임엔진 역할을 수행할 루프
	BgObject bg;
	Hero hero;
	Bullet bullet;
	ArrayList<Enemy> enemyList = new ArrayList();
	ArrayList<Bullet> bulletList = new ArrayList();
	String[] enemyPath= {"res/e1.png","res/e2.png","res/e3.png"};
	boolean right,left,up,down;
	public GamePanel() {
		setPreferredSize(new Dimension(900,500));
		setBackground(Color.yellow);
		
		createBg();
		createHero();
		createEnemy();
		
		loopThread = new Thread() {
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
		loopThread.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 900, 500);
		bg.render(g2);
		hero.render(g2);
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).render(g2);
		}
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).render(g2);
		}
	}
	
	// 배경 객체 생성
	public void createBg() {
		bg = new BgObject(this, 0, 0, 900, 500, -2, 0);
	}
	
	//주인공 생성
	public void createHero() {
		hero = new Hero(this, 10,10,50,30,0,0);
	}
	
	public void createEnemy() {
		for (int i = 0; i < enemyPath.length; i++) {
			Enemy enemy = new Enemy(this, 850, 80*i, 50, 50, -1, 0, enemyPath[i]);
			enemyList.add(enemy);
		}
	}
	
	//좌우 방향처리
	public void moveX(int velX) {
		hero.velX = velX;
	}
	
	//상하 방향처리
	public void moveY(int velY) {
		hero.velY = velY;
	}
	
	public void fire() {
		bullet = new Bullet(this, hero.x+hero.width, hero.y+(hero.height/2), 10,10,10,0);
		bulletList.add(bullet);
	}
	
	
	//물리량 변화
	public void tick() {
		bg.tick();
		hero.tick();
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).tick();
		}
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).tick();
		}
	}
	
	//렌더링 처리
	public void render() {
		repaint();
	}
	
	public void gameLoop() {
		tick();
		render();
	}
}
