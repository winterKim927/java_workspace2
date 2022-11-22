package myPractice.collention;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonBg extends JFrame {
	JButton bt_create, bt_bg;
	JPanel p1, p2;
	ArrayList<JButton> list;
	int index;
	
	public ButtonBg() {
		bt_create = new JButton("생성");
		bt_bg = new JButton("실행");
		p1 = new JPanel();
		p2 = new JPanel();
		p1.add(bt_create);
		p1.add(bt_bg);
		add(p1, BorderLayout.NORTH);
		add(p2);
		bt_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBt();
			}
		});
		
		bt_bg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBg();
			}
		});
		
		list = new ArrayList<JButton>();
		
		setBounds(100,100,500,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void createBt() {
		JButton bt = new JButton("버튼"+index);
		index++;
		list.add(bt);
		p2.add(bt);
		p2.updateUI();
	}
	
	public void setBg() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBackground(Color.yellow);
		}
	}
	
	public static void main(String[] args) {
		new ButtonBg();
	}

}
