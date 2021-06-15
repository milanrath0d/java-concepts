package org.milan.enums;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

public class ThreadLifeCycle {

    public static void main(String[] args) {
        // usingEnumMethods();

        // usingEnumValueOf();

        // usingEnumValues();

        // usingEnumInSwitch(ThreadStates.START);
        // usingEnumInSwitch(ThreadStates.DEAD);

        // usingEnumMap();

        usingEnumSet();
    }

    private static void usingEnumSet() {
        EnumSet<ThreadState> enumSet = EnumSet.allOf(ThreadState.class);
        for (ThreadState threadState : enumSet) {
            System.out.println(Arrays.toString(ThreadState.values()));
            System.out.println("Using EnumSet, priority = " + threadState.getPriority());
        }
    }

    private static void usingEnumMap() {
        EnumMap<ThreadState, String> enumMap = new EnumMap<>(ThreadState.class);
        enumMap.put(ThreadState.START, "thread is started");
        enumMap.put(ThreadState.RUNNING, "thread is running");
        enumMap.put(ThreadState.WAITING, "thread is waiting");
        enumMap.put(ThreadState.DEAD, "thread is dead");

        Set<ThreadState> keySet = enumMap.keySet();
        for (ThreadState key : keySet) {
            System.out.println("key=" + key.toString() + ":: value=" + enumMap.get(key));
        }
    }

    private static void usingEnumInSwitch(ThreadState threadState) {
        switch (threadState) {
            case START:
                System.out.println("START thread");
                break;
            case WAITING:
                System.out.println("WAITING thread");
                break;
            case RUNNING:
                System.out.println("RUNNING thread");
                break;
            case DEAD:
                System.out.println("DEAD thread");
        }
    }

    private static void usingEnumValues() {
        ThreadState[] threadStates = ThreadState.values();

        for (ThreadState ThreadStates : threadStates) {
            System.out.println(ThreadStates.toString() + "::priority=" + ThreadStates.getPriority());
        }
    }

    private static void usingEnumValueOf() {
        ThreadState threadState = Enum.valueOf(ThreadState.class, "START");
        System.out.println("th priority=" + threadState.getPriority());
    }

    private static void usingEnumMethods() throws IOException {
        ThreadState threadState = ThreadState.DEAD;
        System.out.println("priority is:" + threadState.getPriority());

        threadState = ThreadState.DEAD;
        System.out.println("Using overridden method." + threadState);

        threadState = ThreadState.START;
        System.out.println("Using overridden method." + threadState);
        threadState.setPriority(10);
        System.out.println("Enum Constant variable changed priority value=" + threadState.getPriority());
        threadState.close();
    }

    public enum ThreadState implements Closeable {
        START(1) {
            @Override
            public String toString() {
                return "START implementation. Priority=" + getPriority();
            }

            @Override
            public String getDetail() {
                return "START";
            }
        },
        RUNNING(2) {
            @Override
            public String getDetail() {
                return "RUNNING";
            }
        },
        WAITING(3) {
            @Override
            public String getDetail() {
                return "WAITING";
            }
        },
        DEAD(4) {
            @Override
            public String getDetail() {
                return "DEAD";
            }
        };

        private int priority;

        public abstract String getDetail();

        // Enum constructors should always be private.
        ThreadState(int i) {
            priority = i;
        }

        // Enum can have methods
        public int getPriority() {
            return this.priority;
        }

        public void setPriority(int p) {
            this.priority = p;
        }

        // Enum can override functions
        @Override
        public String toString() {
            return "Default ThreadStatesConstructors implementation. Priority=" + getPriority();
        }

        @Override
        public void close() throws IOException {
            System.out.println("Close of Enum");
        }
    }

}

