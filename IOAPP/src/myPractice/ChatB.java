package myPractice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends JFrame implements ActionListener, KeyListener{
	JButton bt_create, bt_send;
	JPanel p_south;
	JTextArea area;
	JScrollPane jp;
	JTextField t_input;
	ChatA ca;
	
	public ChatB(ChatA ca){
		this.ca = ca;
		bt_send = new JButton("전송");
		bt_send.setBackground(new Color(192,192,192));
		p_south = new JPanel();
		p_south.setLayout(new FlowLayout());
		p_south.setBackground(new Color(128,64,64));
		area = new JTextArea();
		area.setBackground(new Color(255,255,128));
		jp = new JScrollPane(area);
		t_input = new JTextField(15);
		t_input.setBackground(new Color(82,205,252));
		p_south.add(t_input);
		p_south.add(bt_send);
		
		add(jp);
		add(p_south, BorderLayout.SOUTH);
		
		setBounds(500,200,300,400);
		setVisible(true);
		
		bt_send.addActionListener(this);
		t_input.addKeyListener(this);
		
	}
	
	public void send() {
		area.append("나 : "+t_input.getText()+"\n");
		if(ca != null) {
			ca.area.append("상대 : "+t_input.getText()+"\n");
		}
		t_input.setText("");
	}
		
	public void actionPerformed(ActionEvent e) {
		send();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			send();
		}
	}
	
}
	
	
