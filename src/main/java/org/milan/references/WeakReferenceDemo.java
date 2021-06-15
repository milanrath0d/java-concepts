package org.milan.references;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Milan Rathod
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object referent = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference<Object> weakReference1 = new WeakReference<>(referent);
        WeakReference<Object> weakReference2 = new WeakReference<>(referent, referenceQueue);

        referent = null;

        System.gc();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object referent3 = weakReference2.get();
        if (referent3 != null) {
            // GC hasn't removed the instance yet
        } else {
            // GC has cleared the instance
            System.out.println("removed!!");
        }

    }
}


