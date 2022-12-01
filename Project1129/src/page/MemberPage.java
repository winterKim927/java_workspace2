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
	JPanel p_form; //입력양식을 묶을 패널
	JLabel la_id, la_pass, la_email;
	JTextField t_id, t_email;
	JPasswordField t_pass;
	JButton bt_regist;
	AppMain main;
	MailSender mailSender;
	
	public MemberPage(AppMain main) {
		this.main = main;
		mailSender = new MailSender();
		p_form = new JPanel();
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		t_id = new JTextField();
		t_pass = new JPasswordField();
		la_email = new JLabel("Email");
		t_email = new JTextField();
		bt_regist = new JButton("회원가입");
		
		p_form.setPreferredSize(new Dimension(400,150));
		Dimension d = new Dimension(170,25);
		
		la_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		la_email.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		p_form.add(la_id);
		p_form.add(t_id);
		p_form.add(la_pass);
		p_form.add(t_pass);
		p_form.add(la_email);
		p_form.add(t_email);
		p_form.add(bt_regist);
		
		add(p_form);
		setBackground(Color.blue);
		setPreferredSize(new Dimension(AppMain.PAGE_WIDTH, AppMain.PAGE_HEIGHT));
		bt_regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = regist();
				if(result>0) {
					JOptionPane.showMessageDialog(MemberPage.this, "등록성공");
					//이메일 발송(유저가 존재하는 메일주소를 넣었을 때만)
					mailSender.sendMail(t_email.getText());
				}
			}
		});
	}
	
	public int regist() {
		PreparedStatement pstmt = null;
		int result = 0;
		// ? 바인드 변수 처리하고 싶을 때 사용하는 기호
		String sql = "insert into member(member_idx, id, pass, email)";
		sql += " values(seq_member.nextval, ?,?,?)";
		try {
			pstmt = main.con.prepareStatement(sql);
			//바인드 변수에 값 할당(인덱스는 1부터 시작이다)
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
