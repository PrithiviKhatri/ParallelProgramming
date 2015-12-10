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

	public Worker() {
	}

	@Override
	public void run() {
		int vertex = workpool.getWork();
		int newdist = 0;
		while (vertex != -1) {
			// System.out.println("outside vertex is " + vertex);
			inflag[vertex] = false;// vertex removed from workpool
			for (int v = 0; v < Application.n; v++) {
				if (weight[vertex][v] < Application.infinity) {// See if this is
																// shorter path
																// to v
					newdist = mindist[vertex] + weight[vertex][v];

					// System.out.println("new distance is " + newdist);

					if (newdist < mindist[v]) {
						mindist[v] = newdist;
						if (!inflag[v]) {// if v not in work pool
							
							inflag[v] = true;
							workpool.putWork(v);
						}
					}

				}
			}
			
			if (workpool.workpool.isEmpty()) {
				workpool.putWork(-1);
			}
			vertex = workpool.getWork();
		}
		System.out.println("exiting from worker");
		// System.out.println("printing mindist");
		
	}

}
