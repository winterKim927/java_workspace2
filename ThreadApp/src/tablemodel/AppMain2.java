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
import javax.swing.table.TableModel;
// JTable에서 TableModel 사용이ㅣ 상당히 큰 비중을 차지하는데,
// 왜 사용해야하는지를 이해하기 위한 예제...(비교)
public class AppMain2 extends JFrame{
	JTable table;
	JScrollPane scroll; // 스크롤을 이용해야 컬럼 제목이 노출된다
	Connection con;
	TableModel model; //EMP 테이블에 대한 정보를 가진 객체
	public AppMain2() {
		connect(); // 오라클 접속
		
		table = new JTable(model = new DeptModel(this));
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
	
	public void release(PreparedStatement pstmt, ResultSet rs) {
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
		new AppMain2();
	}
}
