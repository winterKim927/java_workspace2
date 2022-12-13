package network.multi.katalk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import network.domain.ChatMember;

public class JoinPage extends Page{
	JLabel la_title;
	JTextField t_id;
	JTextField t_pass;
	JTextField t_name;
	JButton bt_login;
	JButton bt_join;
	ChatMemberDAO chatMemberDAO;
	
	public JoinPage(ClientMain main) {
		super(main);
		
		chatMemberDAO = new OracleChatMemberDAO();
		la_title = new JLabel("Sign Up");
		t_id = new JTextField();
		t_pass = new JTextField();
		t_name = new JTextField();
		
		bt_login = new JButton("로그인");
		bt_join = new JButton("회원가입");
		
		la_title.setPreferredSize(new Dimension(350, 200));
		la_title.setFont(new Font("Verdana", Font.BOLD, 45));
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		Dimension d = new Dimension(350, 30);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		
		
		add(la_title);
		add(t_id);
		add(t_pass);
		add(t_name);
		
		add(bt_login);
		add(bt_join);

		setBackground(Color.YELLOW);
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showHide(main.LOGINPAGE);
			}
		});
		
		bt_join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
	}
	
	public void regist() {
		ChatMember cm = new ChatMember();
		cm.setId(t_id.getText());
		cm.setPass(t_pass.getText());
		cm.setName(t_name.getText());
		int result = chatMemberDAO.insert(cm);
		if(result > 0) {
			JOptionPane.showMessageDialog(this, "가입 성공");
		}
	}
}
