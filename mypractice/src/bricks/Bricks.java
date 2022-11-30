package bricks;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks extends GameObject{

	public Bricks(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		super(gp, x, y, width, height, velX, velY);
	}

	@Override
	public void tick() {
		if(rect.intersects(gp.ball.rect)) {
			gp.bList.remove(this);
			gp.ball.velX = -gp.ball.velX; 
			gp.ball.velY = -gp.ball.velY; 
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}

}
