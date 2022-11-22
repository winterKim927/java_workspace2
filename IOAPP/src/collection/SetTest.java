package collection;

import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	//순서 없는 컬렉션 자료형중, Set을 학습해본다
	public static void main(String[] args) {
		//순서가 없는 컬렉션이다
		HashSet<String> set = new HashSet<String>();
		set.add("apple");
		set.add("banana");
		set.add("lemon");
		
		//순서 없는 Set을 나열해보자
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
//		for(String fruit:set) {
//			System.out.println(fruit);
//		}
	}
}
