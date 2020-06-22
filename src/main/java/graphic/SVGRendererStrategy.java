package graphic;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import ui.struct.Element;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SVGRendererStrategy implements RendererStrategy {

	private SVGGraphics2D g;

	public SVGRendererStrategy() {
		String svgNS = "http://www.w3.org/2000/svg";
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		g = new SVGGraphics2D(document);

//		Ask the test to render into the SVG Graphics2D implementation.
//		TestSVGGen test = new TestSVGGen();
//		test.paint(svgGenerator);
	}

	public void render(Element element) {
		g.setPaint(Color.red);
		g.fill(new Rectangle(element.getX(), element.getY(),
				element.getWidth(), element.getHeight()));
	}

	public void dump() {
		// Finally, stream out SVG to the standard output using UTF-8 encoding.
		final boolean useCSS = true; // we want to use CSS style attributes
		try {
			FileWriter fw = new FileWriter("nonmiinteressa.svg");
			BufferedWriter writer = new BufferedWriter(fw);
//			writer.write(str);
//			Writer out = new OutputStreamWriter(writer, "UTF-8");
			g.stream(writer, useCSS);
			writer.close();
		} catch (IOException e) { // UnsupportedEncodingException | SVGGraphics2DIOException |  e) {
			e.printStackTrace();
		}
	}

}
