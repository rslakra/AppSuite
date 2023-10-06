package com.rslakra.appsuite.algos;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 2:01 PM
 */
public class AlgoUtilsTest {


    @Test
    public void testHashCode() {
        List<String> strings = Arrays.asList("abc", "acb", "bac", "cab");
        strings.forEach(str -> System.out.println("str:" + str + ", hashCode: " + Objects.hash(str)));
    }

    @Test
    public void testConvertLeetCode2DStringArrayInputIntoJavaArray() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        AlgoUtils.printArray(nums);

        String
            input1 =
            "[\"zDkA\",\"GfAj\",\"lt\"],[\"GfAj\",\"rtupD\",\"og\",\"l\"],[\"rtupD\",\"IT\",\"jGcew\",\"ZwFqF\"],[\"og\",\"yVobt\",\"EjA\",\"piUyQ\"],[\"IT\",\"XFlc\",\"W\",\"rB\"],[\"l\",\"GwQg\",\"shco\",\"Dub\",\"KwgZq\"],[\"oXMG\",\"uqe\"],[\"sNyV\",\"WbrP\"]";
        String input2 = "[\"A\",\"B\"],[\"C\"],[\"B\",\"C\"],[\"D\"]";
        AlgoUtils.printListList(AlgoUtils.convertLeetCode2DStringArrayInputIntoJavaArray(input1));
        AlgoUtils.printListList(AlgoUtils.convertLeetCode2DStringArrayInputIntoJavaArray(input2));
        AlgoUtils.print(AlgoUtils.convertLeetCode1DStringArrayInputIntoJavaArray(
            "[\"abcsi\",\"abyzjgj\",\"advz\",\"ag\",\"agkgdkob\",\"agpr\",\"ail\"]"));
        AlgoUtils.print2DIntArray(AlgoUtils.convertLeetCodeIrregularLengths2DArrayInputIntoJavaArray(
            "[448,931,123,345],[889],[214,962],[576,746,897]"));
    }
}
