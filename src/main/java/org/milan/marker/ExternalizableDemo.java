package org.milan.marker;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Milan Rathod
 */
public class ExternalizableDemo {

    public static void main(String[] args) {
        Person person = new Person(1, "Test", "Male");

        File file = new File("person.sr");

        try (OutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        } catch (IOException ex) {
            System.err.println("Error happened!");
        }

        try (InputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Person person1 = (Person) objectInputStream.readObject();
            System.out.println("Person object: " + person1);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error happened!");
            file.deleteOnExit();
        }
    }
}

class Person implements Externalizable {

    private int id;
    private String name;
    private String gender;

    public Person(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Person() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name + "xyz");
        out.writeObject("abc" + gender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (int) in.readObject();

        name = (String) in.readObject();

        gender = (String) in.readObject();

        if (!name.endsWith("xyz") && !gender.startsWith("abc")) {
            throw new IOException("Corrupted data!");
        }

        name = name.substring(0, name.length() - 3);
        gender = gender.substring(3);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
