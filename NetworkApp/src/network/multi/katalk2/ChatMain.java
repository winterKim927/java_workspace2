package network.multi.katalk2;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatMain extends JFrame{
	
	JTextArea area;
	JScrollPane scroll;
	
	JPanel p_south;
	
	JTextField t_input;
	JButton bt_send;
	
	public ChatMain() {
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		p_south = new JPanel();
		t_input = new JTextField(20);
		bt_send = new JButton("Send");
		
		p_south.add(t_input);
		p_south.add(bt_send);
		
		add(scroll);
		add(p_south, BorderLayout.SOUTH);
		
		
		setBounds(700,200,400,500);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
