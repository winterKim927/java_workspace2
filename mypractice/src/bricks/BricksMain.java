package bricks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class BricksMain extends JFrame{
	GamePanel p;
	public BricksMain() {
		p = new GamePanel();
		add(p);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case 37 : p.bar.left = true; System.out.println("왼쪽누름"); break;
				case 39 : p.bar.right = true; break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case 37 : p.bar.left = false; break;
				case 39 : p.bar.right = false; break;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new BricksMain();
	}
}
