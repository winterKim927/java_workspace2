package bricks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ball extends GameObject{
	boolean left, right, up, down;
	public Ball(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		super(gp, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		rect.setLocation(x, y);
		if(x + width >= 600 || x <=0) velX = -velX;
		if(y<=0) velY = -velY;
		if(rect.intersects(gp.bar.rect)) {
			velY = -velY;
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval(x, y, width, height);
	}
}
