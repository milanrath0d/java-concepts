package org.milan.thread;

/**
 * @author Milan Rathod
 */
public class TimedWaitingThread {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new TimedWaitingRunnable());
    t1.start();

    // This sleep will give enough time to thread scheduler to start processing t1 thread
    Thread.sleep(1000);
    System.out.println(t1.getState());
  }
}


class TimedWaitingRunnable implements Runnable {

  @Override
  public void run() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
