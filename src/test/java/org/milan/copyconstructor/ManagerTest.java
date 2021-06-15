package org.milan.copyconstructor;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Manager}
 *
 * @author Milan Rathod
 */
class ManagerTest {

    @Test
    void givenCopyConstructor_whenDeepCopy_thenDistinct() {
        Date startDate = new Date(123);
        Employee e1 = new Employee(1, "Test", startDate);
        Employee e2 = new Employee(e1);
        List<Employee> directReports = new ArrayList<>();
        directReports.add(e1);
        directReports.add(e2);

        Manager m1 = new Manager(1, "Test Manager", startDate, directReports);
        Manager m2 = new Manager(m1);
        List<Employee> directReports1 = m1.getDirectReports();
        List<Employee> directReports2 = m2.getDirectReports();
        assertEquals(directReports1.size(), directReports2.size());
        assertArrayEquals(directReports1.toArray(), directReports2.toArray());

        // clear m1's direct reports list. m2's list should not be affected
        directReports.clear();
        directReports1 = m1.getDirectReports();
        directReports2 = m2.getDirectReports();
        assertEquals(0, directReports1.size());
        assertEquals(2, directReports2.size());
    }

    @Test
    void givenCopyMethod_whenCopy_thenDistinct() {
        Date startDate = new Date(123);
        Employee e1 = new Employee(1, "Test", startDate);
        Employee e2 = new Employee(e1);
        List<Employee> directReports = new ArrayList<>();
        directReports.add(e1);
        directReports.add(e2);

        // a Manager object whose declaration type is Employee.
        Employee source = new Manager(1, "Test Manager", startDate, directReports);
        Employee clone = source.copy();

        // after copy, clone should be still a Manager object.
        assertTrue(clone instanceof Manager);
        List<Employee> directReports1 = ((Manager) source).getDirectReports();
        List<Employee> directReports2 = ((Manager) clone).getDirectReports();
        assertEquals(directReports1.size(), directReports2.size());
        assertArrayEquals(directReports1.toArray(), directReports2.toArray());

        // clear source's direct reports list. clone's list should not be affected
        directReports.clear();
        directReports1 = ((Manager) source).getDirectReports();
        directReports2 = ((Manager) clone).getDirectReports();
        assertEquals(0, directReports1.size());
        assertEquals(2, directReports2.size());
    }

}