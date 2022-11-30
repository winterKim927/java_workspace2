package game.shooting;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	public Bullet(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		super(gp, x, y, width, height, velX, velY);
		image = im.getImage("res/rocket.png", 10, 10);
	}

	public void tick() {
		this.x+=this.velX;
		//총알과 적군의 충돌체크
		rect.setLocation(x,y);
		collisionCheck();
	}
	
	public void collisionCheck() {
		for (int i = 0; i < gp.enemyList.size(); i++) {
			Enemy enemy = gp.enemyList.get(i);
			boolean result = rect.intersects(enemy.rect);
			if(result) {
				System.out.println("맞았어");
				gp.bulletList.remove(this);
				gp.enemyList.remove(enemy);
			}
		}
	}

	public void render(Graphics2D g) {
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(image, x, y, width, height, null);
	}

}
