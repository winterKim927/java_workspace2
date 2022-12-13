package network.multi.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	Thread serverThread;
	
	Vector<ServerMessageThread> vec = new Vector<ServerMessageThread>(); 
	
	public MultiServer() {
		//북쪽
		p_north = new JPanel();
		t_port = new JTextField("9999",5);
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

		serverThread = new Thread() {
			public void run() {
				startServer();
			}
		};
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverThread.start();
				bt_connect.setEnabled(false);
			}
		});
		
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(9999);
			area.append("서버가동중\n");
			while (true) {
				Socket socket = server.accept();
				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip + "에서 접속자 발생\n");
				ServerMessageThread smt = new ServerMessageThread(this, socket);
				smt.start();
				vec.add(smt);
				area.append("현재 " + vec.size() + "명이 참여중\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeClient(ServerMessageThread smt) {
		vec.remove(smt);
		area.append("current chatter : "+vec.size()+"\n");
	}
	
	public static void main(String[] args) {
		new MultiServer();
	}
}
