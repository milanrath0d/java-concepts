package org.milan.misc;

import java.io.IOException;

/**
 * @author Milan Rathod
 */
public class ByteArray {

    public static void main(String[] args) throws IOException {
        try {
            throw new Base();

        } catch (Derived ex) {
            System.out.println("Derviced");
        } catch (Base ex) {
            System.out.println("Base");
        }

    }
}

class Base extends RuntimeException {

}

class Derived extends Base {

}