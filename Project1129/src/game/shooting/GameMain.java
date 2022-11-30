package game.shooting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain extends JFrame{
	GamePanel gamePanel;
	public GameMain() {
		gamePanel = new GamePanel();
		add(gamePanel);
		pack();
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch(key) {
					case KeyEvent.VK_LEFT : {gamePanel.left = true; break;}
					case KeyEvent.VK_RIGHT : {gamePanel.right = true; break;}
					case KeyEvent.VK_UP : {gamePanel.up = true; break;}
					case KeyEvent.VK_DOWN : {gamePanel.down = true; break;}
					case KeyEvent.VK_SPACE : gamePanel.fire();
				}
			}
			
			public void keyReleased (KeyEvent e) {
				int key = e.getKeyCode();
				switch(key) {
				case KeyEvent.VK_LEFT : {gamePanel.left = false; break;}
				case KeyEvent.VK_RIGHT : {gamePanel.right = false; break;}
				case KeyEvent.VK_UP : {gamePanel.up = false; break;}
				case KeyEvent.VK_DOWN : {gamePanel.down = false; break;}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new GameMain();
	}
}
