package page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import util.StringUtil;

public class LoginPage extends JPanel{
	JPanel p_form; //입력양식을 묶을 패널
	JLabel la_id, la_pass;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login;
	AppMain main;
	
	public LoginPage(AppMain main) {
		this.main = main;
		p_form = new JPanel();
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("로그인");
		
		p_form.setPreferredSize(new Dimension(400,120));
		Dimension d = new Dimension(170,25);
		
		la_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		
		p_form.add(la_id);
		p_form.add(t_id);
		p_form.add(la_pass);
		p_form.add(t_pass);
		p_form.add(bt_login);
		
		add(p_form);
		
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(AppMain.PAGE_WIDTH, AppMain.PAGE_HEIGHT));
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
	}
	
	public void loginCheck() {
		String sql="select * from member where id=? and pass=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = main.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, StringUtil.getEncryptedPassword(new String(t_pass.getPassword())));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(LoginPage.this, "인증 성공");
				main.login = true;
			} else {
				JOptionPane.showMessageDialog(LoginPage.this, "인증 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt, rs);
		}
	}
}
