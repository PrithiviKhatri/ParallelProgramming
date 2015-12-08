public class SearchThread implements Runnable {

	int[] searchList;
	int[] searchElementOfList;
	int position;

	public SearchThread(int[] searchList, int[] searchElementOfList) {
		this.searchList = searchList;
		this.searchElementOfList=searchElementOfList;

	}

	@Override
	public void run() {
		for (int i = 0; i < searchElementOfList.length; i++) {
			position = mergelist.positionsearchbinary1(searchList, searchElementOfList[i]);
			Application.finallist[i + position] = searchElementOfList[i];

		}

	}

}
