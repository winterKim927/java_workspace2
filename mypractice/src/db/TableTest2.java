package db;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TableTest2 extends JFrame{
	Connection con;
	JButton bt;
	JTextField t;
	JPanel p;
	JTable table;
	JScrollPane scroll;
	public TableTest2() {
		bt = new JButton("쿼리실행");
		t = new JTextField(50);
		p = new JPanel();
		p.add(t);
		p.add(bt);
		add(p, BorderLayout.NORTH);
		
		setSize(800,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		connect();
		t.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					getData(t.getText());
				}
			}
		});
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getData(t.getText());
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "1234");
			if(con!=null) System.out.println("성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getData(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int row = rs.getRow();
			int col = rs.getMetaData().getColumnCount();
			String[][] data = new String[row][col];
			String[] colName = new String[col];
			rs.first();
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					data[i][j] = rs.getObject(j+1)+"";
					if(i == 0) {
						colName[j] = rs.getMetaData().getColumnName(j+1);
					}
				}
				rs.next();
			}
			if(scroll != null) remove(scroll);
			table = new JTable(data, colName);
			scroll = new JScrollPane(table);
			add(scroll);
			SwingUtilities.updateComponentTreeUI(this);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TableTest2();
	}
}
