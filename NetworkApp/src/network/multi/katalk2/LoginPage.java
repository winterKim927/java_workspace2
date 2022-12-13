package network.multi.katalk2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import network.multi.katalk.Page;

public class LoginPage extends JPanel{
	ClientMain main;
	JLabel la_login, la_id, la_pass;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login, bt_tojoin;
	
	public LoginPage(ClientMain main) {
		this.main = main;
		la_login = new JLabel("Please Login");
		la_id = new JLabel("ID");
		t_id = new JTextField(25);
		la_pass = new JLabel("Pass");
		t_pass = new JPasswordField(25);
		bt_login = new JButton("Login");
		bt_tojoin = new JButton("to Join");
		
		la_login.setPreferredSize(new Dimension(300, 150));
		la_id.setPreferredSize(new Dimension(40, 30));
		la_pass.setPreferredSize(new Dimension(40, 30));
		
		add(la_login);
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(bt_login);
		add(bt_tojoin);
		setPreferredSize(new Dimension(300,500));
		
		bt_tojoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showHide(LoginPage.this);
			}
		});
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChatMain();
			}
		});
	}
	
	
}
