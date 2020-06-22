package reader;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ui.Interfaccia;
import ui.callback.Callback;
import ui.callback.ObjectCallback;
import ui.callback.ProcedureCallback;
import ui.element.*;
import ui.exception.WrongStatusException;
import ui.struct.Box;
import ui.struct.Element;

import java.util.HashMap;
import java.util.Map;

public class Reader {

	private String path;

	public Reader(String path) {
		this.path = path;
	}

	public Interfaccia read() {
		// fase uno: loading
		// fase due: measure
		// fase tre: layout
		return load();
	}

	private Interfaccia load() {
		org.w3c.dom.Element root = XMLUtils.interpret(path);
		assert (root.getTagName().equals("uigen"));

		Interfaccia i = new Interfaccia();

		Node meta = XMLUtils.getFirstTagByTagName(root, "meta");
		interpretMeta(meta, i);
		Node ui = XMLUtils.getFirstTagByTagName(root, "ui");
		interpretUi(ui, i);

		return i;
	}

	private void interpretMeta(Node meta, Interfaccia i) {
		// interpreta "callbacks"
		Node callbacksNode = XMLUtils.getFirstTagByTagName(meta, "callbacks");
		for (Node callbackNode : XMLUtils.getChildsByTagName(callbacksNode, "callback")) {
			String nameValue = XMLUtils.getAttributeValueByName(callbackNode, "name");
			String typeValue = XMLUtils.getAttributeValueByName(callbackNode, "type");
			Callback c;
			if (typeValue.equals("object")) {
				String classValue = XMLUtils.getFirstTagByTagName(callbackNode, "class").getNodeValue();
				String methodValue = XMLUtils.getFirstTagByTagName(callbackNode, "method").getNodeValue();
				c = new ObjectCallback(classValue, methodValue);
			} else if (typeValue.equals("procedure")) {
				String functionValue = XMLUtils.getFirstTagByTagName(callbackNode, "function").getNodeValue();
				c = new ProcedureCallback(functionValue);
			} else {
				throw new WrongStatusException();
			}
			i.addCallback(nameValue, c);
		}
		// interpreta "events"
		Node eventsNode = XMLUtils.getFirstTagByTagName(meta, "events");
		for (Node eventNode : XMLUtils.getChildsByTagName(eventsNode, "event")) {
			String nameValue = XMLUtils.getAttributeValueByName(eventNode, "name");
			String callbackValue = XMLUtils.getAttributeValueByName(eventNode, "callback");
			i.addEvent(nameValue, callbackValue);
		}
	}

	private void interpretUi(Node ui, Interfaccia i) {
		Ui e = (Ui) processTag(ui);
		i.setUi(e);
	}

	/*
		private static Node loadAndGetUI(String path) {
			try {
				DocumentBuilder dbui = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = dbui.parse(Resources.getResource(path));
	//			return doc.getDocumentElement();
				NodeList childs = doc.getDocumentElement().getChildNodes();
				return childs.item(childs.getLength() - 2);
			} catch (ParserConfigurationException | IOException | SAXException e) {
				throw new IllegalStateException();
			}
		}
	*/
	private static Element processTag(Node node) {
		if (node.getNodeType() != Node.ELEMENT_NODE) return null;
		// Prelevo informazioni riguardo "nome" e "attributi"
		String name = node.getNodeName();
		Map<String, String> attributes = new HashMap<>();
		{
			NamedNodeMap attrs_map = node.getAttributes();
			for (int i = 0; i < attrs_map.getLength(); i++) {
				Node attr = attrs_map.item(i);
				assert (attr.getNodeType() == Node.ATTRIBUTE_NODE);
				attributes.put(attr.getNodeName(), attr.getNodeValue());
			}
		}

		Element curElement;

		if (name.equals("ui"))
			curElement = new Ui();
		else if (name.equals("lbox"))
			curElement = new LBox();
		else if (name.equals("gbox"))
			curElement = new GBox();
		else if (name.equals("label"))
			curElement = new Label();
		else if (name.equals("text"))
			curElement = new Text();
		else if (name.equals("button"))
			curElement = new Button();
		else
			return null;

		NodeList childs = node.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				Element curElementChild = processTag(child);
				if (curElementChild == null)
					continue;
				if (curElement instanceof Box) {
					((Box) curElement).add(curElementChild);
				} else {
//					System.out.println(name);
//					System.out.println(child.getNodeName());
					throw new IllegalStateException("un ui.element in un ui.element");
				}
			}
			if (child.getNodeType() == Node.TEXT_NODE)
				processText(child);
		}

		return curElement;
	}

	private static void processText(Node node) {
		if (node.getNodeType() != Node.TEXT_NODE) return;
//		System.out.println(node);
	}

}
