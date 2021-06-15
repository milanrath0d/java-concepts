package org.milan.enums;

/**
 * Configuration for pizza delivery
 *
 * @author Milan Rathod
 */
public enum PizzaDeliveryConfiguration {
    INSTANCE;

    PizzaDeliveryConfiguration() {
        // Do the configuration initialization here
    }

    private final PizzaDeliveryStrategy pizzaDeliveryStrategy = PizzaDeliveryStrategy.NORMAL;

    public static PizzaDeliveryConfiguration getInstance() {
        return INSTANCE;
    }

    public PizzaDeliveryStrategy getPizzaDeliveryStrategy() {
        return pizzaDeliveryStrategy;
    }
}
