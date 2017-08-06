package com.ivashchenko.practice4.task44;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class is used to generate HashSet with numbers from range.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class SetRangeGenerator extends AbstractRangeGenerator {
    @Override
    public Collection<Number> getRandomRange(int numberOfElements, int start, int end) throws Exception{
        numbers = new HashSet<>();
        if (numberOfElements > end - start) {
           throw new Exception("No enough elements to generate set of size=" + numberOfElements);
        }
        while(numbers.size() < numberOfElements) {
            numbers.add(start + random.nextInt(end - start));
        }
        return numbers;
    }
}
