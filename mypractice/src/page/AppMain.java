package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class AppMain extends JFrame{
	JPanel p_north, p_center;
	MyLabel[] bt = new MyLabel[4];
	JPanel[] pageList = new JPanel[4];
	String[] path = {"res/app/company.png","res/app/member.png","res/app/login.png","res/app/notice.png"};
	
	public AppMain() {
		p_north = new JPanel();
		p_north.setBackground(Color.gray);
		p_north.setPreferredSize(new Dimension(800, 70));
		p_center = new JPanel();
		p_center.setBackground(Color.black);
		p_center.setPreferredSize(new Dimension(800, 430));
		pageList[0] = new CompanyPage();
		pageList[1] = new MemberPage();
		pageList[2] = new LoginPage();
		pageList[3] = new NoticePage();
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new MyLabel(this, i, createIcon(i));
			p_north.add(bt[i]);
		}
		
		for (int i = 0; i < pageList.length; i++) {
			p_center.add(pageList[i]);
			pageList[i].setVisible(false);
		}
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public ImageIcon createIcon(int index) {
		Class MyClass = this.getClass();
		ImageIcon icon = null;
		Image img = null;
		URL url = null;
			url = MyClass.getClassLoader().getResource(path[index]);
			try {
				img = ImageIO.read(url).getScaledInstance(80, 70, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				e.printStackTrace();
			}
		icon = new ImageIcon(img);
		return icon;
	}
	
	public void showHide(int index) {
		for (int i = 0; i < pageList.length; i++) {
			if(i == index) {
				pageList[i].setVisible(true);
			} else {
				pageList[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}

}
