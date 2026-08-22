package org.milan.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class BiFunctionDemoTest {

    @Test
    void biFunctionTest() {
        BiFunctionDemo biFunctionDemo = new BiFunctionDemo();

        int result = biFunctionDemo.find("test", 100,
                (str, value) -> str.equals("test") ? 200 : value);

        assertEquals(200, result);
    }
}
