package myPractice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICopy extends JFrame implements ActionListener{
	JTextField t_ori, t_dest;
	JButton bt_ori, bt_dest, bt_do;
	File f_ori, f_dest;
	FileInputStream fis;
	FileOutputStream fos;
	JFileChooser chooser;
	
	public GUICopy() {
		setLayout(new FlowLayout());
		t_ori = new JTextField();
		t_ori.setPreferredSize(new Dimension(400, 25));
		t_dest = new JTextField();
		t_dest.setPreferredSize(new Dimension(400, 25));
		bt_ori = new JButton("원본선택");
		bt_dest = new JButton("대상선택");
		bt_do = new JButton("실행");
		chooser = new JFileChooser("T:\\java_workspace2\\IOAPP\\res");
		//chooser2 = new JFileChooser("T:\\java_workspace2\\IOAPP\\res");
		add(t_ori);
		add(bt_ori);
		add(t_dest);
		add(bt_dest);
		add(bt_do);
		
		setBounds(100, 100, 510, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt_ori.addActionListener(this);		
		bt_dest.addActionListener(this);		
		bt_do.addActionListener(this);		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_ori && chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			f_ori = chooser.getSelectedFile();
			t_ori.setText(f_ori.getAbsolutePath());
		} else if(obj == bt_dest && chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			f_dest = chooser.getSelectedFile();
			t_dest.setText(f_dest.getAbsolutePath());
		} else if(obj == bt_do) {
			int result = JOptionPane.showConfirmDialog(this, "복사 실행할까요?");
			if(result == JOptionPane.OK_OPTION) {
				copy(f_ori, f_dest);
			}
		}
	}
	
	public void copy(File f_ori, File f_dest) {
		try {
			fis = new FileInputStream(f_ori);
			fos = new FileOutputStream(f_dest);
			int data = -1;
			while(true) {
				data = fis.read();
				if(data == -1) break;
				fos.write(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
				JOptionPane.showMessageDialog(this, "복사완료");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		new GUICopy();
	}

}
