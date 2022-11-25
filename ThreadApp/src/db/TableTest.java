package db;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import util.ImageManager;

public class TableTest extends JFrame implements ActionListener{
	JPanel p_west;
	JTextField t_id, t_name;
	JPasswordField t_pass;
	JButton bt;
	ImageManager im;
	JTable table;
	JScrollPane scroll;
	int row;
	
	//JTable에 사용할 컬럼
	String[] columnName = {"EMPNO","ENAME","JOB","MGR","HIREDATE","SAL","COMM","DEPTNO"};
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String pass="1234";
	
	Connection con;

	
	public TableTest() {
		p_west = new JPanel();
		t_id = new JTextField();
		t_name = new JTextField();
		t_pass = new JPasswordField();
		im = new ImageManager();
		bt = new JButton(im.getIcon("res/app/member.png", 40, 25));
		
		p_west.setPreferredSize(new Dimension(150,500));
		Dimension d = new Dimension(130, 30);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		bt.setPreferredSize(d);
		
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_pass);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);
		
		//table = new JTable(rowdata,columnName);
		//scroll = new JScrollPane(table);
		//add(scroll);
		
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		bt.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				release();
				System.exit(0); //프로세스 종료
			}
		});
		connect();
		getData();
	}
	
	//오라클 접속
	public void connect() {
		//1) 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			//2) db접속
			con = DriverManager.getConnection(url, user, pass);
			if(con != null) this.setTitle("오라클 접속 완료");
			else this.setTitle("접속실패, 하지만 넌 이걸 볼 일이 영원히 없겠지");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//오라클의 select문을 사용하여, jtable이 사용할 이차원배열을 생성해보자
	public void getData() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from emp order by empno asc";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
//			while(rs.next()) {
//				row = rs.getRow();
//			}
			rs.last();
			int row = rs.getRow();
			System.out.println("총 레코드수는 "+row);
			String[][] data = new String[row][8];
			//rs를 다시 복귀시키자
			rs.beforeFirst();//첫번째 레코드보다도 이전, 처음 탄생위치
			for (int i=0; i<row; i++) {
				rs.next();
				data[i][0] = Integer.toString(rs.getInt("empno")); //사원번호 컬럼
				data[i][1] = rs.getString("ename");
				data[i][2] = rs.getString("job");
				data[i][3] = Integer.toString(rs.getInt("mgr"));
				data[i][4] = rs.getString("hiredate");
				data[i][5] = Integer.toString(rs.getInt("sal"));
				data[i][6] = Integer.toString(rs.getInt("comm"));
				data[i][7] = Integer.toString(rs.getInt("deptno"));
			} 
			System.out.println("생성된 이차원 배열의 크기는 "+data.length);
			table = new JTable(data, columnName);
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
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void release() {
		if (con!=null) {
			try {
				con.close();
				System.out.println("자원 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	public void register() {
		//입력한 값 얻기
		String id = t_id.getText();
		String pass = new String(t_pass.getPassword());
		String name = t_name.getText();
		String[] inputs = {id, pass, name};
		
		//하나의 레코드 넣기
		for (int i = 0; i < inputs.length; i++) {
			table.setValueAt(inputs[i], row, i);
		}
		row++;
	}
	

	
	public void actionPerformed(ActionEvent e) {
		register();
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
}
