package myPractice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICopy2 extends JFrame implements ActionListener{
	JTextField t_ori, t_dest;
	JButton bt_ori, bt_dest, bt_do;
	JFileChooser chooser;
	File f_ori;
	
	public GUICopy2() {
		setLayout(new FlowLayout());
		t_ori = new JTextField();
		t_ori.setPreferredSize(new Dimension(450,25));
		t_dest = new JTextField();
		t_dest.setPreferredSize(new Dimension(450,25));
		bt_ori = new JButton("원본찾기");
		bt_dest = new JButton("대상찾기");
		bt_do = new JButton("실행");
		chooser = new JFileChooser("D:\\테스트");
		
		add(t_ori);
		add(bt_ori);
		add(t_dest);
		add(bt_dest);
		add(bt_do);
		
		setBounds(100,100, 600, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt_ori.addActionListener(this);
		bt_dest.addActionListener(this);
		bt_do.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_ori) {
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				f_ori =  chooser.getSelectedFile();
				String ori_path = f_ori.getAbsolutePath();
				t_ori.setText(ori_path);
				t_dest.setText(ori_path);
			}
		} else if(obj == bt_dest) {			
			
		} else if(obj == bt_do) {
			if(JOptionPane.showConfirmDialog(this, "진짜 복사할까요?") == JOptionPane.OK_OPTION) {
				copy();
			}
		}
	}
	
	public void copy() {
		System.out.println("복사실행중");
	}

	public static void main(String[] args) {
		new GUICopy2();
	}

}
