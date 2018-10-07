package org.milan.core.thread;

class Q1 {
	int n;
	int i;
	boolean valueSet = false;

	synchronized int get() {
		if (!valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		} else {

			System.out.println("Got: " + i);
			i--;
			valueSet = false;
			notify();
		}
		return n;
	}

	synchronized void put(int n) {
		if (valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		} else {
			this.n = n;
			valueSet = true;
			i++;
			System.out.println("Put: " + i);

			notify();
		}
	}
}

class Producer1 implements Runnable {
	Q1 q;

	Producer1(Q1 q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int i = 0;
		while (true) {
			q.put(++i);
		}
	}
}

class Consumer1 implements Runnable {
	Q1 q;

	Consumer1(Q1 q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		while (true) {
			q.get();
		}
	}
}

public class CorrectIPDemo {
	public static void main(String args[]) {
		Q1 q = new Q1();
		new Producer1(q);
		new Consumer1(q);
		System.out.println("Press Control-C to stop.");
	}

}