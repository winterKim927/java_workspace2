//GUI 프로그램에서는 만일 메인쓰레드를 루프나 대기상태에 두면
//이벤트나 그래픽 처리가 먹통이 된다
//일반적인 응용프로그램 분야에서는 아예 금지사항이다
package basic;

public class ThreadTest2 {

	public static void main(String[] args) {
		//메인쓰레드 = 동기방식 Synchronous		
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
		//개발자가 정의한 쓰레드 생성 및 실행
		//쓰레드 = 비동기방식 Asynchronous
		Thread t = new Thread() {
			public void run() {
				System.out.println("A");
			}
		};
		t.start();
		System.out.println("B");
	}
}
