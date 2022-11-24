package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageManager;

public class AppMain extends JFrame {
	JPanel p_north, p_center;
	MyLabel[] menu = new MyLabel[4];;
	ImageManager im;
	String[] path = {
		"res/app/company.png","res/app/member.png","res/app/login.png","res/app/notice.png"	
	};
	ArrayList<JPanel> pages = new ArrayList<JPanel>();
	public static final int PAGE_WIDTH = 800;
	public static final int PAGE_HEIGHT = 500;
	
	public AppMain() {
		p_north = new JPanel();
		p_north.setBackground(Color.gray);
		p_north.setPreferredSize(new Dimension(800,70));
		p_center = new JPanel();
		im = new ImageManager();
		
		for (int i = 0; i < menu.length; i++) {
			menu[i] = new MyLabel(im.getIcon(path[i], 80, 60), i, this);
			p_north.add(menu[i]);
		}
		
		add(p_north, BorderLayout.NORTH);
		
		pages.add(new CompanyPage());
		pages.add(new MemberPage());
		pages.add(new LoginPage());
		pages.add(new NoticePage());
		for (int i = 0; i < pages.size(); i++) {
			p_center.add(pages.get(i));
			pages.get(i).setVisible(false);
		}
		
		add(p_center);
		
		setSize(800,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void showHide(int index) {
		for (int i = 0; i < pages.size(); i++) {
			if(i == index) {
				pages.get(i).setVisible(true);
			} else {
				pages.get(i).setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
	
}
