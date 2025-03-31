package org.milan;

import java.util.Objects;

/**
 * @author Milan Rathod
 */
public class RecordDemo {
    public static void main(String[] args) {
        Human human = new Human("Test", 10, "Test");
        System.out.println(checkObject(human));

        Circle circle = new Circle(10);
        System.out.println(checkObject(circle));

        Point point = new Point(10, 20);
        System.out.println(beforeRecordPattern(point));
        System.out.println(afterRecordPattern(point));
    }

    private static String checkObject(Object object) {
        return switch (object) {
            case Human h -> "Name %s, age %d, profession %s".formatted(h.name(), h.age(), h.profession());
            case Circle c -> "This is a circle";
            default -> null;
        };
    }

    private static int beforeRecordPattern(Object obj) {
        int sum = 0;
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            sum = x + y;
        }
        return sum;
    }

    private static int afterRecordPattern(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            return x + y;
        }
        return 0;
    }
}

record Human(String name, int age, String profession) {
}

record Point(int x, int y) {
}

record Circle(int radius) {
    public static final String UNKNOWN = "Unknown";
}

record Person(String name, String address) {
    public Person {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
    }

    public Person(String name) {
        this(name, "Unknown");
    }
}
