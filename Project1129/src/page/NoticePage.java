package page;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class NoticePage extends JPanel{
	public NoticePage() {
		setBackground(Color.gray);
		setPreferredSize(new Dimension(AppMain.PAGE_WIDTH, AppMain.PAGE_HEIGHT));
	}
}
