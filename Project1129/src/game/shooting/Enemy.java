package game.shooting;

import java.awt.Graphics2D;

public class Enemy extends GameObject{

	public Enemy(GamePanel gp, int x, int y, int width, int height, int velX, int velY, String path) {
		super(gp, x, y, width, height, velX, velY);
		image = im.getImage(path, 100, 100);
	}

	@Override
	public void tick() {
		x += velX;
		rect.setLocation(x,y);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, x, y, width, height, null);
	}

}
