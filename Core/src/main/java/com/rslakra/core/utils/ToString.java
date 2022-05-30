package com.rslakra.core.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 12/13/21 5:41 PM
 */
public final class ToString {

    public static final String EMPTY_STR = "";
    public static final String DELIMITER = ", ";
    public static final String PREFIX = " <";
    public static final String SUFFIX = ">";
    public static final String SEPARATOR = "=";

    private final String prefix;
    private final String delimiter;
    private final String suffix;

    /**
     * Contains all the string components added so far.
     */
    private String[] elements;

    /**
     * The number of string components added so far.
     */
    private int size;

    /**
     * Total length in chars so far, excluding prefix and suffix.
     */
    private int length;

    /**
     * When overridden by the user to be non-null via {setEmptyValue}, the string returned by toString() when no
     * elements have yet been added. When null, prefix + suffix is used as the empty value.
     */
    private String emptyValue;

    /**
     * Constructs a {@code StringJoiner} with no characters in it, with no {@code prefix} or {@code suffix}, and a copy
     * of the supplied {@code delimiter}. If no characters are added to the {@code StringJoiner} and methods accessing
     * the value of it are invoked, it will not return a {@code prefix} or {@code suffix} (or properties thereof) in the
     * result, unless {@code setEmptyValue} has first been called.
     *
     * @param delimiter the sequence of characters to be used between each element added to the {@code StringJoiner}
     *                  value
     * @throws NullPointerException if {@code delimiter} is {@code null}
     */
    public ToString(final CharSequence delimiter) {
        this(delimiter, EMPTY_STR, EMPTY_STR);
    }

    /**
     * Constructs a {@code StringJoiner} with no characters in it using copies of the supplied {@code prefix}, {@code
     * delimiter} and {@code suffix}. If no characters are added to the {@code StringJoiner} and methods accessing the
     * string value of it are invoked, it will return the {@code prefix + suffix} (or properties thereof) in the result,
     * unless {@code setEmptyValue} has first been called.
     *
     * @param delimiter the sequence of characters to be used between each element added to the {@code StringJoiner}
     * @param prefix    the sequence of characters to be used at the beginning
     * @param suffix    the sequence of characters to be used at the end
     * @throws NullPointerException if {@code prefix}, {@code delimiter}, or {@code suffix} is {@code null}
     */
    public ToString(final CharSequence delimiter, final CharSequence prefix, final CharSequence suffix) {
        Objects.requireNonNull(prefix, "The prefix must not be null");
        Objects.requireNonNull(delimiter, "The delimiter must not be null");
        Objects.requireNonNull(suffix, "The suffix must not be null");

        // make defensive copies of arguments
        this.prefix = prefix.toString();
        this.delimiter = delimiter.toString();
        this.suffix = suffix.toString();
    }

    /**
     * @param self
     * @param chars
     * @param start
     * @return
     */
    private static int getChars(final String self, char[] chars, int start) {
        int length = self.length();
        self.getChars(0, length, chars, start);
        return length;
    }

    /**
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> ToString of(final Class<T> classType) {
        return new ToString(DELIMITER, classType.getSimpleName() + PREFIX, SUFFIX);
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> ToString of() {
        return new ToString(DELIMITER, PREFIX, SUFFIX);
    }

    /**
     * Sets the sequence of characters to be used when determining the string representation of this {@code
     * StringJoiner} and no elements have been added yet, that is, when it is empty.  A copy of the {@code emptyValue}
     * parameter is made for this purpose. Note that once an add method has been called, the {@code StringJoiner} is no
     * longer considered empty, even if the element(s) added correspond to the empty {@code String}.
     *
     * @param emptyValue the characters to return as the value of an empty {@code StringJoiner}
     * @return this {@code StringJoiner} itself so the calls may be chained
     * @throws NullPointerException when the {@code emptyValue} parameter is {@code null}
     */
    public ToString setEmptyValue(final CharSequence emptyValue) {
        this.emptyValue = Objects.requireNonNull(emptyValue, "The empty value must not be null").toString();
        return this;
    }

    /**
     * Returns the current value, consisting of the {@code prefix}, the values added so far separated by the {@code
     * delimiter}, and the {@code suffix}, unless no elements have been added in which case, the {@code prefix + suffix}
     * or the {@code emptyValue} characters are returned.
     *
     * @return the string representation of this {@code StringJoiner}
     */
    @Override
    public String toString() {
        final String[] elements = this.elements;
        if (elements == null && emptyValue != null) {
            return emptyValue;
        }
        final int size = this.size;
        final int addLength = prefix.length() + suffix.length();
        if (addLength == 0) {
            compactElements();
            return size == 0 ? EMPTY_STR : elements[0];
        }

        final String delimiter = this.delimiter;
        final char[] allElements = new char[length + addLength];
        int charIndex = getChars(prefix, allElements, 0);
        if (size > 0) {
            charIndex += getChars(elements[0], allElements, charIndex);
            for (int i = 1; i < size; i++) {
                charIndex += getChars(delimiter, allElements, charIndex);
                charIndex += getChars(elements[i], allElements, charIndex);
            }
        }

        charIndex += getChars(suffix, allElements, charIndex);
        return new String(allElements);
    }

    /**
     * Adds the <code>newElement</code>
     *
     * @param newElement
     * @return
     */
    public ToString add(final CharSequence newElement) {
        final String element = String.valueOf(newElement);
        if (this.elements == null) {
            this.elements = new String[8];
        } else {
            if (size == this.elements.length) {
                this.elements = Arrays.copyOf(this.elements, 2 * size);
            }
            length += this.delimiter.length();
        }
        length += element.length();
        this.elements[size++] = element;
        return this;
    }

    /**
     * Adds the <code>key</code>
     *
     * @param key
     * @return
     */
    public ToString add(final CharSequence key, final Object value) {
        if (key != null && value != null) {
            return add(key + "=" + Objects.toString(value));
        } else if (key == null && value != null) {
            return add(Objects.toString(value));
        } else {
            return add(key);
        }
    }

    /**
     * Adds the contents of the given {@code StringJoiner} without prefix and suffix as the next element if it is
     * non-empty. If the given {@code StringJoiner} is empty, the call has no effect.
     *
     * <p>A {@code StringJoiner} is empty if {@link #add(CharSequence) add()}
     * has never been called, and if {@code merge()} has never been called with a non-empty {@code StringJoiner}
     * argument.
     *
     * <p>If the other {@code StringJoiner} is using a different delimiter,
     * then elements from the other {@code StringJoiner} are concatenated with that delimiter and the result is appended
     * to this {@code StringJoiner} as a single element.
     *
     * @param other The {@code StringJoiner} whose contents should be merged into this one
     * @return This {@code StringJoiner}
     * @throws NullPointerException if the other {@code StringJoiner} is null
     */
    public ToString merge(final ToString other) {
        Objects.requireNonNull(other);
        if (other.elements == null) {
            return this;
        }
        other.compactElements();
        return add(other.elements[0]);
    }

    /**
     * Compacts elements.
     */
    private void compactElements() {
        if (size > 1) {
            final char[] chars = new char[length];
            int i = 1, startIndex = getChars(elements[0], chars, 0);
            do {
                startIndex += getChars(delimiter, chars, startIndex);
                startIndex += getChars(elements[i], chars, startIndex);
                elements[i] = null;
            } while (++i < size);
            size = 1;
            elements[0] = new String(chars);
        }
    }

    /**
     * Returns the length of the {@code String} representation of this {@code StringJoiner}. Note that if no add methods
     * have been called, then the length of the {@code String} representation (either {@code prefix + suffix} or {@code
     * emptyValue}) will be returned. The value should be equivalent to {@code toString().length()}.
     *
     * @return the length of the current value of {@code StringJoiner}
     */
    public int length() {
        return (size == 0 && emptyValue != null) ? emptyValue.length() :
               length + prefix.length() + suffix.length();
    }

}
