package org.milan.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link MacAddress}
 *
 * @author Milan Rathod
 */
class MacAddressTest {

    @Test
    void getMacAddress() {
        MacAddress macAddress = new MacAddress();

        String result = macAddress.getMacAddress();

        assertNotNull(result);
    }
}