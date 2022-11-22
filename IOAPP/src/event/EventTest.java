package event;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventTest extends JFrame {
	JButton bt;
	JTextField t;
	JPanel p;
	int x,y;
	
	public EventTest() {
		bt = new JButton("나버튼");
		t = new JTextField(25);
		p = new JPanel() {
			public void paint(Graphics g) {
				g.clearRect(0, 0, 290, 300);
				g.fillRect(0, 0, 290, 300);
				g.drawOval(x, y, 100, 100);
			}
		};
		p.setPreferredSize(new Dimension(290,300));
		p.setBackground(Color.yellow);
		setLayout(new FlowLayout());
		add(bt);
		add(t);
		add(p);
		setSize(300,400);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x += 5;
				y += 5;
				p.repaint();
			}
		});
		//텍스트필드와 키리스너 연결
		//이벤트 구현시 개발자가 재정의할 메서드 수가 3개 이상 넘어가면, sun에서 이미 해당 인터페이스를 구현해놓은
		//즉 재정의해놓은 객체를 지원해주는데, 이 객체들을 가리켜 어댑터라한다
		//KeyListener를 구현한 어댑터 -> KeyAdapter 클래스
		//WindowListener를 구현한 어댑터 -> WindowAdapter 클래스
		t.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				System.out.println("키 눌렀어?");
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				System.out.println("최소화됐어");
			}
			public void windowClosing(WindowEvent e) {
				System.exit(0); //프로세스 종료
			}
		});
	}

	public static void main(String[] args) {
		new EventTest();
	}
}
