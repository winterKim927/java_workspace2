package page;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
				if(index == 1 || index == 2 || main.login)
				main.showHide(index);
				else JOptionPane.showMessageDialog(main, "로그인하세요");
			}
		});
	}
}
