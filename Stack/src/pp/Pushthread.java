package pp;

public class Pushthread implements Runnable {

	Stack stack;
	int x;


	public Pushthread(Stack s, int x) {
		this.stack = s;
		this.x = x;
	}

	@Override
	public void run() {
		stack.push(x);
	}

}
