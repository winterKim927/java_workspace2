package diary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DiaryMain extends JFrame {
	JPanel p_west, p_center;

	// 서쪽영역
	JComboBox box_yy, box_dd, box_mm, box_icon;
	JTextArea area;
	JButton bt_regist, bt_del;

	// 중앙영역
	JPanel p_title, p_day, p_date;
	JButton bt_prev, bt_next;
	JLabel la_title;

	public DiaryMain() {

		// 패널들
		p_west = new JPanel();
		p_center = new JPanel();
		p_title = new JPanel();
		p_day = new JPanel();
		p_date = new JPanel();

		p_west.setPreferredSize(new Dimension(160, 560));
		p_center.setPreferredSize(new Dimension(770, 560));
		p_title.setPreferredSize(new Dimension(770, 50));
		p_day.setPreferredSize(new Dimension(770, 50));
		p_date.setPreferredSize(new Dimension(770, 460));

		box_yy = new JComboBox();
		box_dd = new JComboBox();
		box_mm = new JComboBox();
		box_icon = new JComboBox();

		area = new JTextArea();
		bt_regist = new JButton("등록");
		bt_del = new JButton("삭제");

		Dimension d_box = new Dimension(120, 30);
		box_yy.setPreferredSize(d_box);
		box_dd.setPreferredSize(d_box);
		box_mm.setPreferredSize(d_box);
		box_icon.setPreferredSize(d_box);
		area.setPreferredSize(new Dimension(120, 100));

		p_west.add(box_yy);
		p_west.add(box_mm);
		p_west.add(box_dd);
		p_west.add(area);
		p_west.add(box_icon);
		p_west.add(bt_regist);
		p_west.add(bt_del);

		// 중앙영역
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		la_title = new JLabel("2022년 12월");
		la_title.setFont(new Font("NANUM", Font.PLAIN, 20));

		p_title.add(bt_prev);
		p_title.add(la_title);
		p_title.add(bt_next);

		p_center.add(p_title);
		p_center.add(p_day);
		p_center.add(p_date);

		add(p_west, BorderLayout.WEST);
		add(p_center);

		setSize(930, 560);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new DiaryMain();
	}
}
