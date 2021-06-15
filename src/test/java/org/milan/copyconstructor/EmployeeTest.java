package org.milan.copyconstructor;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class for {@link Employee}
 *
 * @author Milan Rathod
 */
class EmployeeTest {

    @Test
    void givenCopyConstructor_whenDeepCopy_thenDistinct() {
        Date d1 = new Date(123);
        Employee e1 = new Employee(1, "Test", d1);
        Employee e2 = new Employee(e1);
        assertEquals(d1, e1.getStartDate());
        assertEquals(d1, e2.getStartDate());

        d1.setTime(456);
        assertEquals(d1, e1.getStartDate());
        assertNotEquals(d1, e2.getStartDate());
    }

    @Test
    void givenCopyMethod_whenCopy_thenDistinct() {
        Date d1 = new Date(123);
        Employee e1 = new Employee(1, "Test", d1);
        Employee e2 = e1.copy();
        assertEquals(d1, e1.getStartDate());
        assertEquals(d1, e2.getStartDate());

        d1.setTime(456);
        assertEquals(d1, e1.getStartDate());
        assertNotEquals(d1, e2.getStartDate());
    }

}