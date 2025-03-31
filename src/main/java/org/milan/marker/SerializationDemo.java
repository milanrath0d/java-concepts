package org.milan.marker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Serializable Demo
 *
 * @author Milan Rathod
 */
public class SerializationDemo {

    public static void main(String[] a) {
        Square square = new Square(15, 20, 25);
        System.out.println("Before Serialization: " + square.getSize() + " " + square.getDiagonal() + " " + square.getTest());

        // Serialization process
        try (OutputStream outputStream = new FileOutputStream("test.sr");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(square);
            objectOutputStream.flush();
        } catch (IOException ex) {
            System.err.println("Error happened!");
        }

        // De-serialization process
        try (InputStream inputStream = new FileInputStream("test.sr");
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Square deSerializedSquare = (Square) objectInputStream.readObject();
            System.out.println("After Serialization: " + deSerializedSquare.getSize() + " " + deSerializedSquare.getDiagonal() + " " + deSerializedSquare.getTest());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error happened!");
        }
    }
}

class Shape {
    private int size;

    public Shape(int size) {
        this.size = size;
    }

    public Shape() {
        System.out.println("Default shape constructor");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class Square extends Shape implements Serializable {

    private static final long serialVersionUID = 1L;

    private int diagonal;

    private transient int test;

    public Square(int size, int diagonal, int test) {
        super(size);
        this.diagonal = diagonal;
        this.test = test;
    }

    public Square() {
        System.out.println("Default square constructor");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        // Order of read and write should be same
        setSize(objectInputStream.readInt());
        setTest(objectInputStream.readInt());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(getSize());
        objectOutputStream.writeInt(getTest());
    }

    public int getDiagonal() {
        return diagonal;
    }

    public int getTest() {
        return test;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public void setTest(int test) {
        this.test = test;
    }
}