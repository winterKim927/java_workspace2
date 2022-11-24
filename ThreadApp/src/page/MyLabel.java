package page;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{
	int index;
	AppMain main;
	public MyLabel(ImageIcon icon, int index, AppMain main) {
		this.index = index;
		this.main = main;
		setIcon(icon);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.showHide(index);
			}
		});
	}
}
