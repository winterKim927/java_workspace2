package game.shooting;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

import util.ImageManager;

public class Hero extends GameObject{
	
	public Hero(GamePanel gp, int x, int y, int width, int height, int velX, int velY) {
		super(gp, x, y, width, height, velX, velY);
		image = im.getImage("res/plane.png", width, height);
	}

	public void tick() {
		if(gp.right) x += 5;
		if(gp.left) x -= 5;
		if(gp.up) y -= 5;
		if(gp.down) y += 5;
	}
	
	//Hero는 컴포넌트가 아니므로 그려질 수 없지만 JPanel인 GamePanel의
	//Graphics2D를 넘겨받아왔으므로, Hero에서 그려지는 모든 그래픽처리는
	//모두 GamePanel에 표현된다
	//또한 java의 모든 게임 오브젝트들은 GamePanel 위에서 그려질 예정
	public void render(Graphics2D g) {
//		g.setColor(Color.red);
//		g.fillRect(x, y, width, height);
		g.drawImage(image, x, y, width, height, null);
	}
}
