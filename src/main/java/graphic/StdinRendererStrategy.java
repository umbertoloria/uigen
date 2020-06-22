package graphic;

import ui.struct.Box;
import ui.struct.Element;

import java.util.Stack;

public class StdinRendererStrategy implements RendererStrategy {

	private Stack<Element> elms = new Stack<>();

	public void render(Element element) {
		if (elms.size() > 0)
			System.out.print("|   " +
					"    ".repeat(elms.size() - 1));
		System.out.println(element);

		if (element instanceof Box) {
			Box box = (Box) element;
			elms.push(box.getElementAt(box.getSize() - 1));
		} else if (element == elms.peek())
			elms.pop();
	}

}
