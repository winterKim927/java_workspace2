package page;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
	AppMain main;
	int index;
	public MyLabel(AppMain main, int index, ImageIcon icon) {
		this.main = main;
		this.index = index;
		setIcon(icon);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(index);
				main.showHide(index);
			}
		});
	}
}
