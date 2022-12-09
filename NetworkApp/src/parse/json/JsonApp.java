package parse.json;

import java.lang.reflect.Array;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonApp {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		Object obj = null;
		String str = "{"
				+ "    \"name\" : \"철수\","
				+ "    \"age\" : 29,"
				+ "    \"hasPet\" : true,"
				+ "    \"pets\" : ["
				+ "        {"
				+ "            \"name\" : \"원조\","
				+ "            \"type\" : \"고양이\""
				+ "        },"
				+ "        {"
				+ "            \"name\" : \"마찌\","
				+ "            \"type\" : \"고양이\" "
				+ "        }"
				+ "    ]"
				+ "}";
		JSONParser jsonParser = new JSONParser();
		try {
			obj = jsonParser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject json = (JSONObject)obj;
		System.out.println(json.get("name"));
		System.out.println(json.get("age"));
		System.out.println(json.get("hasPet"));
		JSONArray array = (JSONArray)json.get("pets");
		System.out.println("반려동물 수는 "+array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject pet = (JSONObject)array.get(i);
			System.out.println(pet.get("name"));
		}
	}
}
