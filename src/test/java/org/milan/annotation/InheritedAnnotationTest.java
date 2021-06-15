package org.milan.annotation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class InheritedAnnotationTest {

    @Test
    void testInheritedAnnotation() {
        DerivedClass derivedClass = new DerivedClass();
        InheritedAnnotation inheritedAnnotation = derivedClass.getClass().getAnnotation(InheritedAnnotation.class);

        assertNotNull(inheritedAnnotation);
    }

    @Test
    void testNotInheritedAnnotation() {
        NotDerivedClass notDerivedClass = new NotDerivedClass();
        InheritedAnnotation inheritedAnnotation = notDerivedClass.getClass().getAnnotation(InheritedAnnotation.class);

        assertNull(inheritedAnnotation);
    }

}