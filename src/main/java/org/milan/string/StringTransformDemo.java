package org.milan.string;

/**
 * @author Milan Rathod
 */
public class StringTransformDemo {
    public static void main(String[] args) {
        String text = "Test";
        final String transformed = text.transform(s -> new StringBuilder(s).reverse().toString());
        System.out.println(transformed);

        final String indent = text.indent(10);
        System.out.println(indent);

        final String indent1 = indent.indent(-20);
        System.out.println(indent1);
    }
}
