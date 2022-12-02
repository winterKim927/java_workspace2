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
	public DateCell(String title, int fontSize, int x, int y) {
		super(title, fontSize, x, y);

		Border border = new LineBorder(Color.lightGray);
		setBorder(border);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(color==Color.white)color = Color.yellow;
				else color = Color.white;
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
		Font font = new Font("Verdana", Font.BOLD, fontSize);
		g2.setFont(font);
		g2.drawString(title, x, y);
	}
}