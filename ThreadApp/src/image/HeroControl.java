package image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageManager;

//자바에서 이미지를 얻는 방법은 번거로운 Toolkit만 있는게 아니다

public class HeroControl extends JFrame {
	JPanel p;
	Image img;
	ImageManager im;
	String[] imgName = {"res/hero/image1.png","res/hero/image2.png","res/hero/image3.png","res/hero/image4.png","res/hero/image5.png",
			"res/hero/image6.png","res/hero/image7.png","res/hero/image8.png","res/hero/image9.png","res/hero/image10.png","res/hero/image11.png",
			"res/hero/image12.png","res/hero/image13.png","res/hero/image14.png","res/hero/image15.png","res/hero/image16.png","res/hero/image17.png",
			"res/hero/image18.png"};
	Image[] images;
	Thread th;
	int index;
	
	public HeroControl() {
		//클래스에 대한 정보를 가진 클래스                                         
//		Class myClass=this.getClass();
//		Method[] methods = myClass.getMethods();
//		for(Method m: methods) {
//			//System.out.println(m.getName());
//		}
//		Package pack = myClass.getPackage();
//		System.out.println(pack);
		th = new Thread() {
			public void run() {
				while (true) {
					gameLoop();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		im = new ImageManager();
		images = im.createImages(imgName);
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.drawImage(images[index], 50, 50, 200, 200, HeroControl.this);
			}
		};
		th.start();
		add(p);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //화면 중앙에 프레임 놓기
	}
	
	public void gameLoop() {
		tick();
		render();
	}
	
	

	public void tick() {
		index++;
		if(index == 17) {
			index = 0;
		}
	}
	
	public void render() {
		repaint();
	}

	public static void main(String[] args) {
		new HeroControl();
	}
}
