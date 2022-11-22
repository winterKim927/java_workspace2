/*
	Collention Framework이란?
	-객체를 모아서 처리할때 유용한 api들
  
  	Sun에서는 이 세상의 모든 객체들이 모여있는 모습을 크게 2가지로 본다
  	1) 순서있는 집합
  		List 유형
 		- 차이점1 : 자바의 배열은 기본자료형, 객체자료형 등 모든 자료형을 대상으로 하지만,
 					List는 오직 객체만을 담는다
 		- 차이점2 : 자바의 배열은 생성시 그 크기를 명시해야하므로 크기가 고정되어있지만
 					Collection은 JS시절처럼 크기가 동적으로 변할 수 있다
   2) 순서없는 집합
   		Set 유형
  		Map 유형
 */
package collection;

import java.util.ArrayList;

public class CollectionStudy {
	public static void main(String[] args) {
		/*List를 사용해본다
		 * 순서있는 집합을 처리할 때 사용하는 대표적 자료형
		 * 웹개발시 압도적으로 많이 씀
		 * */
		
		/* Generic 타입: 컬렉션에서 해당 컬렉션 객체의 자료형을 
								특정 자료형만으로 한정지을 수 있는 방법
								1) 객체가 섞이지 않도록 컴파일 타임에 방지한다
								2) 꺼낼때 형변환 과정이 필요없다
		 */
		ArrayList<String> list =new ArrayList<String>();
		System.out.println("배열의 길이는: "+ list.size());
		list.add("apple");
		list.add("banana");
		list.add("berry");
		System.out.println("배열의 길이는: "+ list.size());
//		for (int i = 0; i < list.size(); i++) {
//			String obj = (String)list.get(i);
//			System.out.println(obj);
//		}
		//객체를 대상으로 한 반복문을 좀 더 간결하게 = for each
		for(String fruit : list) {
			System.out.println(fruit);
		}
	}
}
