package network.echo.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient_BACK extends JFrame {
	JPanel p_north;
	JComboBox<String> box_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
	
	int port = 9999;
	
	public EchoClient_BACK() {
		//북쪽
		p_north = new JPanel();
		box_ip = new JComboBox();
		t_port = new JTextField(port+"",5);
		bt_connect = new JButton("접속");
		
		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);
		//가운데
		area = new JTextArea();
		scroll = new JScrollPane(area);
		//남쪽
		p_south = new JPanel();
		t_input = new JTextField(20);
		bt_send = new JButton("전송");
		
		p_south.add(t_input);
		p_south.add(bt_send);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(p_south, BorderLayout.SOUTH);
		
		createIp();
		
		setVisible(true);
		setSize(300,400);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void createIp() {
		for (int i = 3; i < 100; i++) {
			box_ip.addItem("172.30.1."+i);
		}
		box_ip.setSelectedIndex(4);
	}
	
	public static void main(String[] args) {
		new EchoClient_BACK();
	}
}
