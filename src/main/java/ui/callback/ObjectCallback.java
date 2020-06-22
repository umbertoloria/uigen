package ui.callback;

public class ObjectCallback implements Callback {

	public final String className, methodName;

	public ObjectCallback(String className, String methodName) {
		this.className = className;
		this.methodName = methodName;
	}

}
