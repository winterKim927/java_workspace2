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

public class Downloader extends JFrame{ //600*200;
	JProgressBar bar;
	JButton bt_open, bt_down;
	JFileChooser chooser;
	FileInputStream fis;
	
	public Downloader() {
		bar = new JProgressBar();
		bt_open = new JButton("파일열기");
		bt_down = new JButton("다운로드");
		chooser = new JFileChooser("D:\\downloads");
		
		bar.setPreferredSize(new Dimension(580, 90));
		bar.setStringPainted(true);

		setLayout(new FlowLayout());
		add(bar);
		add(bt_open);
		add(bt_down);
		
		setSize(600,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		bt_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread th = new Thread() {
					public void run() {
						download();
					}
				};
				th.start();
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeStream();
			}
		});
	}
	
	public void openFile() {
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			fis = null;
			File file = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(file);
				setTitle(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void download() {
		double total = 0;
		//진행 상황을 출력하기 위한 정보 수집
		try {
			total = fis.getChannel().size();
			System.out.println("총 용량 : "+total);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		int data = -1;
		int readByte = 0;
		
		while(true) {
			try {
				data = fis.read();
				if(data==-1) {
					break;
				}
				readByte++;
				double ratio = (readByte/total)*100;
				System.out.println(ratio);
				bar.setValue((int)ratio);
				bar.setString((int)ratio+"% complete");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		closeStream();
		bar.setString("Download Completed");
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
		new Downloader();
	}
}
