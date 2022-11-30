package game.shooting;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import util.ImageManager;

public abstract class GameObject{
	int x,y,width,height,velX,velY;
	Image image;
	ImageManager im;
	Rectangle rect;
	GamePanel gp;
	public GameObject(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		rect = new Rectangle(x, y, width, height);
		im = new ImageManager();
		this.gp = gp;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
}