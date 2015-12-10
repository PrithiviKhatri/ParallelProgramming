package parallelprogramming;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkPool {

	public static BlockingQueue<Integer> workpool = new LinkedBlockingQueue<Integer>();
	int work;

	public int getWork() {
		try {
			work = workpool.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return work;
	}

	public void putWork(int vertex) {
		try {
			workpool.put(vertex);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
