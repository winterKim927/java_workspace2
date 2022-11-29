package util;

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
}

