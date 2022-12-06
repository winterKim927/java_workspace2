package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	//넘겨받은 숫자가 1자리수이면 앞에 0 붙이기
	public static String getNumString(int num) {
		String result = null;
		if (num<10) {
			result = "0"+num; 
		} else {
			result = ""+num;
		}
		return result;
	}
	
	//확장자 추출하여 반환
	public static String getExtend(String filename) {
		int lastIndex = filename.lastIndexOf(".");
		System.out.println(lastIndex);
		return filename.substring(lastIndex+1, filename.length());
	}
	
	//비밀번호 암호화
	//자바의 보안과 관련된 기능 api 패키지 == java.security
	public static String getEncryptedPassword(String pass) {
		//암호화객체
		MessageDigest digest;
		StringBuffer hexString = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pass.getBytes("UTF-8"));
			//String은 불변이다!! 상수!!
			//따라서 String 객체는 반복문 횟수가 클 때는 절대 누적식을 사용해서는 안된다!!
			//해결책) 변경가능한 문자열 객체를 지원하는 StringBuffer, StringBuilder 등을 활용하자
			hexString = new StringBuffer(); 
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff& hash[i]);
				if(hex.length() ==1) {
					hexString.append("0");
				}
				hexString.append(hex); 
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
	
	//(String)url을 입력받아 파일명(현재시간)+확장자를 반환하는 메서드
	public static String createFileName(String url) {
		long time = System.currentTimeMillis();
		String ext = StringUtil.getExtend(url);
		return time+"."+ext;
	}
	
//	public static void main(String[] args) {
//		String result = getEncryptedPassword("minzino");
//		System.out.println(result.length());
//	}
}

