package io.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class KeyboardApp {
	public static void main(String[] args) {
		//키보드 관련 스트림은 os가 켜질 때 이미 생성되어있다! 얻어오기만 하자
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buffr = new BufferedReader(reader);
		
		OutputStream os = System.out; 
		OutputStreamWriter writer = new OutputStreamWriter(os);
		BufferedWriter buffw = new BufferedWriter(writer);
		String data = null;
		try {
			data = buffr.readLine();
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
