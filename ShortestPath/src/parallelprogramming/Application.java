package parallelprogramming;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
	public static final int numworkers = 2;
	public static final int n = 5;
	public static final int infinity = 990000;
	private static WorkPool workpool = new WorkPool();
	// private static int weight[][] = new int[n][n];

	private static int weight[][] = { { infinity, 4, 8, infinity, infinity }, { infinity, infinity, 3, 1, infinity },
			{ infinity, infinity, infinity, infinity, 5 }, { infinity, infinity, 2, infinity, 10 },
			{ infinity, infinity, infinity, infinity, infinity } };

	private static int mindist[] = new int[n];
	private static boolean inflag[] = new boolean[n];
	private static Lock L[] = new ReentrantLock[n];

	public static void main(String[] args) {
		int point[][] = new int[n][2];
		int i, j, temp = 0, dist = 0;
		Random rand = new Random(500);
		for (i = 0; i < n; i++) {
			temp = rand.nextInt(1000);
			point[i][0] = temp;
			point[i][1] = temp;

		}

		for (i = 0; i < n; i++) {
			inflag[i] = false;
		}
		mindist[0] = 0;
		for (i = 1; i < n; i++) {
			mindist[i] = infinity;
		}

		/*
		 * for (i = 0; i < n; i++) for (j = 0; j <= i; j++) if (i == j)
		 * weight[i][j] = infinity; else { temp = point[i][0] - point[j][0];
		 * dist = temp * temp; temp = point[i][1] - point[j][1]; dist = dist +
		 * temp * temp; weight[i][j] = dist; weight[j][i] = dist;
		 * 
		 * }
		 * 
		 */

		/*
		 * for (i = 0; i < n; i++) { for (j = 0; j < n; j++) {
		 * System.out.print(weight[i][j] + " "); } System.out.println();
		 * 
		 * }
		 */

		Worker worker = new Worker(workpool, weight, mindist, inflag, L);
		int startvertex = 0;
		workpool.putWork(startvertex);

		Date startseqtime = new Date();
		worker.run();

		Date endseqtime = new Date();
		long partotaltime = endseqtime.getTime() - startseqtime.getTime();
		System.out.println("Sequential Elapsed Time:" + partotaltime);

		for (i = 0; i < n; i++) {
			System.out.print(mindist[i] + ", ");
		}
		System.out.println();
	}

}
