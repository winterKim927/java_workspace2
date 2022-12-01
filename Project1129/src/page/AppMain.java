package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	//db 관련정보
	FileInputStream fis;
	Properties prop; //Map의 손자
	String driver, url2, user, pass;
	Connection con;
	boolean login = false;
	
	public AppMain() {
		loadInfo();
		connect();
		
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
		pages.add(new MemberPage(this));
		pages.add(new LoginPage(this));
		pages.add(new NoticePage());
		for (int i = 0; i < pages.size(); i++) {
			p_center.add(pages.get(i));
			pages.get(i).setVisible(false);
		}
		pages.get(2).setVisible(true);
		
		add(p_center);
		
		setSize(800,500);
		setVisible(true);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				release(con);
				System.exit(0);
			}
		});
	}
	
	public void checkLogin(int index) {
		if(index == 1 || index == 2 || login) {
			showHide(index);
		} else {
			JOptionPane.showMessageDialog(this, "로그인하세요");
		}
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
	
	public void loadInfo(){
		URL url = this.getClass().getClassLoader().getResource("res/db/db.properties");
		try {
			File file = new File(url.toURI());
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis); //파일을 인식한 Map이 된다
			driver = prop.getProperty("driver");
			url2 = prop.getProperty("url");
			user = prop.getProperty("user");
			pass = prop.getProperty("pass");
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
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
			con = DriverManager.getConnection(url2,user,pass);
			if(con!=null) {setTitle("접속성공");}
			else {setTitle("접속실패");}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void release(Connection con) {
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

	public void release(PreparedStatement pstmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		new AppMain();
	}
	
}
