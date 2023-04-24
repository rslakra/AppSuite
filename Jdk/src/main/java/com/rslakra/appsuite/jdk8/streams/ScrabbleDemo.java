package com.rslakra.appsuite.jdk8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This set is composed of 200 tiles:
 *
 * <pre>
 * 4 blank tiles (scoring 0 points)
 * 1 point: E ×24, A ×16, O ×15, T ×15, I ×13, N ×13, R ×13, S ×10, L ×7, U ×7.
 * 2 points: D ×8, G ×5.
 * 3 points: C ×6, M ×6, B ×4, P ×4.
 * 4 points: H ×5, F ×4, W ×4, Y ×4, V ×3.
 * 5 points: K ×2.
 * 8 points: J ×2, X ×2.
 * 10 points: Q ×2, Z ×2.
 * </pre>
 *
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @Created Mar 6, 2019 11:49:02 AM
 */
public class ScrabbleDemo {

    /**
     * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y,
     * z] [1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8,
     * 4, 10]
     */
    private final int[] letterScore = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
            10};
    private final Function<String, Integer> wordScore = word -> word.chars().map(letter -> letterScore[letter - 'a'])
            .sum();

    /**
     * @param word
     * @return
     */
    public int getWordScore(String word) {
        return (word != null && word.length() > 0 ? wordScore.apply(word.toLowerCase()) : 0);
    }

    /**
     * @param words
     * @return
     */
    public Map<String, Integer> getScores(List<String> words) {
        if (words == null)
            return null;

        if (!words.isEmpty()) {
            words.forEach(word -> System.out.println(word + ":" + getWordScore(word)));
//			return words.stream().flatMap((key, value) -> System.out::println);
        }

        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ScrabbleDemo demo = new ScrabbleDemo();
        List<String> words = Arrays.asList("This", "is", "a", "line");
        demo.getScores(words);
    }

}
