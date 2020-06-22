package ui;

import ui.callback.Callback;
import ui.element.Ui;
import ui.exception.WrongStatusException;

import java.util.HashMap;
import java.util.Map;

public class Interfaccia {

	private Map<String, Callback> callbacks = new HashMap<>();
	private Map<String, String> events = new HashMap<>();
	private Ui ui;

	public void addCallback(String name, Callback callback) {
		if (callbacks.containsKey(name))
			throw new WrongStatusException();
		else
			callbacks.put(name, callback);
	}

	public void addEvent(String event, String callback) {
		if (events.containsKey(event))
			throw new WrongStatusException();
		else if (!callbacks.containsKey(callback))
			throw new WrongStatusException();
		else
			events.put(event, callback);
	}

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	public void print() {
		System.out.println("_______________");
		System.out.println(".: Callbacks :.");
		for (String callbackName : callbacks.keySet()) {
			System.out.println(callbackName);
		}
		System.out.println("____________");
		System.out.println(".: Events :.");
		for (String eventName : events.keySet()) {
			System.out.println(eventName);
		}
	}

	public void render() {
		ui.onDraw();
//		ui.render();
	}

}
