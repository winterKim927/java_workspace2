package network.echo.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	
	int port = 9999;
	ServerSocket server;
	
	InputStream is;
	InputStreamReader reader;
	BufferedReader buffr;
	
	OutputStream os;
	OutputStreamWriter writer;
	BufferedWriter buffw;
	
	public EchoServer() {
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
		
		Thread serverTh = new Thread() {
			public void run() {
				connect();
			}
		};
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverTh.start();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void connect() {
		try {
			server = new ServerSocket(port);
			area.append("서버가동\n");
			Socket socket = server.accept();
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();
			area.append(ip +"에서 접속함\n");
			is = socket.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			os = socket.getOutputStream();
			writer = new OutputStreamWriter(os);
			buffw = new BufferedWriter(writer);
			while (true) {
				String msg = buffr.readLine();
				area.append(msg + "\n");
				
				buffw.write(msg + "\n");
				buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
