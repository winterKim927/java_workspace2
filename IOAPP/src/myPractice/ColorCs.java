package myPractice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ColorCs extends JFrame implements ActionListener{
	JButton[] btn_arr;
	Color[] color = {Color.red, Color.orange, Color.yellow, 
			Color.green, Color.blue, Color.cyan, Color.gray};
	XCanvas c;
	public ColorCs() {
		setLayout(new FlowLayout());
		c = new XCanvas();
		btn_arr = new JButton[7];
		for(int i = 0; i<btn_arr.length; i++) {
			btn_arr[i] = new JButton();
			btn_arr[i].setPreferredSize(new Dimension(30,30));
			btn_arr[i].setBackground(color[i]);
			btn_arr[i].addActionListener(this);
			add(btn_arr[i]);
		}
		
		setBounds(100,100,300,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<btn_arr.length; i++) {
			if(e.getSource()==btn_arr[i]) {
				c.setColor(color[i]);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new ColorCs();
	}

}
