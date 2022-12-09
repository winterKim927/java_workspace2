package io.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class KeyboardApp2 {
	public static void main(String[] args) {
		InputStream is = System.in;
		PrintStream ps = System.out;
		
		int data = -1;
		try {
			data = is.read();
			ps.println((char)data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
