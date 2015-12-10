package parallelprogramming;

import java.util.concurrent.locks.Lock;

public class Worker implements Runnable {
	private WorkPool workpool;
	private int weight[][];
	private int mindist[];
	private boolean inflag[];
	private Lock L[];

	Worker(WorkPool p, int weight[][], int mindist[], boolean inflag[], Lock L[]) {
		this.workpool = p;
		this.weight = weight;
		this.mindist = mindist;
		this.inflag = inflag;
		this.L = L;
	}

	@Override
	public void run() {
		int vertex = 0;
		try {
			vertex = workpool.getWork();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int newdist = 0;
		while (vertex != -1) {
			inflag[vertex] = false;// vertex removed from workpool
			for (int v = 0; v < Application.n; v++) {
				if (weight[vertex][v] < Application.infinity) {// See if this is
																// shorter path
																// to v
					newdist = mindist[vertex] + weight[vertex][v];

				//	if ((newdist < mindist[v])) {
						L[v].lock();
						if (newdist < mindist[v]) {
							mindist[v] = newdist;
							L[v].unlock();
							if (!inflag[v]) {// if v not in work pool

								inflag[v] = true;
								workpool.putWork(v);
							}
						} else
							L[v].unlock();
					//}
				}
			}


			try {
				vertex = workpool.getWork();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("exiting from worker");
		// System.out.println("printing mindist");

	}

}
