package pp;

public class TestMain {

	public static void main(String[] args) {

		Stack stack = new Stack();

		Pushthread pushthread1 = new Pushthread(stack, 3);
		Pushthread pushthread2 = new Pushthread(stack, 11);
		// Pushthread pushthread3 = new Pushthread(stack, 13);
		// Pushthread pushthread4 = new Pushthread(stack, 7);

		Popthread popthread1 = new Popthread(stack);
		Popthread popthread2 = new Popthread(stack);
		Thread t1 = new Thread(pushthread1);
		Thread t2 = new Thread(pushthread2);

		// Thread t3 = new Thread(pushthread3);
		// Thread t4 = new Thread(pushthread4);

		Thread t5 = new Thread(popthread1);
		Thread t6 = new Thread(popthread2);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("stack before pop is " + stack);
		t5.start();
		t6.start();

		try {

			t5.join();
			t6.join();
			System.out.println("popped by t5 is " + popthread1.y);
			System.out.println("popped by t6 is " + popthread2.y);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("stack after pop is " + stack);

	}

}
