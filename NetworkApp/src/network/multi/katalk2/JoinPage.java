package network.multi.katalk2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinPage extends JPanel{
	ClientMain main;
	JLabel la_join, la_id, la_pass, la_name;
	JTextField t_id, t_name;
	JPasswordField t_pass;
	JButton bt_tologin, bt_join;
	
	public JoinPage(ClientMain main) {
		this.main = main;
		la_join = new JLabel("Join Us");
		la_id = new JLabel("ID");
		t_id = new JTextField(25);
		la_pass = new JLabel("Pass");
		t_pass = new JPasswordField(25);
		la_name = new JLabel("Name");
		t_name = new JTextField(25);
		bt_tologin = new JButton("to Login");
		bt_join = new JButton("Join");
		
		la_join.setPreferredSize(new Dimension(300, 150));
		la_id.setPreferredSize(new Dimension(40, 30));
		la_pass.setPreferredSize(new Dimension(40, 30));
		la_name.setPreferredSize(new Dimension(40, 30));
		
		add(la_join);
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(la_name);
		add(t_name);
		add(bt_tologin);
		add(bt_join);
		setPreferredSize(new Dimension(300,500));
		setVisible(false);
		
		bt_tologin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showHide(JoinPage.this);
			}
		});
	}
}