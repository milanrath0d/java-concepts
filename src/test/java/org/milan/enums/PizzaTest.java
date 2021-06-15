package org.milan.enums;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Pizza}
 *
 * @author Milan Rathod
 */
class PizzaTest {

    @Test
    public void givenPizzaOrder_whenReady_thenDeliverable() {
        Pizza testPz = new Pizza();
        testPz.setPizzaStatus(Pizza.PizzaStatus.READY);
        assertTrue(testPz.isDeliverable());
    }

    @Test
    public void givenPizzaOrders_whenRetrievingUnDeliveredPzs_thenCorrectlyRetrieved() {
        List<Pizza> pzList = getPizzas();

        List<Pizza> undeliveredPzs = Pizza.getAllUndeliveredPizzas(pzList);
        assertEquals(3, undeliveredPzs.size());
    }

    @Test
    public void givenPizzaOrders_whenGroupByStatusCalled_thenCorrectlyGrouped() {
        List<Pizza> pzList = getPizzas();

        EnumMap<Pizza.PizzaStatus, List<Pizza>> map = Pizza.groupPizzasByStatus(pzList);
        assertEquals(1, map.get(Pizza.PizzaStatus.DELIVERED).size());
        assertEquals(2, map.get(Pizza.PizzaStatus.ORDERED).size());
        assertEquals(1, map.get(Pizza.PizzaStatus.READY).size());
    }

    @Test
    public void whenDelivered_thenPizzaGetsDeliveredAndStatusChanges() {
        Pizza pz = new Pizza();
        pz.setPizzaStatus(Pizza.PizzaStatus.READY);
        pz.deliver();
        assertSame(pz.getStatus(), Pizza.PizzaStatus.DELIVERED);
    }

    private List<Pizza> getPizzas() {
        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setPizzaStatus(Pizza.PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setPizzaStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz3 = new Pizza();
        pz3.setPizzaStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setPizzaStatus(Pizza.PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        return pzList;
    }

}