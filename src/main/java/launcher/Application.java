package launcher;

import graphic.Renderer;
import graphic.SVGRendererStrategy;
import graphic.StdinRendererStrategy;
import reader.Reader;
import ui.Interfaccia;

public class Application {

	public static void main(String[] args) {
		SVGRendererStrategy strategy = new SVGRendererStrategy();
		// init
		Renderer.setRendererStrategy(
//				strategy
				new StdinRendererStrategy()
		);

		Reader reader = new Reader("sketches/register_form.uigen");
		Interfaccia interfaccia = reader.read();
		interfaccia.print();
		interfaccia.render();

		strategy.dump();

	}

}
