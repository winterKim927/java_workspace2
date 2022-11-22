package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*순서 없는 컬렉션 중, Map을 학습한다*/
public class MapTest {
	//Kisses 초콜릿 연상하자
	//Key-String, Value-String
	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("apple", "사과");
		map.put("banana", "바나나");
		map.put("orange", "오렌지");
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			//System.out.println(key);
			String value = map.get(key);
			System.out.println(value);
		}
	}
	
}
