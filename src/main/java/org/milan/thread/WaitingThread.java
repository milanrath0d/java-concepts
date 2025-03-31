package org.milan.thread;

/**
 * @author Milan Rathod
 */
public class WaitingThread implements Runnable {

  public static Thread t1;

  public static void main(String[] args) {
    t1 = new Thread(new WaitingThread());
    t1.start();
  }

  @Override
  public void run() {
    Thread t2 = new Thread(new WaitingRunnable());
    t2.start();

    try {
      t2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }

  }
}


class WaitingRunnable implements Runnable {

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }

    System.out.println(WaitingThread.t1.getState());
  }
}
