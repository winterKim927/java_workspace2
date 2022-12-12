package network.unicast.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	Thread serverThread;
	
	public UniServer() {
		//북쪽
		p_north = new JPanel();
		t_port = new JTextField("9999"+"",5);
		bt_start = new JButton("서버가동");
		//bt_start.setFont(new Font("NANUM", 0, 10));
		
		p_north.add(t_port);
		p_north.add(bt_start);
		//가운데
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setVisible(true);
		setSize(300,400);
		setLocationRelativeTo(null);
		
		serverThread = new Thread() {
			public void run() {
				startServer();
			}
		};
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		bt_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverThread.start();
			}
		});
	}
	
	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);
			area.append("서버가동\n");
			
			//접속자를 무제한으로 받는다
			while (true) { 
				Socket socket = server.accept();
				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip+"에서 접속함");
				//접속과 동시에 대화용 쓰레드가 탄생
				MessageThread mt = new MessageThread(this, socket);
				mt.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new UniServer();
	}
}
