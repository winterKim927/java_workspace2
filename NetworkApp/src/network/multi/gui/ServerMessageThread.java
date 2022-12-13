package network.multi.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class ServerMessageThread extends Thread {
	Socket socket;
	InputStream is;
	InputStreamReader reader;
	BufferedReader buffr;
	
	OutputStream out;
	OutputStreamWriter writer;
	BufferedWriter buffw;
	
	MultiServer server;
	boolean flag = true;
	public ServerMessageThread(MultiServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			is = socket.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			
			out = socket.getOutputStream();
			writer = new OutputStreamWriter(out);
			buffw = new BufferedWriter(writer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣는 메서드
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			for (int i = 0; i < server.vec.size(); i++) {
				ServerMessageThread smt = server.vec.get(i);
				smt.sendMsg(msg);
			}
		} catch (IOException e) {
			flag = false;
			server.removeClient(this);
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
			server.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}
}
