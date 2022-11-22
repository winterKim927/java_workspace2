package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WariGari extends JFrame{
	JPanel p;
	int x=0, y=100;
	Thread th;
	boolean flag = true;
	public WariGari() {
		p = new JPanel() {
			//AWT에서는 paint()를 사용하고, Swing에서는 paintComponent()를 사용한다
			//Swing에서도 paint() 호출은 가능하지만, 스윙에 최적화된 메서드가 아니다.
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, 600, 500);
				g2.setColor(Color.RED);
				g2.fillOval(x, 100, 30, 30);
			}
		};
		th = new Thread() {
			public void run() {
				while (true) {
					move();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		add(p);
		th.start();
		setBounds(100,100,600,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		if (flag) {
			x += 5;
		} else {
			x -= 5;
		}
		if(x>=560 || x<=0) {
			flag = !flag;
		} 
		p.repaint();
		//p.updateUI();
	}
	
	public static void main(String[] args) {
		new WariGari();
	}

}
