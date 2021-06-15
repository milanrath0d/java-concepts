package org.milan.misc;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link UUIDGenerator}
 *
 * @author Milan Rathod
 */
class UUIDGeneratorTest {

    @Test
    void generateType4UUID() {
        UUID uuid = UUIDGenerator.generateType4UUID();

        assertEquals(36, uuid.toString().length());
        assertEquals(4, uuid.version());
        assertEquals(2, uuid.variant());
    }
}