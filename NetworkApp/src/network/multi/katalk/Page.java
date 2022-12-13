package network.multi.katalk;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	ClientMain main;
	public Page(ClientMain main) {
		this.main = main;
		setPreferredSize(new Dimension(400,500));
		
	}
}
