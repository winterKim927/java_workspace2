package basic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//초를 카운트하는 스탑워치 구현하기
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.StringUtil;
public class StopWatch extends JFrame {
	// main메서드도 사실 thread였다 => 자바는 쓰레드기반
	// main쓰레드는 개발자가 정의한 객체가 아니라서 동작에 제한이있다
	// 메인쓰레드의 목적은 프로그램의 운영에 있으므로, 절대 하지 말아야할 것
	// 1) 무한 루프에 빠지게 해서는 안된다
	// 2) 대기상태에 빠지게 해서는 안된다
	//		ex) 스트림의 read() - 대기상태에 빠지게 하는 메서드
	// 특히 메인쓰레드는 GUI 프로그램에서 이벤트를 감지하거나, 화면에
	// 그래픽 처리를 담당하는 등의 역할을 수행하므로, 개발자가 정의한
	// 쓰레드 이벤트나, GUI처리를 하는 것은 다른 언어에서는 금지사항이다
	//		ex)아이폰, 안드로이드에서는 아예 에러사항이다
	JButton bt;
	JLabel la;
	Thread thread;
	int sec;
	int min;
	int hour;
	boolean flag = false;
	
	public StopWatch() {
		setLayout(new FlowLayout());
		bt = new JButton("Start");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = !flag;
				String title = flag ? "Stop" : "Start";
				bt.setText(title);
//				if(flag) {
//					bt.setText("Stop");
//				} else {
//					bt.setText("Start");
//				}
			}
		});
		la = new JLabel("00:00:00", SwingConstants.CENTER);
		thread = new Thread() {
			public void run() {
				while (true) {
					printTime();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start();
		la.setPreferredSize(new Dimension(680, 250));
		la.setFont(new Font("Verdana", Font.BOLD, 120));
		
		add(bt);
		add(la);
		
		setBounds(100,100,700,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void printTime() {
		if (flag) {
			sec++;
			la.setText(StringUtil.getNumString(hour) + ":" + StringUtil.getNumString(min) + ":"
					+ StringUtil.getNumString(sec));
			if (sec == 60) {
				sec = 0;
				min++;
			}
			if (min == 60) {
				min = 0;
				hour++;
			} 
		}
	}
	
	public static void main(String[] args) {
		new StopWatch();
	}
}
