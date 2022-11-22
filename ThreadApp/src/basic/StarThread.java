package basic;
//쓰레드를 정의한다
public class StarThread extends Thread{
	String star;
	public StarThread(String star) {
		this.star = star;
	}
	//개발자는 독립 실행을 원하는 코드를 run()에 작성해놓기만 하면 된다
	public void run() {
		//int count=0;
		while (true) {
			System.out.println(Thread.currentThread().getName()+star);
			//count++;
			//if(count%10 == 0)System.out.println("★");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
