package org.milan;

/**
 * @author Milan Rathod
 */
public class SealedClassesDemo {
    public static void main(String[] args) {
        System.out.println(test(new SubFinalClass()));
    }

    private static String test(SealedClass sealedClass) {
        if (sealedClass instanceof SubFinalClass) {
            return "SubFinalClass";
        } else if (sealedClass instanceof SubNonSealedClass) {
            return "SubNonSealedClass";
        }
        return null;
    }
}

abstract sealed class SealedClass
  permits SubFinalClass, SubNonSealedClass {

}

final class SubFinalClass extends SealedClass {
}

non-sealed class SubNonSealedClass extends SealedClass {
}
