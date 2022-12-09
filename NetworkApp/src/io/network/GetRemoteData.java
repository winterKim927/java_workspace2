package io.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.parser.JSONParser;

public class GetRemoteData {
	public static void main(String[] args) {
		HttpURLConnection con = null;
		InputStream is = null;
		InputStreamReader reader = null;
		BufferedReader buffr = null;
		JSONParser parser = new JSONParser();
		try {
			URL url = new URL("http://apis.data.go.kr/6430000/goodRestaService1");
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "text/html");
			System.out.println(con.getResponseCode());
			
			//웹서버와의 접속을 통해 스트림을 생성하여 데이터를 가져오기
			is = con.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			String data = null;
			while (true) {
				data = buffr.readLine();
				if(data == null) break;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
