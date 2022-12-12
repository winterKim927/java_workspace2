package network.echo.gui;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	
	int port = 9999;
	ServerSocket server;
	Thread serverThread; //접속자 대기를 위한 전용 쓰레드
	
	public EchoServer() {
		//북쪽
		p_north = new JPanel();
		t_port = new JTextField(port+"",5);
		bt_start = new JButton("서버가동");
		//bt_start.setFont(new Font("GULIM", Font.PLAIN, 13));
		
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
				bt_start.setEnabled(false);
			}
		});
		
	}
	
	//클라이언트와 접속을 감지하기 위한 서버소켓을 생성하고 접속자를 기다리자
	public void startServer() {
		BufferedReader buffr = null;
		InputStreamReader reader = null;
		InputStream is = null;
		BufferedWriter buffw = null;
		OutputStreamWriter writer = null;
		OutputStream os = null;
		try {
			server = new ServerSocket(port);
			area.append("서버 생성\n");
			Socket socket = server.accept();
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();
			area.append(ip+" 에서 접속함\n");
			
			//소켓으로부터 입출력 스트림 뽑기
			is = socket.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			
			os = socket.getOutputStream();
			writer = new OutputStreamWriter(os);
			buffw = new BufferedWriter(writer);
			
			while (true) {
				//클라이언트의 메시지 듣기 (입력)
				String msg = buffr.readLine();
				
				//서버에 로그 남기기
				area.append(msg+"\n");
				
				//클라이언트의 메시지 말하기 (출력)
				buffw.write(msg + "\n");
				buffw.flush(); //버퍼처리된 출력스트림 계열은 반드시 flush
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
