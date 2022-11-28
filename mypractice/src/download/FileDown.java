package download;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class FileDown extends JFrame{
	JProgressBar bar;
	JButton bt_select, bt_down;
	JFileChooser chooser;
	FileInputStream fis = null;
	
	public FileDown() {
		bar = new JProgressBar();
		bt_select = new JButton("열기");
		bt_down = new JButton("실행");
		chooser = new JFileChooser("D:\\녹화");
		
		setLayout(new FlowLayout());
		
		bar.setPreferredSize(new Dimension(480, 70));
		bar.setStringPainted(true);
		bar.setString("다운로드 대기중");
		
		add(bar);
		add(bt_select);
		add(bt_down);
		
		setSize(500, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFile();
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeStream();
			}
		});
	}
	
	public void selectFile() {
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			setTitle(file.getAbsolutePath());
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
			}
			bt_down.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Thread th = new Thread() {
						public void run() {
							download(file);
						}
					};
					th.start();
				}
			});
		}
	}
	
	public void download(File file) {
		int data = -1;
		int read = 0;
		double total = 0;
		try {
			total = (double)fis.getChannel().size();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			while(true) {
				data = fis.read();
				if(data == -1) break;
				read++;
				double percent = (read/total)*100;
				bar.setString((int)percent+"%");
				bar.setValue((int)percent);
				System.out.println(percent+"% 완료");
			}
			System.out.println("다운로드 완료");
			bar.setString("다운로드 완료");
			closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void closeStream() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new FileDown();
	}
}
