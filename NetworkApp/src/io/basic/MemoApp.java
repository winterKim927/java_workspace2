package io.basic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	JButton bt;
	JButton bt_open;
	JButton bt_save;
	JFileChooser chooser;
	JTextArea area;
	JScrollPane scroll;
	String path = "D:\\java_workspace2\\data\\NetworkApp\\res\\memo.txt";
	File file;
	public MemoApp() {
		bt = new JButton("실행");
		bt_open = new JButton("파일열기");
		bt_save =new JButton("저장");
		chooser = new JFileChooser("D:\\java_workspace2\\ShopAdmin\\src\\com\\edu\\shop");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		setLayout(new FlowLayout());
		
		area.setFont(new Font("NANUM", Font.BOLD, 15));
		scroll.setPreferredSize(new Dimension(1570, 720));
		
		//add(bt);
		add(bt_open);
		add(bt_save);
		add(scroll);
		
		setSize(1600,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData2();
			}
		});
		
		bt_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
	}
	
	public void openFile() {
		if(chooser.showOpenDialog(MemoApp.this) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			BufferedReader reader = null;
			FileReader fr = null;
			int count = 0;
			try {
				fr = new FileReader(file);
				reader = new BufferedReader(fr);
				String str = "";
				while(true) {
					str = reader.readLine();
					if(str==null) break;
					count++;
					area.append(str+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(reader!=null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fr!=null) {
					try {
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(count);
		}
	}
	
	public void saveFile() {
		BufferedWriter buffw = null;
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			buffw = new BufferedWriter(writer);
			String str = area.getText();
			buffw.write(str);
			JOptionPane.showMessageDialog(this, "저장완료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(buffw != null) {
				try {
					buffw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void loadData() {
		FileInputStream fis = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(path);
			int data = -1;
			while(true) {
				data = fis.read();
				if(data == -1) break;
				sb.append((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		area.setText(sb.toString());
	}
	
	public void loadData2() {
		FileInputStream fis = null;
		InputStreamReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			int data = -1;
			while (true) {
				data = reader.read();
				if(data==-1) break;
				sb.append((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		area.setText(sb.toString());
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}
}
