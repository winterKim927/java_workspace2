package page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.StringUtil;

public class LoginPage extends JPanel{
	JPanel p;
	JLabel la_id, la_pass;
	JPasswordField t_pass;
	JTextField t_id;
	JButton bt_login;
	AppMain main;
	public LoginPage(AppMain main) {
		this.main = main;
		p = new JPanel();
		la_id = new JLabel("아이디");
		la_pass = new JLabel("비밀번호");
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("로그인");
		
		p.setPreferredSize(new Dimension(300, 150));
		Dimension d = new Dimension(120, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		
		p.add(la_id);
		p.add(t_id);
		p.add(la_pass);
		p.add(t_pass);
		p.add(bt_login);
		
		add(p);
		
		setPreferredSize(new Dimension(800,430));
		setBackground(Color.blue);
		bt_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = loginCheck();
				if (result>0) {
					JOptionPane.showMessageDialog(LoginPage.this, "로그인 성공");
					main.login = true;
				} else {
					JOptionPane.showMessageDialog(LoginPage.this, "로그인 실패\n아이디와 비밀번호를 확인하세요");
				}
			}
		});
	}
	
	public int loginCheck() {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "select * from member where id = ? and pass = ?";
		try {
			pstmt = main.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, StringUtil.getEncryptedPassword(new String(t_pass.getPassword())));
			System.out.println(StringUtil.getEncryptedPassword(new String(t_pass.getPassword())));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt);
		}
		return result;
	}
}
