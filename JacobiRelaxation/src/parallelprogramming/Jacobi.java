package parallelprogramming;

import java.util.stream.IntStream;

public class Jacobi {

	float A[][];
	float B[][];
	boolean localDone;

	public Jacobi(int start, int end, float[][] A, float[][] B) {
		// this.start = start;
		// this.end = end;
		this.A = A;
		this.B = B;
	}

	public Jacobi(float[][] A, float[][] B) {
		this.A = A;
		this.B = B;
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

	public void streamRun() {
		do {
			Application.globalDone = true;

			IntStream.range(1, Application.n).parallel().forEach(i -> {
				for (int j = 1; j < Application.n; j++) {
					B[i][j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4;

				}
			});
			IntStream.range(1, Application.n).parallel().forEach(i -> {
				float change;
				for (int j = 1; j < Application.n; j++) {
					change = Math.abs(B[i][j] - A[i][j]);
					if (change > Application.tolerance) {
						Application.globalDone = false;
					}
					A[i][j] = B[i][j];
				}
			});
		} while (!Application.globalDone);

	}
}