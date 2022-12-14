//Thread란?
//=하나의 프로세스내에서 독립적으로 동작하는 하위 실행단위
//쓰레드 프로그래밍 순서
//1) 쓰레드 클래스를 정의한다
//	방법1) Thread 클래스를 상속받는 방법
//	방법2) 이미 특정 클래스를 상속받아 놓은 경우, 다중상속이 불가능하므로
//			Runnable 인터페이스를 구현(상속)하면 된다
//	방법3) 내부익명클래스로 쓰레드를 사용한다
//2)원하는 로직을 run()에 작성해놓아야한다
//3) 시스템 즉 JVM에게  수행을 맡긴다(start() 호출)
package basic;

public class ThreadTest extends Thread{
	int count;
	
	//쓰레드로 실행하고픈 코드를 run()에 작성해놓으면
	//jvm이 알아서 실행여부를 결정해준다
	
	//쓰레드가 소멸할때 내부적으로 destroy()를 호출하며 죽는다
	public void run() {
		while (true) {
			count++;
			System.out.println(count);
			if(count == 10) {
				System.out.println("10초가 지났습니다");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadTest t = new ThreadTest();
		t.start();
	}
}
