package game.shooting;

import java.awt.Graphics2D;

public class BgObject extends GameObject {
	public BgObject(GamePanel gp,int x, int y, int width, int height, int velX, int velY) {
		super(gp,x, y, width, height, velX, velY);
		image = im.getImage("res/bg.png", width, height);
	}

	@Override
	public void tick() {
		this.x += this.velX;
		if(x<=-900) x=0;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, x, y, width, height, null);
		g.drawImage(image, x+900, y, width, height, null);
	}

}
