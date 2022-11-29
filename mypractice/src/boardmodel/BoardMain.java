package boardmodel;
//응용프로그램 개발시, 디자인영역과 데이터 및 그 처리영역(업무로직)은 서로 분리시켜 개발해야 좋다 (유지보수성=비용)

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardMain extends JFrame{ //900(150+600+150)*
	JPanel p_north; 
	JTextField t_keyword;
	JButton bt_search;
	
	JPanel p_west; 
	JTextField t_title;
	JTextField t_writer;
	JTextArea t_content;
	JButton bt_regist;
	
	JPanel p_east;
	JTextField t_title2;
	JTextField t_writer2;
	JTextArea t_content2;
	JButton bt_edit, bt_del;
	
	JTable table;
	JScrollPane scroll;
	
	Connection con;
	
	public BoardMain() {
		connect();
		
		p_north = new JPanel();
		t_keyword = new JTextField(25);
		bt_search = new JButton("검색");
		p_north.add(t_keyword);
		p_north.add(bt_search);
		add(p_north, BorderLayout.NORTH);
		
		p_west = new JPanel();
		t_title = new JTextField(12);
		t_writer = new JTextField(12);
		t_content = new JTextArea();
		bt_regist = new JButton("등록");
		p_west.setPreferredSize(new Dimension(150,500));
		t_content.setPreferredSize(new Dimension(140,150));
		
		p_west.add(t_title);
		p_west.add(t_writer);
		p_west.add(t_content);
		p_west.add(bt_regist);
		add(p_west, BorderLayout.WEST);
		
		table = new JTable(10, 6);
		scroll = new JScrollPane(table);
		add(scroll);
		
		p_east = new JPanel();
		t_title2= new JTextField(12);
		t_writer2 = new JTextField(12);
		t_content2 = new JTextArea();
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		p_east.setPreferredSize(new Dimension(150,500));
		t_content2.setPreferredSize(new Dimension(140,150));
		
		p_east.add(t_title2);
		p_east.add(t_writer2);
		p_east.add(t_content2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		add(p_east, BorderLayout.EAST);
		
		setSize(900 , 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","javase","1234");
			if(con!=null) {
				setTitle("접속완료됨");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void release() {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	public void release(PreparedStatement pstmt) {
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	public void release(PreparedStatement pstmt, ResultSet rs) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) {
		new BoardMain();
	}
}
