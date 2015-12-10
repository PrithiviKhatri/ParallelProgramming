package parallelprogramming;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkPool {

	public static BlockingQueue<Integer> workpool = new LinkedBlockingQueue<Integer>();
	int work = -1;
	int count=0;
	Lock lock = new ReentrantLock();

	public int getWork() throws InterruptedException {
		lock.lock();
		count = count - 1;
		lock.unlock();
		if (count == -Application.numworkers)
			for (int i = 0; i < Application.numworkers; i++) {
				workpool.put(-1);
			}
		else
			work = workpool.take();

		return work;
	}

	public void putWork(int vertex) {
		lock.lock();
		count++;
		lock.unlock();
		try {
			workpool.put(vertex);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
