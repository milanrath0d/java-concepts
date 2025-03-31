package org.milan.string;

/**
 * @author Milan Rathod
 */
public class TextBlockDemo {
    public static void main(String[] args) {
        textBlock();
    }

    private static void textBlock() {
        String TEXT_BLOCK_JSON = """
          {
              "name" : "Test",
              "website" : "https://www.%s.com/"
          }
          """;
        System.out.println(TEXT_BLOCK_JSON);
        System.out.println(TEXT_BLOCK_JSON.formatted("test").contains("www.test.com"));
    }

}
