package page;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CompanyPage extends JPanel{
	public CompanyPage() {
		setBackground(Color.red);
		setPreferredSize(new Dimension(AppMain.PAGE_WIDTH, AppMain.PAGE_HEIGHT));
	}
}
