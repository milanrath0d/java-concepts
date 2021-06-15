package org.milan.enums;

/**
 * Pizza delivery strategy
 *
 * @author Milan Rathod
 */
public enum PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public String deliver(Pizza pizza) {
            return "Pizza will be delivered in express mode";
        }
    },
    NORMAL {
        @Override
        public String deliver(Pizza pizza) {
            return "Pizza will be delivered in normal mode";
        }
    };

    public abstract String deliver(Pizza pizza);

}
