package parallelprogramming;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

public class Application {
	public static int n = 6;
	public static int numiter = 100;
	static double tolerance = 0.01;
	static float A[][] = new float[n + 1][n + 1];
	static float B[][] = new float[n + 1][n + 1];
	static boolean globalDone = true;
	// static int numofprocesor = Runtime.getRuntime().availableProcessors();
	static int numofprocesor = 2;

	public static void main(String[] args) {
		int i, j;
		intializeArray(A);
		printArray(A);

		// Sequential code
		Jacobi seqjacobi = new Jacobi(A, B);

		Date startseqtime = new Date();

		// seqjacobi.sequentialrun();

		Date endseqtime = new Date();
		Long totalseqtime = endseqtime.getTime() - startseqtime.getTime();
		System.out.println("total seq time is " + totalseqtime);
		// printArray(A);

		// parallel version
		int start = 0, end = 0;
		intializeArray(A);

		int width = n / numofprocesor;
		Jacobi[] jacobi = new Jacobi[numofprocesor];
		Thread[] thread = new Thread[numofprocesor];
		for (i = 0; i < numofprocesor; i++) {
			start = i * width;
			if (start == 0) {
				start = 1;
				end = width;
			} else
				end = start + width;
			System.out.println("start " + start);
			System.out.println("end " + end);
			thread[i] = new Thread(new Jacobi(start, end, A, B));
			thread[i].start();

		}
		for (i = 0; i < numofprocesor; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printArray(A);

	}

	static void printArray(float[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();

	}

	static void intializeArray(float[][] A) {
		/* Initialising the A array */
		int i, j;
		for (i = 1; i < n; i++) {
			for (j = 1; j < n; j++)
				A[i][j] = 0;
		}
		for (i = 0; i <= n; i++) {
			A[i][0] = 10;
			A[0][i] = 10;
			A[n][i] = 10;
			A[i][n] = 10;
		}
	}
}
