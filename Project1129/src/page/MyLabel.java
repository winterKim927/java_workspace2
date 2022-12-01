package page;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyLabel extends JLabel{
	int index;
	AppMain main;
	public MyLabel(ImageIcon icon, int index, AppMain main) {
		this.index = index;
		this.main = main;
		setIcon(icon);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.checkLogin(index);
			}
		});
	}
}
