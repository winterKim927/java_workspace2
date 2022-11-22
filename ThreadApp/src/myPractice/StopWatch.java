package myPractice;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.StringUtil;

public class StopWatch extends JFrame{
	JButton bt;
	JPanel p;
	JLabel la;
	Thread th;
	boolean flag;
	int sec, min, hour;
	
	public StopWatch() {
		bt = new JButton("Start");
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				flag = !flag;
				String name = flag ? "Stop":"Start";
				bt.setText(name);
			}
		});
		p = new JPanel();
		la = new JLabel("00:00:00", SwingConstants.CENTER);
		la.setFont(new Font("Verdana", Font.BOLD, 120));
		p.add(bt);
		th = new Thread() {
			public void run() {
				while (true) {
					watch();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		};
		th.start();
		add(p, BorderLayout.NORTH);
		add(la);
		
		setBounds(100,100,700,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void watch() {
		if (flag) {
			sec++;
			String ssec = StringUtil.getNumString(sec);
			la.setText("00:00:" + ssec);
		}
	}
	
	public static void main(String[] args) {
		new StopWatch();
	}
}
