package org.milan.nested;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class CommaSeparatedCustomersTest {

    @Test
    void whenCustomerListJoined_thenReturnsJoinedNames() {
        Customer.List customerList = new CommaSeparatedCustomers();
        customerList.add(new Customer("customer1"));
        customerList.add(new Customer("customer2"));
        assertEquals("customer1,customer2", customerList.getCustomerNames());
    }

}