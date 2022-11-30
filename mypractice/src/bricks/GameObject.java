package bricks;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	int x, y, width, height, velX, velY;
	GamePanel gp;
	Rectangle rect;
	public GameObject(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		this.gp = gp;
		rect = new Rectangle(x,y,width,height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
}
