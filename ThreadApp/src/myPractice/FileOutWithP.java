package myPractice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class FileOutWithP extends JFrame{
	FileInputStream fis;
	JButton bt_find, bt_down;
	JFileChooser chooser;
	JProgressBar bar;
	Thread th;
	String ori;
	FileChannel fc;
	ArrayList<Integer> n = new ArrayList<Integer>();
	int bt;
	double percent = 0;
	public FileOutWithP() {
		setLayout(new FlowLayout());
		bt_find = new JButton("Find");
		bt_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chooser.showOpenDialog(FileOutWithP.this)==JFileChooser.APPROVE_OPTION) {
					ori = chooser.getSelectedFile().getAbsolutePath();
					try {
						fis = new FileInputStream(ori);
						bt = fis.available();
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					bar.setString(bt+"bytes");
				}
			}
		});
		
		bt_down = new JButton("Down");
		bt_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(FileOutWithP.this, "다운로드 할까요?")==JOptionPane.OK_OPTION) {
						th.start();
				}
			}
		});
		chooser = new JFileChooser();
		bar = new JProgressBar();
		bar.setPreferredSize(new Dimension(500,200));
		bar.setBackground(Color.white);
		bar.setStringPainted(true);
		bar.setFont(new Font("Verdana", Font.BOLD, 30));
		bar.setString("File Downloader");
		
		
		th = new Thread() {
			public void run() {
				while (true) {
					if(fis !=null)
					gameLoop();
				
				}
			};
		};
		
		add(bar);
		add(bt_find);
		add(bt_down);
		
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void gameLoop() {
			try {
				n.add(fis.read());
				System.out.println(n.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (bt!=n.size()){
				percent = (((double)n.size())/bt)*100;
				System.out.println(bt);
				System.out.println(percent);
				bar.setValue((int)percent);
				bar.setString((int)percent+"%");
			}
	}
	
//	public void fileCopy() {
//		try {
//			fis = new FileInputStream(ori);
//			int data = -1;
//			while(true) {
//				try {
//					data = fis.read();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} finally {
//			try {
//				fis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public static void main(String[] args) {
		new FileOutWithP();
	}

}
