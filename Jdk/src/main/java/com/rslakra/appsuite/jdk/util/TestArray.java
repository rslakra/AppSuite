/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.appsuite.jdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2018-02-10 01:21:27 PM
 * @since 1.0.0
 */
public class TestArray {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestArray.class);

    /**
     * Input: nums = [1,1,2,2,2,3] Output: [3,1,1,2,2,2]
     * <p>
     * Input: nums = [2,3,1,3,2] Output: [1,3,3,2,2]
     *
     * @param values
     */
    public int[] sortArrayByIncreasingFrequency(int[] values) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(values).forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));
        return Arrays.stream(values).boxed().sorted((k, v) -> {
            LOGGER.info("k:{}, v:{}", k, v);
            return (map.get(k) != map.get(v) ? map.get(k) - map.get(v) : v - k);
        }).mapToInt(n -> n).toArray();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestArray testArray = new TestArray();
        int[] first = {1, 1, 2, 2, 2, 3};
        int[] result = testArray.sortArrayByIncreasingFrequency(first);
        LOGGER.info("first:{}, result:{}}", first, result);
        int[] second = {2, 3, 1, 3, 2};
        result = testArray.sortArrayByIncreasingFrequency(second);
        LOGGER.info("second:{}, result:{}}", second, result);
    }

}
