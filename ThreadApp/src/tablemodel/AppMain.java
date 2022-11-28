package tablemodel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
// JTable에서 TableModel 사용이ㅣ 상당히 큰 비중을 차지하는데,
// 왜 사용해야하는지를 이해하기 위한 예제...(비교)
public class AppMain extends JFrame{
	JTable table;
	JScrollPane scroll; // 스크롤을 이용해야 컬럼 제목이 노출된다
	String[][] data;
	String[] column; 
	Connection con;
	public AppMain() {
		connect(); // 오라클 접속
		select(); // 레코드 가져오기
		
		table = new JTable(data,column);
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(600,400);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				release(con);
				System.exit(0);
			}
		});
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "1234");
			if(con!=null) setTitle(con+"");
			else setTitle("접속실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select* from emp";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			data = new String[total][rs.getMetaData().getColumnCount()];
			column = new String[rs.getMetaData().getColumnCount()];
		
			rs.first();
			for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = rs.getObject(j+1)+""; 
                    if(i == 0) {
                        column[j] = rs.getMetaData().getColumnName(j+1); 
                    }
                }
                rs.next();
            }
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
	
	private void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
