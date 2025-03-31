package org.milan.thread;

/**
 * @author Milan Rathod
 */
public class BlockedThread {
  public static void main(String[] args) {
    Thread t1 = new Thread(new BlockRunnable());
    Thread t2 = new Thread(new BlockRunnable());

    t1.start();
    t2.start();

    System.out.println(t1.getState());
    System.out.println(t2.getState());
    System.exit(0);
  }

}


class BlockRunnable implements Runnable {

  @Override
  public void run() {
    heavyProcessing();
  }

  private static synchronized void heavyProcessing() {
    while (true) {
      // Trying to mimic heavy processing
    }
  }
}
