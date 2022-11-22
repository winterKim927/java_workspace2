package stream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{
	CharacterRead cr;
	
	
	public MyActionListener(CharacterRead cr) {
		this.cr = cr;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("눌렀어?");
		cr.area.append("눌렀어?\n");
	}

}
