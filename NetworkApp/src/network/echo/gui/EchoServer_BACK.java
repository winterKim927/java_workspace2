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

public class EchoServer_BACK extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	
	int port = 9999;
	
	public EchoServer_BACK() {
		//북쪽
		p_north = new JPanel();
		t_port = new JTextField(port+"",5);
		bt_connect = new JButton("서버가동");
		
		p_north.add(t_port);
		p_north.add(bt_connect);
		//가운데
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setVisible(true);
		setSize(300,400);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new EchoServer_BACK();
	}
}
