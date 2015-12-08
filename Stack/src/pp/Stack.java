package pp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Stack {

	List<Integer> stackArray = new ArrayList<Integer>();
	Lock pushlock = new ReentrantLock();
	Lock poplock = new ReentrantLock();

	public void push(int x) {
		pushlock.lock();
		stackArray.add(x);
		pushlock.unlock();

	}

	public int pop() {
		poplock.lock();
		int y = stackArray.remove(0);
		poplock.unlock();
		return y;
	}

	public void clear() {
		stackArray.clear();
	}

	@Override
	public String toString() {
		return "Stack [stackArray=" + stackArray + "]";
	}

}
