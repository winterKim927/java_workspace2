package parse.json;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

public class FoodBest {
	public String getData() throws IOException {
		String serviceKey = "Wk636lybR0B29crfR1q5w2%2BAJ0yged7T%2Fx%2Fd0oM0cQ%2B%2BX%2Bpe2kNUCGoKDWeGq3PE12rKllaIrzXF%2B44VzNXedw%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6430000/goodRestaService1/getGoodResta1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("currentPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("BSSH_NM","UTF-8") + "=" + URLEncoder.encode("즐거운나의집돌솥밥", "UTF-8")); /*밥맛 좋은 집 업소 명*/
//        urlBuilder.append("&" + URLEncoder.encode("SIGUN_NM","UTF-8") + "=" + URLEncoder.encode("청주시", "UTF-8")); /*업소 위치*/
//        urlBuilder.append("&" + URLEncoder.encode("DETAIL_ADRES","UTF-8") + "=" + URLEncoder.encode("상당구 영운천로 153번길 36", "UTF-8")); /*업소 세부 주소*/
//        urlBuilder.append("&" + URLEncoder.encode("TELNO","UTF-8") + "=" + URLEncoder.encode("2985285", "UTF-8")); /*업소 전화번호*/
//        urlBuilder.append("&" + URLEncoder.encode("RM","UTF-8") + "=" + URLEncoder.encode("돌솥정식", "UTF-8")); /*비고*/
//        urlBuilder.append("&" + URLEncoder.encode("APPN_YEAR","UTF-8") + "=" + URLEncoder.encode("null", "UTF-8")); /*밥맛 좋은 집 지정 년도*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
		return sb.toString();
	}
	
    public static void main(String[] args) throws IOException {
    	FoodBest fb = new FoodBest();
    	JSONObject json = null;
    	String data = fb.getData();
        JSONParser parser = new JSONParser();
        try {
			json = (JSONObject)parser.parse(data);
			JSONArray array = (JSONArray)json.get("body");
			for (int i = 0; i < array.size(); i++) {
				JSONObject json2 = (JSONObject)array.get(i);
				System.out.println("맛집 번호 "+(i+1)+") "+json2.get("BSSH_NM"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} 
    }
}
