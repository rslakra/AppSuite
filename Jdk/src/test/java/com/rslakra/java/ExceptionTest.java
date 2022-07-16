package com.rslakra.java;

import com.rslakra.core.MathUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/16/19 11:53 AM
 */
public class ExceptionTest {

    private void throwException() {
        throw new RuntimeException("ExceptionTest-throwException");
    }


    @Test
    public void testThrowException() {
        try {
            throwException();
        } catch (RuntimeException ex) {
            Assert.assertEquals(true, true);
        }
    }

    /**
     *
     */
    @Test
    public void testListEvenNumbers() {
        int count = 5;
        List<Integer> evenNumbers = MathUtils.listEvenNumbers(count);
        Assert.assertEquals(count, evenNumbers.size());
    }

    @Test
    public void testFilterEvenNumbers() {
        List<Integer> validEvenNumbers = null;
        try {
            int numbers = 5;
            List<Integer> evenNumbers = MathUtils.listEvenNumbers(numbers);
            evenNumbers.add(5);
            validEvenNumbers = MathUtils.filterEvenNumbers(evenNumbers);
        } catch (RuntimeException ex) {
            Assert.assertEquals(null, validEvenNumbers);
        }
    }

}
