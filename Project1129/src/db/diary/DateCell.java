package db.diary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//날짜에 사용하기 위한 셀
public class DateCell extends Cell {
	Color color = Color.white;
	DiaryMain main;
	public DateCell(DiaryMain main, String title, String content, int fontSize, int x, int y) {
		super(title, content, fontSize, x, y);
		this.main = main;
		Border border = new LineBorder(Color.lightGray);
		setBorder(border);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(color==Color.white) {
					color = Color.yellow;
					main.setDateInfo(DateCell.this.title);
					main.area.setText(DateCell.this.content);
				} else {
					color = Color.white;
				}
				repaint();
			}
		});
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 120, 120);
		g2.setColor(color);
		g2.fillRect(0, 0, 120, 120);
		g2.setColor(Color.darkGray);
		Font font = new Font("NANUM", Font.BOLD, fontSize);
		g2.setFont(font);
		g2.drawString(title, x, y);
		g2.drawString(content, x-30, y+20);
	}
}