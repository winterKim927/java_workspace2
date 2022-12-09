package parse.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//파싱 대상이 되는 xml을 분석하여, 각 노드마다 알맞는 이벤트를 발생시켜
//개발자로 하여금 적절한 처리를 할 수 있는 기회를 준다
public class MyHandler extends DefaultHandler{
	ArrayList<Food> list;
	Food food;
	boolean isName = false; //현재 실행부가 어떤 태그를 지나는지
	boolean isPrice = false;
	public void startDocument() throws SAXException {
		//System.out.println("문서 시작함");
		
	}
	
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		//System.out.print("<"+tag+">");
		if(tag.equals("foodList")) {
			list = new ArrayList<Food>();
		} else if(tag.equals("food")){
			food = new Food();
		} else if(tag.equals("name")) {
			isName = true;
		} else if(tag.equals("price")) {
			isPrice = true;
		}
	}
	
	@Override
	//태그와 태그 사이의 내용이 되는 노드 발견할 때 호출!
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		//System.out.print(content);
		if(isName) {
			food.setName(content);
		} else if(isPrice) {
			food.setPrice(Integer.parseInt(content));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//System.out.println("</"+tag+">");
		if (tag.equals("name")) {
			isName = false;
		} else if(tag.equals("price")) {
			isPrice = false;
		} else if(tag.equals("food")) {
			list.add(food);
		}
	}
	
	public void endDocument() throws SAXException {
		//System.out.println("문서 끝남");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
			System.out.println(list.get(i).getPrice());
		}
	}
}
