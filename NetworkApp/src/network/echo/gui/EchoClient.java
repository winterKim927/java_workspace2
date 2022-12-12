package network.echo.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame {
	JPanel p_north;
	JComboBox<String> box_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
	
	Socket socket; // 대화용 소켓, 서버 접속용
	
	BufferedReader buffr = null;
	InputStreamReader reader = null;
	InputStream is = null;
	BufferedWriter buffw = null;
	OutputStreamWriter writer = null;
	OutputStream os = null;
	
	public EchoClient() {
		//북쪽
		p_north = new JPanel();
		box_ip = new JComboBox();
		t_port = new JTextField("9999"+"",5);
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
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		bt_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMsg();
				}
			}
		});
	}
	
	public void createIp() {
		for (int i = 3; i < 100; i++) {
			box_ip.addItem("172.30.1."+i);
		}
		box_ip.setSelectedIndex(4);
	}
	
	//소켓 객체의 인스턴스를 만드는 순간 지정한 서버에 접속
	public void connect() {
		String ip = box_ip.getSelectedItem()+"";
		try {
			socket = new Socket(ip, Integer.parseInt(t_port.getText()));
			//현재 소켓으로부터 대화에 사용할 스트림 얻기
			is = socket.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			
			os = socket.getOutputStream();
			writer = new OutputStreamWriter(os);
			buffw = new BufferedWriter(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버가 보낸 메시지 청취하기
	public void listen() {
		try {
			String msg = buffr.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg() {
		//메시지 보내기
		String msg = t_input.getText();
		try {
			buffw.write(msg+"\n"); //개행문자 반드시 넣을 것
			buffw.flush(); //버퍼처리된 출력스트림은 반드시 flush
		} catch (IOException e) {
			e.printStackTrace();
		}
		t_input.setText("");
		listen();
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
