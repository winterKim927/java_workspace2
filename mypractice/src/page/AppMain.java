package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class AppMain extends JFrame{
	JPanel p_north, p_center;
	MyLabel[] bt = new MyLabel[4];
	JPanel[] pageList = new JPanel[4];
	String[] path = {"res/app/company.png","res/app/member.png","res/app/login.png","res/app/notice.png"};
	Connection con;
	FileInputStream fis;
	Properties props;
	String driver, url, user, pass;
	boolean login = false;
	public AppMain() {
		loadInfo();
		connect();
		p_north = new JPanel();
		p_north.setBackground(Color.gray);
		p_north.setPreferredSize(new Dimension(800, 70));
		p_center = new JPanel();
		p_center.setBackground(Color.black);
		p_center.setPreferredSize(new Dimension(800, 430));
		pageList[0] = new CompanyPage();
		pageList[1] = new MemberPage(this);
		pageList[2] = new LoginPage(this);
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
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				release();
				System.exit(0);
			}
		});
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
	
	public void loadInfo() {
		URL url2 = this.getClass().getClassLoader().getResource("res/db/db.properties");
		try {
			File file = new File(url2.toURI());
			fis = new FileInputStream(file);
			props = new Properties();
			props.load(fis);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			pass = props.getProperty("pass");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			if(con!=null) setTitle("접속성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void release() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}

}
