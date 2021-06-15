package org.milan.collections;

import java.util.Objects;

/**
 * @author Milan Rathod
 */
public class Human {

    private String name;

    private int age;

    public Human() {
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Human [name=" + name + ", age=" + age + "]";
    }

    public static int compareByNameThenAge(final Human first, final Human second) {
        if (first.name.equals(second.name)) {
            return Integer.compare(first.age, second.age);
        } else {
            return first.name.compareTo(second.name);
        }
    }
}
