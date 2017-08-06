package com.ivashchenko.practice4.task44;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is used to generate ArrayList with Numbers from range.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class ListRangeGenerator extends AbstractRangeGenerator {
    @Override
    public Collection<Number> getRandomRange(int numberOfElements, int start, int end) {
        numbers = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            numbers.add(start + random.nextInt(end - start));
        }
        return numbers;
    }
}
