package org.milan.function;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class SupplierDemoTest {

    @Test
    void supplierDemoTest() {
        SupplierDemo supplierDemo = new SupplierDemo();

        Supplier<Double> supplier = () -> {
            Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
            return 9d;
        };
        double result = supplierDemo.squareLazy(supplier);

        assertEquals(81.0d, result);
    }
}
