package org.milan.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Milan Rathod
 */
public class Pizza {

    private static final EnumSet<PizzaStatus> deliveredPizzaStatuses = EnumSet.of(PizzaStatus.DELIVERED);

    private PizzaStatus pizzaStatus;

    public PizzaStatus getStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public boolean isDeliverable() {
        return this.pizzaStatus.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " + this.getStatus().getTimeToDelivery() + " days");
    }

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input) {
        return input.stream()
                .filter(pizza -> !deliveredPizzaStatuses.contains(pizza.getStatus()))
                .collect(Collectors.toList());
    }

    public static EnumMap<PizzaStatus, List<Pizza>> groupPizzasByStatus(List<Pizza> pizzaList) {
        return pizzaList.stream()
                .collect(Collectors.groupingBy(Pizza::getStatus, () -> new EnumMap<>(PizzaStatus.class), Collectors.toList()));
    }

    public void deliver() {
        if (isDeliverable()) {
            PizzaDeliveryConfiguration.getInstance().getPizzaDeliveryStrategy().deliver(this);
            this.setPizzaStatus(PizzaStatus.DELIVERED);
        }
    }

    public enum PizzaStatus {
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private final int timeToDelivery;

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }
    }
}
