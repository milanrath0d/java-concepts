package org.milan.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Milan Rathod
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<Object> objectList = new ArrayList<>();
        List<LargeObjectFinalizer> references = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Object object = new Object();
            objectList.add(object);
            references.add(new LargeObjectFinalizer(object, referenceQueue));
        }

        objectList = null;

        System.gc();

        Reference<?> reference;
        for (PhantomReference<Object> phantomReference : references) {
            System.out.println(phantomReference.isEnqueued());
        }

        while ((reference = referenceQueue.poll()) != null) {
            ((LargeObjectFinalizer) reference).finalizeResources();
            reference.clear();
        }
    }
}

class LargeObjectFinalizer extends PhantomReference<Object> {

    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     *
     * <p> It is possible to create a phantom reference with a {@code null}
     * queue, but such a reference is completely useless: Its {@code get}
     * method will always return {@code null} and, since it does not have a queue,
     * it will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q        the queue with which the reference is to be registered,
     *                 or {@code null} if registration is not required
     */
    public LargeObjectFinalizer(Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }

    public void finalizeResources() {
        System.out.println("Clearing...");
    }
}
