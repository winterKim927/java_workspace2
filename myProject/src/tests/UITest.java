package tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UITest extends JFrame{
	public static final int FRAME_WIDTH = 1500;
	public static final int FRAME_HEIGHT = 800;
	public static final int P_WEST_X = 350;
	public static final int P_WEST_Y = 800;
	JPanel p_west, p_center;
	JTextField t_search;
	MenuIcon icon;
	
	public UITest() {
		t_search = new JTextField(20);
		p_west = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, P_WEST_X, P_WEST_Y );
				g2.setColor(new Color(50,130,246));
				g2.fillRect(0, 0, P_WEST_X, 150);
			}
		};
		p_west.setPreferredSize(new Dimension(P_WEST_X, P_WEST_Y));
		p_west.add(icon = new MenuIcon());
		p_west.add(t_search);
		
		
		add(p_west, BorderLayout.WEST);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		new UITest();
	}
}
