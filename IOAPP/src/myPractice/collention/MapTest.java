package myPractice.collention;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class MapTest {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("apple", "사과");
		map.put("orange", "오렌지");
		map.put("banana", "바나나");
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String value = map.get(it.next());
			System.out.println(value);
		}
	}
}
