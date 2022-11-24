package gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShurekGallery extends JFrame implements ActionListener{
	//제너릭 : 컴파일 시점부터 미리 자료형을 정해주는것
	ArrayList<CustomButton> btnList;
	JPanel p, p_south;
	ArrayList<Image> imageList = new ArrayList<Image>();
	Image image = null;
	Thread th;
	double x;
	int targetX;
	public static final double a = 0.08;
	public ShurekGallery() {
		super("갤러리");
		btnList = new ArrayList<CustomButton>();
		for (int i = 0; i < 7; i++) {
			btnList.add(new CustomButton(30,30));
		}
		createImage();
		p = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				for (int i = 0; i < imageList.size(); i++) {
					g2.drawImage(imageList.get(i), (int)x+500*i, 0, 500, 340, p);
				}
			}
		};
		p.setPreferredSize(new Dimension(500, 340));
		p.setBackground(Color.yellow);
		
		p_south = new JPanel();
		p_south.setBackground(Color.gray);
		p_south.setPreferredSize(new Dimension(500,60));
		th = new Thread() {
			public void run() {
				while (true) {
					move();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
		
		//남쪽 패널에 버튼 7개 넣기
		//우리가 사용하고 있는 모든 GUI 컴포넌트는 다음과 같이 2가지 유형으로 분류된다.
		/* GUI 컴포넌트란? 사용자 입력을 받는 모든 컨트롤
		 * 						ex) 버튼, 체크박스, 텍스트박스...
		 * 1) 컨테이너류 : 남을 포함할 수 있는 자
		 * 					ex) Frame, Panel...
		 * 					컨테이너류는 남을 포함해야하기 때문에 배치관리자가 지원된다
		 * 2) 비주얼컴포넌트 : 컨테이너에게 포함되는 자
		 * 					ex) Button, TextField...
		 * */
		for(CustomButton bt :btnList) {
			p_south.add(bt);
		}
		
		add(p);
		add(p_south, BorderLayout.SOUTH);
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		for (int i = 0; i < btnList.size(); i++) {
			btnList.get(i).addActionListener(this);
		}
	}
	
	public void createImage() {
		Class myClass = this.getClass();
		for (int i = 0; i < btnList.size(); i++) {
			URL url = myClass.getClassLoader().getResource("res/shurek/img"+i+".jpg");
			try {
				Image image = ImageIO.read(url);
				imageList.add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("생성된 이미지는 총"+imageList.size()+"개");
	}
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < btnList.size(); i++) {
			if(e.getSource() == btnList.get(i)) {
				targetX = -500*i;
			}
		}		
	}
	
	public void move() {
		x = x+a*(targetX - x);
		p.repaint();
	}
	
	public static void main(String[] args) {
		new ShurekGallery();
	}

}
