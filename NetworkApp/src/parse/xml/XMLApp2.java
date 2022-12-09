package parse.xml;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLApp2 {
	DocumentBuilderFactory factory; 
	DocumentBuilder builder;
	public XMLApp2() {
		factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("D:\\java_workspace2\\data\\NetworkApp\\res\\food.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element root = doc.getDocumentElement();
		System.out.println(root.getNodeName());
		
		Node firstNode = root.getFirstChild();
		Node customer = firstNode.getNextSibling();
		NodeList childList = customer.getChildNodes();
		
		for (int i = 0; i < childList.getLength(); i++) {
			Node item = childList.item(i);
			if(item.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(item.getNodeName());
				System.out.println(item.getTextContent());
			}
		}
	}
	public static void main(String[] args) {
		new XMLApp2();
	}
}
