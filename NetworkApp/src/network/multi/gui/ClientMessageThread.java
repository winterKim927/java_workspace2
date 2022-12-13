package network.multi.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientMessageThread extends Thread{
	Socket socket;
	
	InputStream is;
	InputStreamReader reader;
	BufferedReader buffr;
	
	OutputStream out;
	OutputStreamWriter writer;
	BufferedWriter buffw;
	
	MultiClient client;
	
	boolean flag = true;
	
	public ClientMessageThread(MultiClient client, Socket socket) {
		this.client = client;
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
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			client.area.append(msg+"\n");
		} catch (IOException e) {
			flag=false;
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
		while (flag) {
			listen();
		}
	}
}
