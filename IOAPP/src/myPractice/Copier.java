package myPractice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Copier extends JFrame implements ActionListener {
	JTextField t_ori, t_dest;
	JButton bt_ori, bt_dest, bt_do;
	FileInputStream fis;
	FileOutputStream fos;
	JFileChooser jfc;
	public Copier() {
		super("파일복사기");
		setLayout(new FlowLayout());
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt_ori = new JButton("원본찾기");
		bt_dest = new JButton("대상찾기");
		bt_do = new JButton("실행");
		t_ori.setPreferredSize(new Dimension(300, 30));
		t_dest.setPreferredSize(new Dimension(300, 30));
		jfc = new JFileChooser();
		
		add(t_ori);
		add(bt_ori);
		add(t_dest);
		add(bt_dest);
		add(bt_do);
		
		setBounds(200,200, 450, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt_ori.addActionListener(this);
		bt_dest.addActionListener(this);
		bt_do.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_ori) {
			jfc.showOpenDialog(this);
			if(JFileChooser.APPROVE_OPTION==0) {
				t_ori.setText(jfc.	getSelectedFile().getAbsolutePath());
			}
		} else if(obj == bt_do) {
			JOptionPane.showConfirmDialog(this, "복사할까요?");
		}
		
	}
	
	public static void main(String[] args) {
		new Copier();
	}
}
