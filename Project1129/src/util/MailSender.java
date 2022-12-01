package util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

//구글이 제공해주는 구글 메일 서버를 이용한 자바 메일 발송
public class MailSender {
	String myMailAccount ="winter0927@gmail.com"; 
	String password="egdmdcwwyqyeinnw";
	public void sendMail(String to) {
		String subject = "주문해주셔서 감사합니다";
		String from ="winter09277@amazingstore.com";
		String sender ="쇼핑몰 관리자";
		//String to = "winter09277@naver.com, winter0927@gmail.com"; // 콤마로 다수의 메일 가능
		StringBuffer content = new StringBuffer();
		content.append("<h1>고객님 안녕하세요, 쇼핑닷컴입니다</h1>");
		content.append("<p>입금이 아직 안되어있네요</p>");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); //TLS 사용
		
		//java mail api에서 지원하는 Session 객체 생성 & 인증절차
		Session mailSession = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myMailAccount, password);
			}
		});
		
		//보내기 위한 셋팅
		MimeMessage msg = new MimeMessage(mailSession);
		
		//보내는 사람, 받는 사람 정보 셋팅
		try {
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(sender, "UTF-8", "B")));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			//HTML형식으로 보냄
			msg.setContent(content.toString(), "text/html;charset=UTF-8");
			msg.setSentDate(new Date());
			
			//전송하기
			Transport trans = mailSession.getTransport("smtp");
			trans.connect(myMailAccount, password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();
			System.out.println("메일발송성공");
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		MailSender sender = new MailSender();
//		sender.sendMail();
//	}
}
