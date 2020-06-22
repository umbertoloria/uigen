package ui.struct;

import java.util.LinkedList;
import java.util.List;

public abstract class Box extends Element {

	private List<Element> elements = new LinkedList<>();

	public void add(Element element) {
		elements.add(element);
	}

	public void remove(Element element) {
		elements.remove(element);
	}

	public void onDraw() {
//		onDraw();
		for (Element element : elements) {
			element.onDraw();
//			if (element instanceof Box)
//				((Box) element).render();
//			else
//				element.onDraw();
		}
	}

	public int getSize() {
		return elements.size();
	}

	public Element getElementAt(int index) {
		return elements.get(index);
	}

}
