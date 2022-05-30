package com.rslakra.core.algos.sort.keyindex;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:02 PM
 */
public class KeyIndexSort {

    private final StringKeyIndex keyIndex;

    /**
     * @param keyIndexType
     */
    public KeyIndexSort(KeyIndexType keyIndexType) {
        switch (keyIndexType) {
            case BINARY:
                keyIndex = new BinaryStringKeyIndex();
                break;

//            case VOWELS:
//                keyIndex = new VowelStringKeyIndex();
//                break;

//            case ALPHABETS:
//                keyIndex = new AlphabetsStringKeyIndex();
//                break;
//
//            case ALPHANUMERIC:
//                keyIndex = new AlphaNumericStringKeyIndex();
//                break;

            default:
                keyIndex = new AsciiStringKeyIndex();
                break;
        }
    }

    /**
     * Sorts the characters array based on the key-index.
     *
     * @param chars
     */
    public void sort(char[] chars) {
        char[] sortedChars = new char[chars.length];
        int[] count = new int[keyIndex.size() + 1];
        // count each vowel
        for (int i = 0; i < chars.length; i++) {
//            System.out.println("i:" + i + ", char:" + chars[i]);
            int index = keyIndex.get(chars[i]) + 1;
            count[index] = count[index] + 1;
        }
//        System.out.println(Arrays.toString(count));

        // find the next position index
        for (int i = 1; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }
//        System.out.println(Arrays.toString(count));

        // sort characters
        for (int i = 0; i < chars.length; i++) {
            sortedChars[count[keyIndex.get(chars[i])]++] = chars[i];
        }
//        System.out.println(Arrays.toString(count));
//        System.out.println(Arrays.toString(sortedChars));

        // copy the sorted array to original array
        System.arraycopy(sortedChars, 0, chars, 0, chars.length);
    }

    /**
     * @param str
     * @return
     */
    public String sort(String str) {
        char[] chars = str.toCharArray();
        sort(chars);
        return new String(chars);
    }

}
