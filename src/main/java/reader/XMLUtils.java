package reader;

import launcher.Resources;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XMLUtils {

	public static Element interpret(String path) {
		try {
			DocumentBuilder dbui = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dbui.parse(Resources.getResource(path));
			return doc.getDocumentElement();
		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new IllegalStateException();
		}
	}

	public static Node getFirstTagByTagName(Node node, String tagName) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals(tagName)) {
				return child;
			}
		}
		throw new IllegalStateException();
//		return null;
	}

	public static List<Node> getChildsByTagName(Node node, String tagName) {
		List<Node> result = new LinkedList<>();
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals(tagName)) {
				result.add(child);
			}
		}
		return result;
	}

	public static String getAttributeValueByName(Node node, String attributeName) {
		NamedNodeMap map = node.getAttributes();
		for (int i = 0; i < map.getLength(); i++) {
			Node n = map.item(i);
			if (n.getNodeType() == Node.ATTRIBUTE_NODE
					&& n.getNodeName().equals(attributeName)) {
				return n.getNodeValue();
			}
		}
		throw new IllegalStateException();
//		return null;
	}
}
