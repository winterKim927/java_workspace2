package network.multi.katalk;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientMain extends JFrame{
	JPanel container;//전환될 페이지들을 안고 있을 최상위 패널
	
	Page[] page = new Page[3];
	
	public static final int LOGINPAGE = 0;
	public static final int JOINPAGE = 1;
	public static final int CHATPAGE = 2;
	
	public ClientMain() {
		container = new JPanel();
		page[0] = new LoginPage(this);
		page[1] = new JoinPage(this);
		page[2] = new ChatPage(this);
		
		for (int i = 0; i < page.length; i++) {
			container.add(page[i]);
		}
		
		add(container);
		showHide(LOGINPAGE);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showHide(int n) {
		for (int i = 0; i < page.length; i++) {
			if(n==i) {
				page[i].setVisible(true);
			} else {
				page[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}
