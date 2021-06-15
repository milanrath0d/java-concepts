package org.milan.annotation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RepeatableAnnotationTest {

    @Test
    void testRepeatableAnnotation() {
        Hint annotation = RepeatableAnnotation.class.getAnnotation(Hint.class);

        assertNull(annotation);

        Hints hints = RepeatableAnnotation.class.getAnnotation(Hints.class);

        assertEquals(2, hints.value().length);

        Hint[] hintsArray = RepeatableAnnotation.class.getAnnotationsByType(Hint.class);

        assertEquals(2, hintsArray.length);
    }

}