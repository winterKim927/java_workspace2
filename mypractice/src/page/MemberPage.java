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

import util.MailSender;
import util.StringUtil;

public class MemberPage extends JPanel{
	JPanel p;
	JLabel la_id, la_pass, la_email;
	JPasswordField t_pass;
	JTextField t_id, t_email;
	JButton bt_regist;
	AppMain main;
	public MemberPage(AppMain main) {
		this.main = main;
		p = new JPanel();
		la_id = new JLabel("아이디");
		la_pass = new JLabel("비밀번호");
		la_email = new JLabel("이메일");
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		t_email = new JTextField();
		bt_regist = new JButton("회원가입");
		
		p.setPreferredSize(new Dimension(300, 150));
		Dimension d = new Dimension(120, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		la_email.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		p.add(la_id);
		p.add(t_id);
		p.add(la_pass);
		p.add(t_pass);
		p.add(la_email);
		p.add(t_email);
		p.add(bt_regist);
		
		add(p);
		
		setPreferredSize(new Dimension(800,430));
		setBackground(Color.black);
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = regist();
				if(result > 0) {
					JOptionPane.showMessageDialog(MemberPage.this, "회원가입 성공\n메일을 확인하세요");
					MailSender.sendMail(t_email.getText());
				} else {
					JOptionPane.showMessageDialog(MemberPage.this, "회원가입 실패");
				}
			}
		});
	}
	
	public int regist() {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member(member_id, id, pass, email)";
		sql+=" values(seq_member.nextval, ?,?,?)";
		try {
			pstmt = main.con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, StringUtil.getEncryptedPassword(new String(t_pass.getPassword())));
			pstmt.setString(3, t_email.getText());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt);
		}
		return result;
		
	}
}
