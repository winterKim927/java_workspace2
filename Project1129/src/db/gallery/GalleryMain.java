package db.gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import util.StringUtil;

public class GalleryMain extends JFrame implements ActionListener{
	//너비 150
	JPanel p_west;
	JTextField t_title, t_writer;
	JTextArea t_content;
	JPanel p_preview; // 크기 150, 130
	JButton bt_open,bt_regist;
	JPanel p_sign; // 싸인처리할 패널
	
	JTable table;
	JScrollPane scroll;
	
	JPanel p_east;
	JTextField t_title2, t_writer2;
	JTextArea t_content2;
	JPanel p_preview2; // 크기 150, 130
	JButton bt_edit, bt_del;
	
	JFileChooser chooser;
	Image img;
	Image dImg;
	File file;
	String filename;
	String dir = "D:\\java_workspace2\\data\\project1129\\images\\";
	Connection con;
	GalleryModel model;
	SignModel sModel;
	ArrayList<int[]> history = new ArrayList<int[]>();
	
	int selected_id;
	public GalleryMain() {
		connect();
		
		sModel = new SignModel(this);
		
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(150,600));
		t_title = new JTextField(15);
		t_writer = new JTextField(15);
		t_content = new JTextArea();
		p_preview = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.gray);
				g2.fillRect(0, 0, 150, 130);
				g2.setColor(Color.white);
				g2.drawString("파일을 선택하세요", 30, 60);
				g2.drawImage(img, 0, 0, 150, 130, GalleryMain.this);
			}
		};
		bt_open = new JButton("열기");
		bt_regist = new JButton("등록");
		p_sign = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, 140, 70);
				g2.setColor(Color.red);
				for (int i = 0; i < history.size(); i++) {
					int[] dot = history.get(i);
					g2.fillOval(dot[0], dot[1], 3, 3);
				}
			}
		};
		
		Dimension d = new Dimension(150,130);
		t_content.setPreferredSize(new Dimension(150,70));
		p_sign.setPreferredSize(new Dimension(140,70));
		p_preview.setPreferredSize(d);
		p_preview.setBackground(Color.green);
		
		p_west.add(t_title);
		p_west.add(t_writer);
		p_west.add(t_content);
		p_west.add(p_sign);
		p_west.add(p_preview);
		p_west.add(bt_open);
		p_west.add(bt_regist);
		add(p_west, BorderLayout.WEST);
		
		chooser = new JFileChooser();
		
		table = new JTable(model = new GalleryModel(this));
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(600,500));
		add(scroll);
		
		p_east = new JPanel();
		p_east.setPreferredSize(new Dimension(150,600));
		t_title2 = new JTextField(15);
		t_writer2 = new JTextField(15);
		t_content2 = new JTextArea();
		p_preview2 = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.clearRect(0, 0, 15, 130);
				g2.setColor(Color.gray);
				g2.fillRect(0, 0, 150, 130);
				g2.setColor(Color.white);
				g2.drawString("파일을 선택하세요", 30, 60);
				g2.drawImage(dImg, 0, 0, 150, 130, GalleryMain.this);
			}
		};
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		t_content2.setPreferredSize(d);
		p_preview2.setPreferredSize(d);
		p_preview2.setBackground(Color.green);
		
		p_east.add(t_title2);
		p_east.add(t_writer2);
		p_east.add(t_content2);
		p_east.add(p_preview2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		add(p_east, BorderLayout.EAST);
		
		setSize(900, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		
		bt_open.addActionListener(this);
		bt_regist.addActionListener(this);
		bt_edit.addActionListener(this);
		bt_del.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				release(con);
				System.exit(0);
			}
		});
		
		//테이블과 리스너 연결
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.setSelectionBackground(Color.yellow);
				int row = table.getSelectedRow();
				String[] record = model.data[row];
				selected_id = Integer.parseInt(record[0]);
				System.out.println(selected_id);
				getDetail(row);
			}
		});
		
		//p_sign과 마우스리스너 연결
		p_sign.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("x는 : "+x+"y는 : "+y);
				int[] dot = {x, y}; //한점을 표현할 배열
				history.add(dot); //리스트에 추가
				
				p_sign.repaint();
			}
		});
	}
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "javase", "1234");
			if(con!=null) setTitle("접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//접속에만 사용
	public void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//DML인 경우에만 사용
	public void release(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//SELECT인 경우에만 사용
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
	
	public void openFile() {
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			System.out.println(file.getName());
			try {
				img = ImageIO.read(file);
				p_preview.repaint();
				//이미지명에 사용할 현재시간, 밀리세컨드까지!
				long time = System.currentTimeMillis();
				//System.out.println(time);
				filename = time+"."+StringUtil.getExtend(file.getName());
				//System.out.println(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copy() {
		//이미지 복사
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(dir+filename);
			int data = -1;
			byte[] buff = new byte[1024];
			while (true) {
				data = fis.read(buff);
				if(data == -1) break;
				fos.write(buff);;
			}
			System.out.println("복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();							
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null) {
				try {
					fis.close();							
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void regist() {
		//이미지 복사 + 오라클 등록
		copy();
		int result = model.insert(t_title.getText(), t_writer.getText(), t_content.getText(), filename);
		if(result >0) {
			model.selectAll();
			table.updateUI();
		}
		for (int i = 0; i < history.size(); i++) {
			int[] dot = history.get(i);
			sModel.insert(dot[0], dot[1], 1);
		}
	}
	
	public void getDetail(int row) {
		GalleryModel gm = (GalleryModel)model;
		t_title2.setText(gm.data[row][1]);
		t_writer2.setText(gm.data[row][2]);
		t_content2.setText(gm.data[row][3]);
		File file = new File(dir+gm.data[row][4]);
		try {
			dImg = ImageIO.read(file);
			p_preview2.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void edit() {
		int result = model.update(t_title2.getText(), t_writer2.getText(), t_content2.getText(), selected_id);
		if(result>0) {
			model.selectAll();
			table.updateUI();
		}
	}
	
	public void del() {
		int result = model.delete(selected_id);
		if(result!=0) {
			model.selectAll();
			table.updateUI();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_open) {
			openFile();
		} else if(e.getSource() == bt_regist) {
			regist();
		} else if(e.getSource() == bt_edit) {
			edit();
		} else if(e.getSource() == bt_del) {
			if(JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?")==JOptionPane.OK_OPTION)
			del();
		}
	}
	
	public static void main(String[] args) {
		new GalleryMain();
	}
}
