package boardmodel;
//응용프로그램 개발시, 디자인영역과 데이터 및 그 처리영역(업무로직)은 서로 분리시켜 개발해야 좋다 (유지보수성=비용)

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class BoardMain extends JFrame implements ActionListener{
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
	TableModel model;
	
	int selected_id;
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
		
		table = new JTable(model = new BoardModel(this));
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
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				release(con);
				System.exit(0);
			}
		});
		
		bt_regist.addActionListener(this);
		bt_search.addActionListener(this);
		bt_edit.addActionListener(this);
		bt_del.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				System.out.println("선택된 층수는 "+row);
				BoardModel boardModel = (BoardModel)model;
				String record[] = boardModel.data[row];
				System.out.println(record[1]);
				getDetail(record);
			}
		});
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "javase", "1234");
			if(con!=null) setTitle("Javase로 접속됨");
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

	public void regist() {
		BoardModel boardModel = (BoardModel)model;
		int result = boardModel.insert(t_title.getText(),t_writer.getText(),t_content.getText());
		if(result > 0) {
			boardModel.select();
			table.updateUI();
		}
	}
	
	//상세보기
	public void getDetail(String[] record) {
		selected_id = Integer.parseInt(record[0]);
		t_title2.setText(record[1]);
		t_writer2.setText(record[2]);
		t_content2.setText(record[3]);
	} 
	
	//한건 변경하기
	public void edit() {
		
	}
	
	//한건삭제
	public void del() {
		if(JOptionPane.showConfirmDialog(this, "삭제할까요?")==JOptionPane.OK_OPTION) {
			BoardModel boardModel = (BoardModel)model;
			int result = boardModel.delete(selected_id);
			if(result > 0) {
				boardModel.select();
				table.updateUI();
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_regist) {
			regist();
		} else if(e.getSource() == bt_search) {
			
		} else if(e.getSource() == bt_edit) {
			edit();
		} else if(e.getSource() == bt_del) {
			del();
		}
	}
	
	public static void main(String[] args) {
		new BoardMain();
	}

}
