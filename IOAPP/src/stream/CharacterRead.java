package stream;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CharacterRead extends JFrame{ // 500*600
	JButton bt;
	JTextArea area;
	MyActionListener listener;

	public CharacterRead() {
		bt = new JButton("읽기");
		area = new JTextArea();
		area.setPreferredSize(new Dimension(480,550));
		Font font = new Font("Verdana", Font.BOLD, 30);
		area.setFont(font);
		listener = new MyActionListener(this);
		setLayout(new FlowLayout());

		add(bt);
		add(area);

		setBounds(100, 100, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//내부익명클래스란? 클래스 코드 안에, 또 다른 이름없는 클래스를 둘 수 있는데
		//이러한 용도의 클래스를 가리켜, 내부 익명 클래스라 한다.
		//내부 익명 클래스는 특히 이벤트 구현시 재사용성이 없는 클래스를 
		//물리적인 .java파일로까지 만들 필요가 없으므로 자주 사용된다
		//new 뒤에 부모자료형이 오고 뒤에 오는 {}를 자식클래스로 본다
		//내부 익명 클래스의 장점 - 내부익명클래스 자신이 소속되어있는 외부 클래스(CharacterRead)의
		//멤버 변수를 맘대로 접근할 수 있다.
		//(람다 이전단계)
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				readFile();
			}
		});
		
	}
	//메모장 파일 읽기(한글도 포함)
	public void readFile() {
		//지금까지 써왔던 FileInputStream은 바이트기반 스트림이었다.
		//따라서 데이터 처리를 1byte씩 처리한다.
		//한글의 경우는 2byte로 구성되어있으므로 문자화 시킬 경우 한글을 제대로 표현할 수 없다
		//읽어들인 데이터를 대상으로 한글로 변환하기 위해서는 문자 기반 스트림을 이용하면 된다
		//FileInputStream fis = null;
		FileReader reader = null; //전세계 모든 문자가 깨지지 않는 문자기반 스트림
		String path="T:\\java_workspace2\\IOAPP\\data\\memo.txt";
		
		//스트림을 생성하자
		try {
			//fis = new FileInputStream(path);
			reader = new FileReader(path);
			//System.out.println("스트림 생성 성공");
			int data = -1;
			data = reader.read();
			System.out.print(Character.toString((char)data));
			data = reader.read();
			System.out.print(Character.toString((char)data));
			data = reader.read();
			System.out.print(Character.toString((char)data));
			data = reader.read();
			System.out.print(Character.toString((char)data));
			data = reader.read();
			System.out.print(Character.toString((char)data));
			data = reader.read();
			System.out.print(Character.toString((char)data));
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
		}
	}
	
	public static void main(String[] args) {
		new CharacterRead();
	}


}
