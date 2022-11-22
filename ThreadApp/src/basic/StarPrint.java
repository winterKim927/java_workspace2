package basic;

public class StarPrint {
/*쓰레드를 2개이상 생성하여 실행하는 프로그램 작성하기
 * 2개 의상의 쓰레드를 정의한다면, .java로 만들 필요가 있다
 * 따라서 쓰레드를 별도의 클래스로 정의하자!!
 */
	
	public static void main(String[] args) {
		StarThread st1 = new StarThread("★");
		st1.start();
		StarThread st2 = new StarThread("☆");
		st2.start();
	}

}
