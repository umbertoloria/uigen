package graphic;

import ui.struct.Element;

public class Renderer {

	private static RendererStrategy rendererStrategy;

	public static void setRendererStrategy(RendererStrategy rendererStrategy) {
		Renderer.rendererStrategy = rendererStrategy;
	}

	public static void render(Element element) {
		rendererStrategy.render(element);
	}

}
