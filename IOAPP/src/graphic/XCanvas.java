package graphic;

import java.awt.Graphics;

import javax.swing.JPanel;

//패널을 상속받아 개발자 주도하에 그림을 그리자
public class XCanvas extends JPanel{
	int x =50,y=50;
	//개발자가 원하는 그래픽처리를 코드 작성 해 놓으면
	//시스템이 적절한 시점에 아래의 메서드를 호출해준다
	//update() ---> paint()
	public void paint(Graphics g) {
		g.clearRect(0, 0, 600, 500);
		g.drawOval(x, y, 30, 30);
	}
}
