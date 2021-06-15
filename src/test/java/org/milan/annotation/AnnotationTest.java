package org.milan.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationTest {

    @Test
    void testRetention() {
        AnnotatedClass annotatedClass = new AnnotatedClass();

        Annotation[] annotations = annotatedClass.getClass().getAnnotations();

        assertEquals(2, annotations.length);
    }

}