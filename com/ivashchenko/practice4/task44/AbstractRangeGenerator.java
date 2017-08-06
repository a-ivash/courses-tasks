package com.ivashchenko.practice4.task44;

import java.util.Collection;
import java.util.Random;

/**
 * Abstract class declaring method to interact with.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public abstract class AbstractRangeGenerator {
    Random random = new Random();
    Collection<Number> numbers;
    public abstract Collection<Number> getRandomRange(int numberOfElements, int start, int end) throws Exception;
}
