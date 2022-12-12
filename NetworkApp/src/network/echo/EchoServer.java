package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//에코 서버를 정의
//클라이언트가 소켓으로 접속을 시도하면 서버는 클라이언트의 접속을 감지해야
//대화할 수 있는 소켓을 얻을 수 있는데, 이와 같은 접속 감지용 소켓을 가리켜
//ServerSocket이라 한다
public class EchoServer {
	int port = 9999; // 실행되고 있는 네트워크 프로그램간 간섭이 발생하지 않도록 구분해주는 네트워크 구분 번호
	Socket socket;
	ServerSocket server; //접속자를 감지하는 객체
	public EchoServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("서버 가동");
			socket = server.accept(); // 접속자가 들어올 때까지 무한 대기상태
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();
			
			System.out.println(ip+" 접속자 감지");
			
			//현재 접속과 관련된 정보를 가진 소켓 객체로부터 스트림을 뽑자
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			String msg = null;
			msg = buffr.readLine();
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
