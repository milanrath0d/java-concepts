package org.milan.nested;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticEnclosingTest {

    @Test
    void test() {
        StaticEnclosing.StaticNested staticNested = new StaticEnclosing.StaticNested();
        String result = staticNested.run();
        assertEquals("Hello", result);
    }

}