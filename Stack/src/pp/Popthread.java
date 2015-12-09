package pp;

public class Popthread implements Runnable {

	Stack stack;
	int y;

	public Popthread(Stack s) {
		this.stack = s;
	}

	@Override
	public void run() {
		y = stack.pop();

	}

}
