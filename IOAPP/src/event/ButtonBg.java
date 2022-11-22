package event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ButtonBg extends JFrame{
	JButton bt_create, bt_color;
	JPanel p1,p2;
	JButton[] btn = new JButton[10];
	ArrayList<JButton> list = new ArrayList<JButton>();
	int index = 0;
	public ButtonBg() {
		bt_create = new JButton("생성");
		bt_color = new JButton("색칠");
		p1 = new JPanel();
		p2 = new JPanel();
		p2.setBackground(Color.yellow);
		p1.add(bt_create);
		p1.add(bt_color);
		bt_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBtn();
			}
		});
		
		bt_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBg();
			}
		});
		
		add(p1, BorderLayout.NORTH);
		add(p2);
		
		setBounds(100,100, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setBg() {
		for (int i = 0; i < list.size(); i++) {
			JButton x = list.get(i);
			x.setBackground(Color.blue);
		}
		//객체를 모아서 처리하는데 유용한 api = Collection Framework라 하며, java.util 패키지에서 지원한다
	}
	
	//버튼을 동적으로 생성하여, JFrame에 붙이기
	public void createBtn() {
		JButton bt = new JButton("버튼"+index);
	    list.add(bt);
		index++;
		p2.add(bt);
		
		//paint()를 재정의한 경우와 다르게 GUI컴포넌트를 부착한 경우에는 컴포넌트의 갱신을 요청해야한다
		//패널을 새로고침
		//p2.updateUI();
		//프레임을 새로고침할 경우 = SwingUtilities.updateComponentTreeUI(this);
		SwingUtilities.updateComponentTreeUI(this); 
	}
	
	public static void main(String[] args) {
		new ButtonBg();
	}

}
