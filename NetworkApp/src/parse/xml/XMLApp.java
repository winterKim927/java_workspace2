package parse.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 1) DOM 파싱 : 태그마다 1:1 대응하는 객체를 메모리에 생성하므로
 					요즘같은 스마트폰이 활성화된 개발분야에서는 잘 쓰지 않는다
 					
 2) SAX 파싱 : 발견되는 태그마다 이벤트를 발생시켜주는 파싱방식
 */

public class XMLApp {
	public static void main(String[] args) {
		// GOF의 패턴 중 팩토리 패턴으로 생성
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			String path = "D:\\java_workspace2\\data\\NetworkApp\\res\\food.xml";
			parser.parse(new File(path), new MyHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
