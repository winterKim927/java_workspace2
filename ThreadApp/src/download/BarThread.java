package download;
//하나의 프로그램 내에서 각각 틀린 방식으로 동작할 프로그래스바를 구현하기 위해
//재사용 가능성이 예상되므로, 별도의 .java로 정의

import javax.swing.JProgressBar;

public class BarThread extends Thread{
	JProgressBar jProgressBar;
	int n;
	int velX;
	int time;
	
	public BarThread(JProgressBar jProgressBar, int velX, int time) {
		this.jProgressBar = jProgressBar;
		this.velX = velX;
		this.time = time;
	}
	
	public void printValue() {
		n+=velX;
		jProgressBar.setValue(n);
		
	}
	
	public void run() {
		while(true) {
			printValue();
			if(n>=100) {
				break;
			}
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
