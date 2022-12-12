package network.unicast.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread extends Thread {
	UniServer server;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	public MessageThread(UniServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		try {
			String msg = buffr.readLine();
			server.area.append(msg+"\n");
			sendMsg(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			listen();
		}
	}
}
