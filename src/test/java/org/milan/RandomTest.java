package org.milan;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author Milan Rathod
 */
public class RandomTest {

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomLongUnbounded_thenCorrect() {
        final long generatedLong = new Random().nextLong();

        System.out.println(generatedLong);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomLongBounded_thenCorrect() {
        final long leftLimit = 1L;
        final long rightLimit = 10L;
        final long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        System.out.println(generatedLong);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomIntegerUnbounded_thenCorrect() {
        final int generatedInteger = new Random().nextInt();

        System.out.println(generatedInteger);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomIntegerBounded_thenCorrect() {
        final int leftLimit = 1;
        final int rightLimit = 10;
        final int generatedInteger = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));

        System.out.println(generatedInteger);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomFloatUnbounded_thenCorrect() {
        final float generatedFloat = new Random().nextFloat();

        System.out.println(generatedFloat);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomFloatBounded_thenCorrect() {
        final float leftLimit = 1F;
        final float rightLimit = 10F;
        final float generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);

        System.out.println(generatedFloat);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomDoubleUnbounded_thenCorrect() {
        final double generatedDouble = Math.random();

        System.out.println(generatedDouble);
    }

    @Test
    public void givenUsingPlainJava_whenGeneratingRandomDoubleBounded_thenCorrect() {
        final double leftLimit = 1D;
        final double rightLimit = 10D;
        final double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);

        System.out.println(generatedDouble);
    }

}