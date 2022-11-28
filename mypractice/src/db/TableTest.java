package db;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	Connection con;
	
	public TableTest() {
		setSize(800,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		connect();
		getData();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	
	public void connect() {
		String url="jdbc:oracle:thin:@localhost:1521:XE";;
		String user = "java";
		String pass = "1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con!=null) System.out.println("success");
	}
	
	public void getData() {
		String query = "select * from emp2";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			int columns = rs.getMetaData().getColumnCount();
			
			rs.last();
			int rows = rs.getRow();
			System.out.println("줄 수는 " + rows);
			System.out.println("칸 수는 " + columns);
			String[][] data = new String[rows][columns];
			String[] cName = new String[columns];
			rs.first();
			for (int i = 0; i < cName.length; i++) {
				cName[i] = rs.getMetaData().getColumnName(i+1);
			}
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					data[i][j] = rs.getObject(j+1)+"";
				}
				rs.next();
			}
			
			table = new JTable(data, cName);
			scroll = new JScrollPane(table);
			add(scroll);
			SwingUtilities.updateComponentTreeUI(TableTest.this);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void close() {
		try {
			if(con!=null)con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
}
