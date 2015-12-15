package parallelprogramming;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Jacobi implements Runnable {

	int start, end;
	float A[][];
	float B[][];
	boolean localDone;

	static CyclicBarrier barrier = new CyclicBarrier(Application.numofprocesor);

	public Jacobi(int start, int end, float[][] A, float[][] B) {
		this.start = start;
		this.end = end;
		this.A = A;
		this.B = B;
	}

	public Jacobi(float[][] A, float[][] B) {
		this.A = A;
		this.B = B;
	}

	/*
	 * void computeGlobaldone(boolean done) { Application.globalDone =
	 * this.localDone && Application.globalDone;
	 * 
	 * }
	 */

	@Override
	public void run() {
		int i, j;
		float maxchange, change;
		do {
			maxchange = 0;
			localDone = true;
			Application.globalDone=true;
			for (i = start; i < end; i++) {
				for (j = 1; j < Application.n; j++) {
					B[i][j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4;
					change = Math.abs(B[i][j] - A[i][j]);
					if (change > maxchange) {
						maxchange = change;
						if (maxchange > Application.tolerance) {
							localDone = false;
						System.out.println("here");
						}
						System.out.println("localdDone " + localDone);
					}
				}

			}

			try {
				barrier.await();

				for (i = 1; i < Application.n; i++) {
					for (j = 1; j < Application.n; j++) {

						A[i][j] = B[i][j];
					}
				}
				Application.globalDone = this.localDone && Application.globalDone;
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			//System.out.println("here");

		} while (Application.globalDone);

	}

	public void sequentialrun() {
		int i, j;
		float maxchange, change;

		do {
			maxchange = 0;
			for (i = 1; i < Application.n; i++) {
				for (j = 1; j < Application.n; j++) {
					B[i][j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4;
					change = Math.abs(B[i][j] - A[i][j]);
					if (change > maxchange)
						maxchange = change;
				}

			}

			for (i = 1; i < Application.n; i++) {
				for (j = 1; j < Application.n; j++) {

					A[i][j] = B[i][j];
				}
			}
		} while (maxchange > Application.tolerance);

	}

}
