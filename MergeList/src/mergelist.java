
public class mergelist {

	public static void main(String[] args) {

		/*int list1[] = { 1, 9, 10, 11, 12, 20, 25, 27, 28, 30, 45, 47, 50 };
		// int list1[]={45,47};
		int list2[] = { 12, 15, 19, 22, 23, 29, 35, 37 };

		int size1 = list1.length;
		int size2 = list2.length;
		int finalsize = size1 + size2;
		System.out.println("final size is " + finalsize);
		int finallist[] = new int[finalsize];

		int position;
		for (int i = 0; i < size1; i++) {
			position = positionsearch(list2, list1[i]);
			finallist[i + position] = list1[i];

		}

		for (int i = 0; i < size2; i++) {
			position = positionsearch(list1, list2[i]);
			finallist[i + position] = list2[i];
		}

		System.out.println("printing the sorted array");
		for (int i = 0; i < finallist.length; i++) {
			System.out.println(finallist[i]);
		}
*/
	}

	static int positionsearch(int searchlist[], int element) {
		int i;
		for (i = 0; i < searchlist.length; i++) {
			if (element < searchlist[i])
				return i;
		}
		return i;

	}

	static int positionsearchbinary1(int searchlist[], int element) {
		int start = 0;
		int end = searchlist.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (element < searchlist[mid])
				end = mid - 1;
			else
				start = mid + 1;

		}
		return end + 1;
	}
	static int positionsearchbinary2(int searchlist[], int element) {
		int start = 0;
		int end = searchlist.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (element <= searchlist[mid])
				end = mid - 1;
			else
				start = mid + 1;

		}
		return end + 1;
	}

}
