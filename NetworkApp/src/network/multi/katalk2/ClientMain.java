package network.multi.katalk2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientMain extends JFrame{
	LoginPage p_login;
	JoinPage p_join;
	
	public ClientMain() {
		
		p_login = new LoginPage(this);
		p_join = new JoinPage(this);
		setLayout(new FlowLayout());
		
		add(p_login);
		add(p_join);
		
		setBounds(300,200,400,500);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}	
	
	public void showHide(JPanel panel) {
		if (panel == p_login) {
			p_login.setVisible(false);
			p_join.setVisible(true);
		} else {
			p_login.setVisible(true);
			p_join.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}
