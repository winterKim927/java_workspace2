package page;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LoginPage extends JPanel{
	public LoginPage() {
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(AppMain.PAGE_WIDTH, AppMain.PAGE_HEIGHT));
	}
}
