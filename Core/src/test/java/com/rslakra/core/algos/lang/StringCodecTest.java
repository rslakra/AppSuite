package com.rslakra.core.algos.lang;

import com.rslakra.core.algos.text.StringCodec;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 4/21/21 5:11 PM
 */
public class StringCodecTest {

    @Test
    public void testStringCodec() {
        StringCodec stringCodec = new StringCodec();
        List<String> listStrings = Arrays.asList("Rohtash", "Singh", "Lakra", "7#Rohtash5#Singh5#Lakra");
        System.out.println(listStrings);
        String encoded = stringCodec.encode(listStrings);
        System.out.println(encoded);
        List<String> result = stringCodec.decode(encoded);
        System.out.println(result);
    }
}
