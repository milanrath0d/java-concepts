package org.milan;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Milan Rathod
 */
public class ObjectsComparisonTest {

    @Test
    void compareUsing_ObjectEquals() {
        Person firstPerson = new Person("TestFirstName", "TestLastName");
        Person secondPerson = new Person("TestFirstName", "TestLastName");
        Person thirdPerson = new Person("TestFirstName2", "TestLastName2");

        assertTrue(firstPerson.equals(secondPerson));
        assertFalse(firstPerson.equals(thirdPerson));
    }

    @Test
    void compareUsing_ObjectEquals_Null() {
        Person firstPerson = new Person("TestFirstName", "TestLastName");
        Person secondPerson = null;

        assertFalse(firstPerson.equals(secondPerson));

        assertThrows(NullPointerException.class, () -> secondPerson.equals(firstPerson));
    }

    @Test
    void compareUsing_ObjectsEqualsNull() {
        Person firstPerson = new Person("TestFirstName", "TestLastName");
        Person secondPerson = null;

        assertFalse(Objects.equals(firstPerson, secondPerson));
        assertFalse(Objects.equals(secondPerson, firstPerson));
        assertTrue(Objects.equals(secondPerson, null));
    }


    class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person that = (Person) o;
            return firstName.equals(that.firstName) &&
                    lastName.equals(that.lastName);
        }
    }
}
