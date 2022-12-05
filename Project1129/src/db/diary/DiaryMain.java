package db.diary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DiaryMain extends JFrame implements ActionListener {
	DBManager dbManager = DBManager.getInstance();
	DiaryDAO diaryDAO = new DiaryDAO();
	JPanel p_west;
	JComboBox box_yy;
	JComboBox box_mm;
	JComboBox box_dd;
	JTextArea area;
	JScrollPane scroll;
	JComboBox box_icon;// 다이어리에 사용할 아이콘
	JButton bt_regist;// 등록및 수정
	JButton bt_del;

	// 센터
	JPanel p_center;// 플로우를 적용하기 위한 센터의 최상위 컨테이너
	JPanel p_title;// 이전 다음 선택 버튼
	JPanel p_dayOfWeek;
	JPanel p_dayOfMonth;
	JButton bt_prev;
	JLabel la_title;// 현재 년월
	JButton bt_next;

	// 요일 처리
	DayCell[] dayCells = new DayCell[7];
	String[] dayTitle = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

	// 날짜
	DateCell[][] dateCells = new DateCell[6][7];

	// 현재 사용자가 보게될 날짜 정보!!
	Calendar currentObj = Calendar.getInstance();


	public DiaryMain() {
		// 서쪽 영역
		p_west = new JPanel();
		box_yy = new JComboBox();
		box_mm = new JComboBox();
		box_dd = new JComboBox();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		box_icon = new JComboBox();
		bt_regist = new JButton("등록");
		bt_del = new JButton("삭제");

		// 센터영역
		p_center = new JPanel();
		p_title = new JPanel();
		p_dayOfWeek = new JPanel();
		p_dayOfMonth = new JPanel();
		bt_prev = new JButton("이전");
		la_title = new JLabel("2022년 12월");
		bt_next = new JButton("다음");

		p_west.add(box_yy);
		p_west.add(box_mm);
		p_west.add(box_dd);
		p_west.add(scroll);
		p_west.add(box_icon);
		p_west.add(bt_regist);
		p_west.add(bt_del);

		p_west.setPreferredSize(new Dimension(150, 500));
		Dimension d = new Dimension(120, 30);
		box_yy.setPreferredSize(d);
		box_mm.setPreferredSize(d);
		box_dd.setPreferredSize(d);
		box_icon.setPreferredSize(d);
		area.setPreferredSize(new Dimension(120, 100));
		bt_regist.setPreferredSize(new Dimension(60, 30));
		bt_del.setPreferredSize(new Dimension(60, 30));

		p_center.add(p_title);
		p_center.add(p_dayOfWeek);
		p_center.add(p_dayOfMonth);

		p_title.add(bt_prev);
		p_title.add(la_title);
		p_title.add(bt_next);

		p_center.setPreferredSize(new Dimension(750, 500));
		p_title.setPreferredSize(new Dimension(750, 50));
		Font font = new Font("NANUM", Font.BOLD, 15);
		bt_prev.setFont(font);
		la_title.setFont(new Font("NANUM", Font.BOLD, 20));
		bt_next.setFont(font);

		p_dayOfWeek.setPreferredSize(new Dimension(750, 50));
		p_dayOfMonth.setPreferredSize(new Dimension(750, 400));
		p_dayOfWeek.setBackground(Color.white);
		p_dayOfWeek.setLayout(new GridLayout(1, 7));
		p_dayOfMonth.setLayout(new GridLayout(6, 7));

		add(p_west, new BorderLayout().WEST);
		add(p_center);

		createDayOfWeek(); // 요일 출력
		createDayOfMonth(); // 날짜 출력
		calculate();

		setSize(930, 560);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// setResizable(false); //윈도우 크기 변경 불가

		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjustYYMM(e);
			}

		});
		bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjustYYMM(e);
			}
		});
		
		bt_regist.addActionListener(this);
	}

	public void adjustYYMM(ActionEvent e) {
		int adjust = 0;
		if (e.getSource() == bt_next) {
			adjust = 1;
		} else if (e.getSource() == bt_prev) {
			adjust = -1;
		}
		int mm = currentObj.get(Calendar.MONTH);
		currentObj.set(Calendar.MONTH, mm + adjust);
		calculate();
		printLog();
	}

	// 요일 출력
	public void createDayOfWeek() {
		// 7개를 생성하여 패널에 부착
		for (int i = 0; i < dayCells.length; i++) {
			dayCells[i] = new DayCell(dayTitle[i], "", 15, 30, 20);
			p_dayOfWeek.add(dayCells[i]); // 화면에 부착
		}
	}

	public void createDayOfMonth() {
		int n = 0;
		for (int i = 0; i < dateCells.length; i++) {
			for (int j = 0; j < dateCells[i].length; j++) {
				dateCells[i][j] = new DateCell(this, "", "", 13, 40, 10);
				p_dayOfMonth.add(dateCells[i][j]);
			}
		}
	}

	// 제목 출력
	public void printTitle() {
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);

		String str = yy + "년 " + (mm + 1) + "월";

		la_title.setText(str);
	}

	// 달력을 구현하기 위한 두가지 정보
	// 1) 해당 월이 무슨 요일부터 시작하는가?
	public int getStartDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		cal.set(yy, mm, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	// 2) 해당 월이 며칠까지 있는가?
	public int getLastDateOfMonth() {
		Calendar cal = Calendar.getInstance();
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH + 1);
		cal.set(yy, mm, 0);
		int date = cal.get(Calendar.DATE);
		return date;
	}

	public void printDate() {
		int startDay = getStartDayOfWeek();
		int lastDay = getLastDateOfMonth();
		int n = 1;
		int t = 1;
		for (int i = 0; i < dateCells.length; i++) {
			for (int j = 0; j < dateCells[i].length; j++) {
				DateCell cell = dateCells[i][j];
				if (n >= startDay && t <= lastDay) {
					cell.title = t + "";
					t++;
				} else {
					cell.title = "";
				}
				n++;
			}
		}
		p_dayOfMonth.repaint();
	}

	public void calculate() {
		printTitle();
		// getStartDayOfWeek();
		// getLastDateOfMonth();
		printDate();
		//기록된 다이어리 출력
		printLog();
	}
	
	public void printLog() {
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		List<Diary> diaryList = diaryDAO.selectAll(yy, mm);
		System.out.println("등록된 다이어리 수는 "+diaryList.size());
		for (int i = 0; i < dateCells.length; i++) {
			for (int j = 0; j < dateCells[i].length; j++) {
				if(dateCells[i][j].title.equals("")==false) {
					int date = Integer.parseInt(dateCells[i][j].title);
					for (int x = 0; x < diaryList.size(); x++) {
						Diary obj = diaryList.get(x);
						if(obj.getDd() == date) {
							dateCells[i][j].content = obj.getContent();
							dateCells[i][j].color = Color.CYAN;
						}
					}
				}
			}
		}
		p_center.repaint();
	}

	// 재사용을 위해 쿼리만을 전담하는 객체를 별도로 정의하자
	// == DAO (Data Access Object)
	public void regist() {
		//DTO 생성
		Diary d = new Diary();
		
		//UnBoxing
		int yy = Integer.parseInt(box_yy.getSelectedItem().toString());
		int mm = Integer.parseInt(box_mm.getSelectedItem().toString());
		int dd = Integer.parseInt(box_dd.getSelectedItem().toString());
		String content = area.getText();
		String icon = box_icon.getSelectedItem().toString();
		
		//레코드 한건 채워넣기
		d.setYy(yy);
		d.setMm(mm);
		d.setDd(dd);
		d.setContent(content);
		d.setIcon(icon);
		
		int result = diaryDAO.insert(d);
		System.out.println(result);
		if(result!=0) {
			JOptionPane.showMessageDialog(this, "등록성공");
			printLog();
		} else {
			System.out.println("쿼리실패");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_regist) {
			regist();
		} else if (obj == bt_del) {

		}
	}
	
	//콤보박스에 날짜 채워넣기
	public void setDateInfo(String title) {
		box_yy.removeAllItems();
		box_mm.removeAllItems();
		box_dd.removeAllItems();
		box_icon.removeAllItems();
		box_yy.addItem(currentObj.get(Calendar.YEAR)); // int => object (Boxing)
		box_mm.addItem(currentObj.get(Calendar.MONTH));
		box_dd.addItem(title);
		box_icon.addItem("압정");
	}
	
	

	public static void main(String[] args) {
		new DiaryMain();
	}

}