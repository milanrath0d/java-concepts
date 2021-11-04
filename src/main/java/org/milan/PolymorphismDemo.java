package org.milan;

/**
 * @author Milan Rathod
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        A a = new B();

        a.run(null);
    }
}

class A {

    /*
    * Commented out for resolving ambiguous method calls error
    public void run(String str) {
        System.out.println("Inside str");
    }*/

    private void run(A a) {
        System.out.println("Inside A");
    }

    public void run(Object obj) {
        System.out.println("Inside obj");
    }
}

class B extends A {

    public void run(A a) {
        System.out.println("Inside B");
    }

}
