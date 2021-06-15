package org.milan.function;

import java.util.function.BiFunction;

/**
 * @author Milan Rathod
 */
public class BiFunctionDemo {

    public int find(String str, Integer value, BiFunction<String, Integer, Integer> biFunction) {
        return biFunction.apply(str, value);
    }
}
