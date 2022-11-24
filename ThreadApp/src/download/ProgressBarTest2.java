package download;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class ProgressBarTest2 extends JFrame{ //700 250
	JProgressBar bar1, bar2;
	JButton bt;
	int count1, count2;
	Thread th1, th2;
	
	public ProgressBarTest2() {
		setLayout(new FlowLayout());
		bar1 = new JProgressBar();
		Dimension d = new Dimension(680, 80);
		bar1.setPreferredSize(d);
		bar2 = new JProgressBar();
		bar2.setPreferredSize(d);
		bar1.setStringPainted(true);
		bar1.setFont(new Font("Verdana", Font.BOLD, 30));
		bar2.setStringPainted(true);
		bar2.setFont(new Font("Verdana", Font.BOLD, 30));
		bt = new JButton("실행");
		
		
		add(bar1);
		add(bar2);
		add(bt);
		
		
		setSize(700,250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th1.start();
				th2.start();
			}
		});
		
		th1 = new Thread() {
			public void run() {
				while (true) {
					if (count1 < 100) {
						count1++;
						bar1.setValue(count1);
						bar1.setString(count1 + "%");
						
					} else if (count1 == 100) break; 
					try {
						Thread.sleep(70);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		th2 = new Thread() {
			public void run() {
				while (true) {
					if (count2<100) {
						count2++;
						bar2.setValue(count2);
						bar2.setString(count2 + "%");
					} else if(count2 == 100) {
						JOptionPane.showMessageDialog(ProgressBarTest2.this, "다운로드 완료");
						break;
					}
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	public static void main(String[] args) {
		new ProgressBarTest2();
	}
}
