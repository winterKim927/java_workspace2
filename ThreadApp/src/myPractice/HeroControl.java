package myPractice;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageManager;

public class HeroControl extends JFrame{
	JPanel p;
	String[] imgName = {"res/hero/image1.png","res/hero/image2.png","res/hero/image3.png","res/hero/image4.png","res/hero/image5.png",
			"res/hero/image6.png","res/hero/image7.png","res/hero/image8.png","res/hero/image9.png","res/hero/image10.png","res/hero/image11.png",
			"res/hero/image12.png","res/hero/image13.png","res/hero/image14.png","res/hero/image15.png","res/hero/image16.png","res/hero/image17.png",
			"res/hero/image18.png"};
	Image[] imgs;
	Image img;
	ImageManager im;
	Thread th;
	int index;
	
	public HeroControl() {
		im = new ImageManager();
		imgs = im.createImages(imgName);
		th = new Thread() {
			public void run() {
				while (true) {
					changeImage();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		};
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(img, 200, 200, 400, 400, null);
			}
		};
		
		add(p);
		th.start();
		
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void changeImage() {
		img = imgs[index];
		index++;
		if(index == imgs.length-1) {
			index = 0;
		}
		repaint();
	}
	
	public static void main(String[] args) {
		new HeroControl();
	}
}
