package parallelprogramming;

import java.util.Date;

public class Application {
	public static int n = 10;
	static double tolerance = 0.1;
	static float A[][] = new float[n + 1][n + 1];
	static float B[][] = new float[n + 1][n + 1];
	static boolean globalDone = true;

	public static void main(String[] args) {
		intializeArray(A);
		// printArray(A);
		
		// Sequential code
		Jacobi seqjacobi = new Jacobi(A, B);
		Date startseqtime = new Date();
		seqjacobi.sequentialrun();
		Date endseqtime = new Date();
		Long totalseqtime = endseqtime.getTime() - startseqtime.getTime();
		System.out.println("total seq time is " + totalseqtime);
		// printArray(A);

		// implementation with Stream
		System.out.println("-----------------------------------------------------------------");
		intializeArray(A);
		Date startStreamtime = new Date();
		new Jacobi(A, B).streamRun();
		Date endStreamtime = new Date();
		Long totalStreamtime = endStreamtime.getTime() - startStreamtime.getTime();
		System.out.println("total parrallel time with Stream is " + totalStreamtime);
		System.out.println("speed up with Stream is " + (float) totalseqtime / totalStreamtime);
		//printArray(A);
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