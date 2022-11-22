package myPractice.collention;

import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();
		set.add("바나나");
		set.add("사과");
		set.add("딸기");
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
//		for(String fruit : set) {
//			System.out.println(fruit);
//		}
	}
}
