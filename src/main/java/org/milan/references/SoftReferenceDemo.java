package org.milan.references;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author Milan Rathod
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        ReferenceQueue<StringBuilder> referenceQueue = new ReferenceQueue<>();
        SoftReference<StringBuilder> reference
                = new SoftReference<>(builder, referenceQueue);

        StringBuilder stringBuilder = reference.get();
        if (stringBuilder != null) {
            // GC hasn't removed the instance yet
            System.out.println("Object present!!");
        } else {
            // GC has cleared the instance
        }

        reference.clear();

        stringBuilder = reference.get();

        if (stringBuilder != null) {
            // GC hasn't removed the instance yet
        } else {
            // GC has cleared the instance
            System.out.println("Object is not present!!");
        }
    }
}
