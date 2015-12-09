import java.util.Date;

public class Application {

	public static final int n = 2000000;
	static int[] finallist = new int[2 * n];

	public static void main(String[] args) {
		int i, position;
		int[] list1 = new int[n];
		int[] list2 = new int[n];
		/* input sorted lists X and Y */
		for (i = 0; i < n; i++)
			list1[i] = i * 2;
		for (i = 0; i < n; i++)
			list2[i] = 1 + i * 2;

		Date startseqtime = new Date();

		for (i = 0; i < list1.length; i++) {
			position = mergelist.positionsearchbinary1(list2, list1[i]);
			finallist[i + position] = list1[i];

		}

		for (i = 0; i < list2.length; i++) {
			position = mergelist.positionsearchbinary2(list1, list2[i]);
			finallist[i + position] = list2[i];
		}

		Date endseqtime = new Date();
		long seqtotaltime = endseqtime.getTime() - startseqtime.getTime();
		System.out.println("Elapsed Sequential Time::" + seqtotaltime);

		SearchThread t1 = new SearchThread(list2, list1);
		SearchThread t2 = new SearchThread(list1, list2);
		Thread threadX = new Thread(t1);
		Thread threadY = new Thread(t2);

		Date startpartime = new Date();

		threadX.start();
		threadY.start();

		try {
			threadX.join();
			threadY.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endpartime = new Date();

		long partotaltime = endpartime.getTime() - startpartime.getTime();
		System.out.println("Elapsed Parallel Time::" + partotaltime);
		System.out.println("Speed Up is " + (float)seqtotaltime / partotaltime);
		
//		System.out.println("printing the sorted array");
//
//		for (i = 0; i < finallist.length; i++) {
//			System.out.println(finallist[i]);
//		}

	}

}
