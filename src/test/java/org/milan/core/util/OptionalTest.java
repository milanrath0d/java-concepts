package org.milan.core.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Use cases for {@link java.util.Optional}
 *
 * @author Milan Rathod
 */
public class OptionalTest {

    @Test
    public void whenCreatesEmptyOptional() {
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    @Test
    public void givenNonNull_whenCreatesNonNullable() {
        String name = "optional";
        Optional<String> optional = Optional.of(name);
        assertTrue(optional.isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate() {
        String name = null;
        Optional.of(name);
    }

    @Test
    public void givenNonNull_whenCreatesNullable() {
        String name = "optional";
        Optional<String> opt = Optional.ofNullable(name);
        assertTrue(opt.isPresent());
    }

    @Test
    public void givenNull_whenCreatesNullable() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        assertFalse(opt.isPresent());
    }

    @Test
    public void givenAnEmptyOptional_thenIsEmptyBehavesAsExpected() {
        Optional<String> opt = Optional.of("optional");
        assertFalse(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    @Test
    public void givenOptional_whenIfPresentWorks() {
        Optional<String> opt = Optional.of("optional");
        opt.ifPresent(name -> System.out.println(name.length()));
    }

    @Test
    public void whenOrElseWorks() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("john");
        assertEquals("john", name);
    }

    @Test
    public void whenOrElseGetWorks() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
        assertEquals("john", name);
    }

    @Test
    public void whenOrElseGetAndOrElseOverlap() {
        String text = null;

        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }

    @Test
    public void whenOrElseGetAndOrElseDiffer() {
        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowWorks() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
    }

    @Test
    public void givenOptional_whenGetsValue() {
        Optional<String> opt = Optional.of("optional");
        String name = opt.get();
        assertEquals("optional", name);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenOptionalWithNull_whenGetThrowsException() {
        Optional<String> opt = Optional.ofNullable(null);
        String name = opt.get();
    }

    @Test
    public void whenOptionalFilterWorks() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }

    @Test
    public void givenOptional_whenMapWorks() {
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    @Test
    public void givenOptional_whenMapWorks2() {
        String name = "optional";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional
                .map(String::length)
                .orElse(0);
        assertEquals(8, len);
    }


    private String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}