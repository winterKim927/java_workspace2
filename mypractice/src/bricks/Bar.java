package bricks;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bar extends GameObject{
	boolean left,right;
	public Bar(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		super(gp, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		if(left&&x>=0) x+= -velX;
		if(right&&x+width <= 600) x+= velX;
		rect.setLocation(x, y);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}
