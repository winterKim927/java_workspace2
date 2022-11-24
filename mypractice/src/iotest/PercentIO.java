package iotest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PercentIO extends JFrame{
	JFileChooser cs;
	JProgressBar bar;
	JButton bt_find, bt_down;
	JPanel p;
	FileInputStream fis;
	Thread th;
	String path;
	String fileName;
	ArrayList<Integer> readed = new ArrayList<Integer>();
	int fileSize;
	double percent;
	int done;
	boolean flag=false;
	public PercentIO() {
		
		cs = new JFileChooser("D:\\녹화");
		bar = new JProgressBar();
		bar.setBackground(new Color(131,219,248));
		bar.setString("다운로드 대기중...");
		bar.setStringPainted(true);
		bar.setFont(new Font("Gulim", Font.BOLD, 40));
		
		bt_find = new JButton("Find");
		bt_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cs.showOpenDialog(PercentIO.this)==JFileChooser.APPROVE_OPTION) {
					File file = cs.getSelectedFile();
					path = file.getAbsolutePath();
					fileName = file.getName();
					getFileInfo();
					bar.setString(fileSize + "Bytes" );
				}
			}
		});
		bt_down = new JButton("Down");
		bt_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th = new Thread() {
					public void run() {
						readFile();
					}
				};
				th.start();
			}
		});
		p = new JPanel();
		
		
		p.add(bt_find);
		p.add(bt_down);
		
		add(bar);
		add(p, BorderLayout.SOUTH);
		
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void getFileInfo() {
		try {
			fis = new FileInputStream(path);
			fileSize = fis.available();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void readFile() {
			while (true) {
				try {
					readed.add(fis.read());
				} catch (IOException e) {
					e.printStackTrace();
				}
				done = readed.size();
				percent = done / (double) fileSize * 100;
				bar.setString((int) percent + "%");
				bar.setValue((int) percent);
				if (done >= fileSize) {
					bar.setString("다운로드 완료");
					break;
				}
				System.out.println("읽은양 " + done);
				System.out.println("파일크기" + fileSize);
			}
	}
	
	public static void main(String[] args) {
		new PercentIO();
	}
}
