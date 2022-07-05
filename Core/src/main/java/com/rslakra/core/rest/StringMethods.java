package com.rslakra.core.rest;

import com.rslakra.core.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rohtash Lakra
 * @created 4/15/20 9:57 AM
 */
public class StringMethods extends DefaultMethodsSupport {

    private static String NEW_LINE = "\n";
    private static String lineSeparator = null;

    public StringMethods() {
    }

    public static boolean asBoolean(CharSequence string) {
        return string.length() > 0;
    }

//    public static <T> T asType(CharSequence self, Class<T> klass) {
//        return asType(self.toString(), klass);
//    }
//
//
//    public static <T> T asType(String self, Class<T> klass) {
//        if (klass == File.class) {
//            return (T) new File(self.toString());
//        } else {
//            return (isNotNumber(klass) && !isPrimitive(klass)) ? asType(self, klass) :
//                   asType(self.toString(), klass);
//        }
//    }

    /**
     * @param self
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> T asType(final String self, Class<T> classType) {
        if (classType == List.class) {
            return (T) toList((CharSequence) self);
        } else if (classType == BigDecimal.class) {
            return (T) toBigDecimal((CharSequence) self);
        } else if (classType == BigInteger.class) {
            return (T) toBigInteger((CharSequence) self);
        } else if (classType != Long.class && classType != Long.TYPE) {
            if (classType != Integer.class && classType != Integer.TYPE) {
                if (classType != Short.class && classType != Short.TYPE) {
                    if (classType != Byte.class && classType != Byte.TYPE) {
                        if (classType != Character.class && classType != Character.TYPE) {
                            if (classType != Double.class && classType != Double.TYPE) {
                                if (classType != Float.class && classType != Float.TYPE) {
                                    if (classType == File.class) {
                                        return (T) new File(self);
                                    } else {
//                                        return classType.isEnum() ? InvokerHelper
//                                            .invokeMethod(classType, "valueOf", new Object[]{self})
//                                                                  : asType(self, classType);
                                        return null;
                                    }
                                } else {
                                    return (T) toFloat((CharSequence) self);
                                }
                            } else {
                                return (T) toDouble((CharSequence) self);
                            }
                        } else {
                            return (T) toCharacter(self);
                        }
                    } else {
                        return (T) Byte.valueOf(self.trim());
                    }
                } else {
                    return (T) toShort((CharSequence) self);
                }
            } else {
                return (T) toInteger((CharSequence) self);
            }
        } else {
            return (T) toLong((CharSequence) self);
        }
    }

    public static Pattern bitwiseNegate(CharSequence self) {
        return Pattern.compile(self.toString());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Pattern bitwiseNegate(String self) {
        return bitwiseNegate((CharSequence) self);
    }

    public static String uncapitalize(CharSequence self) {
        String s = self.toString();
        return s != null && s.length() != 0 ? Character.toLowerCase(s.charAt(0)) + s.substring(1) : s;
    }

    /**
     * @param self
     * @param numberOfChars
     * @return
     */
    public static String center(final CharSequence self, final Number numberOfChars) {
        return center((CharSequence) self, numberOfChars, (CharSequence) " ");
    }

    /**
     * @param self
     * @param numberOfChars
     * @param padding
     * @return
     */
    public static String center(CharSequence self, Number numberOfChars, CharSequence padding) {
        int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self.toString();
        } else {
            int charsToAdd = numChars - self.length();
            String
                semiPad =
                charsToAdd % 2 == 1 ? getPadding(padding, charsToAdd / 2 + 1) : getPadding(padding, charsToAdd / 2);
            return charsToAdd % 2 == 0 ? semiPad + self + semiPad
                                       : semiPad.substring(0, charsToAdd / 2) + self + semiPad;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String center(String self, Number numberOfChars) {
        return center((CharSequence) self, numberOfChars);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String center(String self, Number numberOfChars, String padding) {
        return center((CharSequence) self, numberOfChars, (CharSequence) padding);
    }

    public static boolean contains(CharSequence self, CharSequence text) {
        int idx = self.toString().indexOf(text.toString());
        return idx >= 0;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean contains(String self, String text) {
        return contains((CharSequence) self, (CharSequence) text);
    }

    public static int count(CharSequence self, CharSequence text) {
        int answer = 0;
        int idx = 0;

        while (true) {
            idx = self.toString().indexOf(text.toString(), idx);
            if (idx < answer) {
                return answer;
            }

            ++answer;
            ++idx;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static int count(String self, String text) {
        return count((CharSequence) self, (CharSequence) text);
    }

    /**
     * Sets the line separator.
     */
    private static void setLineSeparator() {
        if (lineSeparator == null || lineSeparator.trim().length() == 0) {
            final StringWriter stringWriter = new StringWriter(2);
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(stringWriter);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                lineSeparator = stringWriter.toString();
            } catch (IOException ex) {
                lineSeparator = NEW_LINE;
            } finally {
                IOUtils.closeSilently(bufferedWriter);
            }
        }
    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String denormalize(String self) {
//        return denormalize((CharSequence) self);
//    }

    public static CharSequence drop(CharSequence self, int num) {
        if (num <= 0) {
            return self;
        } else {
            return self.length() <= num ? self.subSequence(0, 0) : self.subSequence(num, self.length());
        }
    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String expand(String self) {
//        return expand((CharSequence) self);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String expand(String self, int tabStop) {
//        return expand((CharSequence) self, tabStop);
//    }

    public static String expandLine(CharSequence self, int tabStop) {
        String s;
        int index;
        StringBuilder builder;
        for (s = self.toString(); (index = s.indexOf(9)) != -1; s = builder.toString()) {
            builder = new StringBuilder(s);
            int count = tabStop - index % tabStop;
            builder.deleteCharAt(index);

            for (int i = 0; i < count; ++i) {
                builder.insert(index, " ");
            }
        }

        return s;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String expandLine(String self, int tabStop) {
        return expandLine((CharSequence) self, tabStop);
    }

    public static String find(CharSequence self, CharSequence regex) {
        return find(self, Pattern.compile(regex.toString()));
    }


    public static String find(CharSequence self, Pattern pattern) {
        Matcher matcher = pattern.matcher(self.toString());
        return matcher.find() ? matcher.group(0) : null;
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static String find(String self, Pattern pattern) {
        return find((CharSequence) self, (Pattern) pattern);
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static String find(String self, String regex) {
        return find((CharSequence) self, (CharSequence) regex);
    }


    public static List<String> findAll(CharSequence self, CharSequence regex) {
        return findAll(self, Pattern.compile(regex.toString()));
    }


    public static List<String> findAll(CharSequence self, Pattern pattern) {
        Matcher matcher = pattern.matcher(self.toString());
        boolean hasGroup = hasGroup(matcher);
        List<String> list = new ArrayList();
        Iterator iter = iterator(matcher);

        while (iter.hasNext()) {
            if (hasGroup) {
                list.add((String) ((List) iter.next()).get(0));
            } else {
                list.add((String) iter.next());
            }
        }

        return new ArrayList(list);
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static List<String> findAll(String self, Pattern pattern) {
        return findAll((CharSequence) self, (Pattern) pattern);
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static List<String> findAll(String self, String regex) {
        return findAll((CharSequence) self, (CharSequence) regex);
    }


    private static int findMinimumLeadingSpaces(String line, int count) {
        int length = line.length();

        int index;
        for (index = 0; index < length && index < count && Character.isWhitespace(line.charAt(index)); ++index) {
        }

        return index;
    }


    public static Object getAt(Matcher matcher, int idx) {
        try {
            int count = getCount(matcher);
            if (idx >= -count && idx < count) {
                idx = normaliseIndex(idx, count);
                Iterator iter = iterator(matcher);
                Object result = null;

                for (int i = 0; i <= idx; ++i) {
                    result = iter.next();
                }

                return result;
            } else {
                throw new IndexOutOfBoundsException(
                    "index is out of range " + -count + ".." + (count - 1) + " (index = " + idx + ")");
            }
        } catch (IllegalStateException var6) {
            return null;
        }
    }

    public static boolean matchesPartially(Matcher matcher) {
        return matcher.matches() || matcher.hitEnd();
    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String getAt(String self, Collection indices) {
//        return getAt((CharSequence) self, (Collection) indices);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String getAt(String text, EmptyRange range) {
//        return getAt((CharSequence) text, (EmptyRange) range);
//    }
//
//    public static String getAt(String text, int index) {
//        index = normaliseIndex(index, text.length());
//        return text.substring(index, index + 1);
//    }
//
//    public static String getAt(String text, IntRange range) {
//        return getAt((String) text, (Range) range);
//    }
//
//    public static String getAt(String text, Range range) {
//        Range info = subListBorders(text.length(), range);
//        String answer = text.substring(info.from, info.to);
//        if (info.reverse) {
//            answer = reverse((CharSequence) answer);
//        }
//
//        return answer;
//    }

    public static char[] getChars(CharSequence self) {
        return self.toString().toCharArray();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static char[] getChars(String self) {
        return getChars((CharSequence) self);
    }

    public static int getCount(Matcher matcher) {
        int counter = 0;
        matcher.reset();

        while (matcher.find()) {
            ++counter;
        }

        return counter;
    }

    private static String getPadding(CharSequence padding, int length) {
        return padding.length() < length ? multiply((CharSequence) padding, length / padding.length() + 1)
            .substring(0, length) : "" + padding.subSequence(0, length);
    }

//    private static String getReplacement(Matcher matcher, Closure closure) {
//        if (!hasGroup(matcher)) {
//            return InvokerHelper.toString(closure.call(matcher.group()));
//        } else {
//            int count = matcher.groupCount();
//            List<String> groups = new ArrayList();
//
//            for (int i = 0; i <= count; ++i) {
//                groups.add(matcher.group(i));
//            }
//
//            return closure.getParameterTypes().length == 1 && closure.getParameterTypes()[0] == Object[].class
//                   ? InvokerHelper.toString(closure.call(groups.toArray()))
//                   : InvokerHelper.toString(closure.call(groups));
//        }
//    }

    public static boolean hasGroup(Matcher matcher) {
        return matcher.groupCount() > 0;
    }

    public static boolean isAllWhitespace(CharSequence self) {
        for (int i = 0; i < self.length(); ++i) {
            if (!Character.isWhitespace(self.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isAllWhitespace(String self) {
        return isAllWhitespace((CharSequence) self);
    }


    /**
     * Returns true if the class is not null and is type of primitive otherwise false.
     *
     * @param klass
     * @return
     */
    public static boolean isPrimitive(final Class<?> klass) {
        return (klass != null && klass.isPrimitive());
    }


    /**
     * Returns true if the class is not null and is not type of primitive otherwise false.
     *
     * @param klass
     * @return
     */
    public static boolean isNumber(final Class<?> klass) {
        return (klass != null && Number.class.isAssignableFrom(klass));
    }


    public static boolean isNumber(CharSequence chars) {
//        return isBigDecimal(self);
        return false;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isNumber(String self) {
        return isNumber((CharSequence) self);
    }

    /**
     * @param klass
     * @return
     */
    public static boolean isNotNumber(final Class<?> klass) {
        return (!isNumber(klass));
    }

    public static boolean isBigDecimal(CharSequence chars) {
        try {
            new BigDecimal(chars.toString().trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isBigDecimal(String self) {
        return isBigDecimal((CharSequence) self);
    }

    public static boolean isBigInteger(CharSequence self) {
        try {
            new BigInteger(self.toString().trim());
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isBigInteger(String self) {
        return isBigInteger((CharSequence) self);
    }

    public static boolean isCase(CharSequence caseValue, Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        } else {
            return caseValue.toString().equals(switchValue.toString());
        }
    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static boolean isCase(GString caseValue, Object switchValue) {
//        return isCase((CharSequence) caseValue, switchValue);
//    }

    public static boolean isCase(Pattern caseValue, Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        } else {
            Matcher matcher = caseValue.matcher(switchValue.toString());
            if (matcher.matches()) {
//                RegexSupport.setLastMatcher(matcher);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isCase(String caseValue, Object switchValue) {
        return isCase((CharSequence) caseValue, switchValue);
    }

    public static boolean isDouble(CharSequence self) {
        try {
            Double.valueOf(self.toString().trim());
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isDouble(String self) {
        return isDouble((CharSequence) self);
    }

    public static boolean isFloat(CharSequence self) {
        try {
            Float.valueOf(self.toString().trim());
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isFloat(String self) {
        return isFloat((CharSequence) self);
    }

    public static boolean isInteger(CharSequence self) {
        try {
            Integer.valueOf(self.toString().trim());
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isInteger(String self) {
        return isInteger((CharSequence) self);
    }

    public static boolean isLong(CharSequence self) {
        try {
            Long.valueOf(self.toString().trim());
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean isLong(String self) {
        return isLong((CharSequence) self);
    }


    public static Iterator iterator(final Matcher matcher) {
        matcher.reset();
        return new Iterator() {
            private boolean found;
            private boolean done;

            public boolean hasNext() {
                if (this.done) {
                    return false;
                } else {
                    if (!this.found) {
                        this.found = matcher.find();
                        if (!this.found) {
                            this.done = true;
                        }
                    }

                    return this.found;
                }
            }

            public Object next() {
                if (!this.found && !this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    this.found = false;
                    if (!StringMethods.hasGroup(matcher)) {
                        return matcher.group();
                    } else {
                        List<String> list = new ArrayList(matcher.groupCount());

                        for (int i = 0; i <= matcher.groupCount(); ++i) {
                            list.add(matcher.group(i));
                        }

                        return list;
                    }
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static StringBuilder leftShift(CharSequence self, Object value) {
        return (new StringBuilder(self)).append(value);
    }

    public static StringBuffer leftShift(String self, Object value) {
        return (new StringBuffer(self)).append(value);
    }

    public static StringBuffer leftShift(StringBuffer self, Object value) {
        self.append(value);
        return self;
    }

    public static StringBuilder leftShift(StringBuilder self, Object value) {
        self.append(value);
        return self;
    }

    public static boolean matches(CharSequence self, Pattern pattern) {
        return pattern.matcher(self).matches();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static boolean matches(String self, Pattern pattern) {
        return matches((CharSequence) self, pattern);
    }

    public static String minus(CharSequence self, Object target) {
        String s = self.toString();
        String text = null;//DefaultGroovyMethods.toString(target);
        int index = s.indexOf(text);
        if (index == -1) {
            return s;
        } else {
            int end = index + text.length();
            return s.length() > end ? s.substring(0, index) + s.substring(end) : s.substring(0, index);
        }
    }

    public static String minus(CharSequence self, Pattern pattern) {
        return pattern.matcher(self).replaceFirst("");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String minus(String self, Pattern pattern) {
        return minus((CharSequence) self, (Pattern) pattern);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String minus(String self, Object target) {
        return minus((CharSequence) self, (Object) target);
    }

    public static String multiply(CharSequence self, Number factor) {
        int size = factor.intValue();
        if (size == 0) {
            return "";
        } else if (size < 0) {
            throw new IllegalArgumentException(
                "multiply() should be called with a number of 0 or greater not: " + size);
        } else {
            StringBuilder answer = new StringBuilder(self);

            for (int i = 1; i < size; ++i) {
                answer.append(self);
            }

            return answer.toString();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String multiply(String self, Number factor) {
        return multiply((CharSequence) self, factor);
    }

    public static String next(CharSequence self) {
        StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            buffer.append('\u0000');
        } else {
            char last = buffer.charAt(buffer.length() - 1);
            if (last == '\uffff') {
                buffer.append('\u0000');
            } else {
                char next = (char) (last + 1);
                buffer.setCharAt(buffer.length() - 1, next);
            }
        }

        return buffer.toString();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String next(String self) {
        return next((CharSequence) self);
    }

    public static String normalize(CharSequence self) {
        String s = self.toString();
        int nx = s.indexOf(13);
        if (nx < 0) {
            return s;
        } else {
            int len = s.length();
            StringBuilder sb = new StringBuilder(len);
            int i = 0;

            do {
                sb.append(s, i, nx);
                sb.append('\n');
                if ((i = nx + 1) >= len) {
                    break;
                }

                if (s.charAt(i) == '\n') {
                    ++i;
                    if (i >= len) {
                        break;
                    }
                }

                nx = s.indexOf(13, i);
            } while (nx > 0);

            sb.append(s, i, len);
            return sb.toString();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String normalize(String self) {
        return normalize((CharSequence) self);
    }

    public static String padLeft(CharSequence self, Number numberOfChars) {
        return padLeft((CharSequence) self, numberOfChars, (CharSequence) " ");
    }

    public static String padLeft(CharSequence self, Number numberOfChars, CharSequence padding) {
        int numChars = numberOfChars.intValue();
        return numChars <= self.length() ? self.toString()
                                         : getPadding(padding.toString(), numChars - self.length()) + self;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String padLeft(String self, Number numberOfChars) {
        return padLeft((CharSequence) self, numberOfChars);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String padLeft(String self, Number numberOfChars, String padding) {
        return padLeft((CharSequence) self, numberOfChars, (CharSequence) padding);
    }

    public static String padRight(CharSequence self, Number numberOfChars) {
        return padRight((CharSequence) self, numberOfChars, (CharSequence) " ");
    }

    public static String padRight(CharSequence self, Number numberOfChars, CharSequence padding) {
        int numChars = numberOfChars.intValue();
        return numChars <= self.length() ? self.toString()
                                         : self + getPadding(padding.toString(), numChars - self.length());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String padRight(String self, Number numberOfChars) {
        return padRight((CharSequence) self, numberOfChars, (CharSequence) " ");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String padRight(String self, Number numberOfChars, String padding) {
        return padRight((CharSequence) self, numberOfChars, (CharSequence) padding);
    }

//    public static String plus(CharSequence left, Object value) {
//        return left + DefaultGroovyMethods.toString(value);
//    }
//
//    public static String plus(Number value, String right) {
//        return DefaultGroovyMethods.toString(value) + right;
//    }

    /**
     * @deprecated
     */
//    @Deprecated
//    public static String plus(String left, Object value) {
//        return plus((CharSequence) left, (Object) value);
//    }
    public static String plus(String left, CharSequence value) {
        return left + value;
    }

    public static String plus(StringBuffer left, String value) {
        return left + value;
    }

    public static String previous(CharSequence self) {
        StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            throw new IllegalArgumentException("the string is empty");
        } else {
            char last = buffer.charAt(buffer.length() - 1);
            if (last == 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            } else {
                char next = (char) (last - 1);
                buffer.setCharAt(buffer.length() - 1, next);
            }

            return buffer.toString();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String previous(String self) {
        return previous((CharSequence) self);
    }

//    public static void putAt(StringBuffer self, EmptyRange range, Object value) {
//        Range info = subListBorders(self.length(), range);
//        self.replace(info.from, info.to, value.toString());
//    }
//
//    public static void putAt(StringBuffer self, IntRange range, Object value) {
//        Range info = subListBorders(self.length(), range);
//        self.replace(info.from, info.to, value.toString());
//    }
//
//    public static List<String> readLines(CharSequence self) {
//        return DefaultGroovyMethods.toList(new StringMethods.LineIterable(self));
//    }

    /**
     * @deprecated
     */
//    @Deprecated
//    public static List<String> readLines(String self) {
//        return readLines((CharSequence) self);
//    }
    public static String replaceAll(CharSequence self, CharSequence regex, CharSequence replacement) {
        return self.toString().replaceAll(regex.toString(), replacement.toString());
    }

//    public static String replaceAll(CharSequence self, CharSequence regex,
//                                    @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                        "String[]"}) Closure closure) {
//        return replaceAll(self, Pattern.compile(regex.toString()), closure);
//    }

    public static String replaceAll(CharSequence self, Pattern pattern, CharSequence replacement) {
        return pattern.matcher(self).replaceAll(replacement.toString());
    }

//    public static String replaceAll(CharSequence self, Pattern pattern,
//                                    @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                        "String[]"}) Closure closure) {
//        String s = self.toString();
//        Matcher matcher = pattern.matcher(s);
//        if (!matcher.find()) {
//            return s;
//        } else {
//            StringBuffer sb = new StringBuffer(s.length() + 16);
//
//            do {
//                String replacement = getReplacement(matcher, closure);
//                matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
//            } while (matcher.find());
//
//            matcher.appendTail(sb);
//            return sb.toString();
//        }
//    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String replaceAll(String self, Pattern pattern,
//                                    @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                        "String[]"}) Closure closure) {
//        return replaceAll((CharSequence) self, (Pattern) pattern, (Closure) closure);
//    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String replaceAll(String self, Pattern pattern, String replacement) {
        return pattern.matcher(self).replaceAll(replacement);
    }

    /**
     * @deprecated
     */
//    @Deprecated
//    public static String replaceAll(String self, String regex,
//                                    @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                        "String[]"}) Closure closure) {
//        return replaceAll((CharSequence) self, (CharSequence) regex, (Closure) closure);
//    }
//
//    public static String replaceFirst(CharSequence self, CharSequence regex, CharSequence replacement) {
//        return self.toString().replaceFirst(regex.toString(), replacement.toString());
//    }
//
//    public static String replaceFirst(CharSequence self, CharSequence regex,
//                                      @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                          "String[]"}) Closure closure) {
//        return replaceFirst(self, Pattern.compile(regex.toString()), closure);
//    }
    public static String replaceFirst(CharSequence self, Pattern pattern, CharSequence replacement) {
        return pattern.matcher(self).replaceFirst(replacement.toString());
    }

//    public static String replaceFirst(CharSequence self, Pattern pattern,
//                                      @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                          "String[]"}) Closure closure) {
//        String s = self.toString();
//        Matcher matcher = pattern.matcher(s);
//        if (matcher.find()) {
//            StringBuffer sb = new StringBuffer(s.length() + 16);
//            String replacement = getReplacement(matcher, closure);
//            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
//            matcher.appendTail(sb);
//            return sb.toString();
//        } else {
//            return s;
//        }
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String replaceFirst(String self, Pattern pattern,
//                                      @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                          "String[]"}) Closure closure) {
//        return replaceFirst((CharSequence) self, (Pattern) pattern, (Closure) closure);
//    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String replaceFirst(String self, Pattern pattern, String replacement) {
        return pattern.matcher(self).replaceFirst(replacement);
    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String replaceFirst(String self, String regex,
//                                      @ClosureParams(value = FromString.class, options = {"List<String>",
//                                                                                          "String[]"}) Closure closure) {
//        return replaceFirst((CharSequence) self, (CharSequence) regex, (Closure) closure);
//    }

    public static String reverse(CharSequence self) {
        return (new StringBuilder(self)).reverse().toString();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String reverse(String self) {
        return reverse((CharSequence) self);
    }

    public static void setIndex(Matcher matcher, int idx) {
        int count = getCount(matcher);
        if (idx >= -count && idx < count) {
            if (idx == 0) {
                matcher.reset();
            } else {
                int i;
                if (idx > 0) {
                    matcher.reset();

                    for (i = 0; i < idx; ++i) {
                        matcher.find();
                    }
                } else if (idx < 0) {
                    matcher.reset();
                    idx += getCount(matcher);

                    for (i = 0; i < idx; ++i) {
                        matcher.find();
                    }
                }
            }

        } else {
            throw new IndexOutOfBoundsException(
                "index is out of range " + -count + ".." + (count - 1) + " (index = " + idx + ")");
        }
    }

    public static int size(CharSequence text) {
        return text.length();
    }

    public static long size(Matcher self) {
        return (long) getCount(self);
    }

    public static int size(String text) {
        return text.length();
    }

    public static int size(StringBuffer buffer) {
        return buffer.length();
    }

    public static String[] split(CharSequence self) {
        StringTokenizer st = new StringTokenizer(self.toString());
        String[] strings = new String[st.countTokens()];

        for (int i = 0; i < strings.length; ++i) {
            strings[i] = st.nextToken();
        }

        return strings;
    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String[] split(GString self) {
//        return split((CharSequence) self);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static String[] split(String self) {
//        return split((CharSequence) self);
//    }
//
//    public static <T> T splitEachLine(CharSequence self, CharSequence regex,
//                                      @ClosureParams(value = FromString.class, options = {
//                                          "List<String>"}) Closure<T> closure) throws IOException {
//        return splitEachLine(self, Pattern.compile(regex.toString()), closure);
//    }
//
//    public static <T> T splitEachLine(CharSequence self, Pattern pattern,
//                                      @ClosureParams(value = FromString.class, options = {
//                                          "List<String>"}) Closure<T> closure) {
//        List<String> list = readLines(self);
//        T result = null;
//
//        List vals;
//        for (Iterator var5 = (new StringMethods.LineIterable(self)).iterator(); var5.hasNext();
//             result = closure.call(vals)) {
//            String line = (String) var5.next();
//            vals = Arrays.asList(pattern.split(line));
//        }
//
//        return result;
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static <T> T splitEachLine(String self, Pattern pattern, @ClosureParams(value = FromString.class, options = {
//        "List<String>"}) Closure<T> closure) throws IOException {
//        return splitEachLine((CharSequence) self, (Pattern) pattern, closure);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static <T> T splitEachLine(String self, String regex, @ClosureParams(value = FromString.class, options = {
//        "List<String>"}) Closure<T> closure) throws IOException {
//        return splitEachLine((CharSequence) self, (CharSequence) regex, closure);
//    }

    public static String stripIndent(CharSequence self) {
        if (self.length() == 0) {
            return self.toString();
        } else {
            int runningCount = -1;
//            Iterator var2 = (new StringMethods.LineIterable(self)).iterator();
            Iterator var2 = null;

            while (var2.hasNext()) {
                String line = (String) var2.next();
                if (!isAllWhitespace((CharSequence) line)) {
                    if (runningCount == -1) {
                        runningCount = line.length();
                    }

                    runningCount = findMinimumLeadingSpaces(line, runningCount);
                    if (runningCount == 0) {
                        break;
                    }
                }
            }

            return stripIndent(self, runningCount == -1 ? 0 : runningCount);
        }
    }

    public static String stripIndent(CharSequence self, int numChars) {
        if (self.length() != 0 && numChars > 0) {
            StringBuilder builder = new StringBuilder();

//            for (Iterator var3 = (new StringMethods.LineIterable(self)).iterator(); var3.hasNext();
//                 builder.append("\n")) {
//                String line = (String) var3.next();
//                if (!isAllWhitespace((CharSequence) line)) {
//                    builder.append(stripIndentFromLine(line, numChars));
//                }
//            }

            if (self.charAt(self.length() - 1) != '\n') {
                builder.deleteCharAt(builder.length() - 1);
            }

            return builder.toString();
        } else {
            return self.toString();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String stripIndent(String self) {
        return stripIndent((CharSequence) self);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String stripIndent(String self, int numChars) {
        return stripIndent((CharSequence) self, numChars);
    }

    private static String stripIndentFromLine(String line, int numChars) {
        int length = line.length();
        return numChars <= length ? line.substring(numChars) : "";
    }

    public static String stripMargin(CharSequence self) {
        return stripMargin(self, '|');
    }

    public static String stripMargin(CharSequence self, char marginChar) {
        if (self.length() == 0) {
            return self.toString();
        } else {
            StringBuilder builder = new StringBuilder();
            Iterator var3 = null;//(new StringMethods.LineIterable(self)).iterator();

            while (var3.hasNext()) {
                String line = (String) var3.next();
                builder.append(stripMarginFromLine(line, marginChar));
                builder.append("\n");
            }

            if (self.charAt(self.length() - 1) != '\n') {
                builder.deleteCharAt(builder.length() - 1);
            }

            return builder.toString();
        }
    }

    public static String stripMargin(CharSequence self, CharSequence marginChar) {
        String mc = marginChar.toString();
        return mc.length() == 0 ? stripMargin(self, '|') : stripMargin(self, mc.charAt(0));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String stripMargin(String self) {
        return stripMargin((CharSequence) self);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String stripMargin(String self, char marginChar) {
        return stripMargin((CharSequence) self, marginChar);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String stripMargin(String self, String marginChar) {
        return stripMargin((CharSequence) self, (CharSequence) marginChar);
    }

    private static String stripMarginFromLine(String line, char marginChar) {
        int length = line.length();

        int index;
        for (index = 0; index < length && line.charAt(index) <= ' '; ++index) {
        }

        return index < length && line.charAt(index) == marginChar ? line.substring(index + 1) : line;
    }

    public static CharSequence take(CharSequence self, int num) {
        if (num < 0) {
            return self.subSequence(0, 0);
        } else {
            return self.length() <= num ? self : self.subSequence(0, num);
        }
    }

    public static BigDecimal toBigDecimal(CharSequence self) {
        return new BigDecimal(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BigDecimal toBigDecimal(String self) {
        return toBigDecimal((CharSequence) self);
    }

    public static BigInteger toBigInteger(CharSequence self) {
        return new BigInteger(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BigInteger toBigInteger(String self) {
        return toBigInteger((CharSequence) self);
    }

    public static Boolean toBoolean(String self) {
        String trimmed = self.trim();
        return !"true".equalsIgnoreCase(trimmed) && !"y".equalsIgnoreCase(trimmed) && !"1".equals(trimmed)
               ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Character toCharacter(String self) {
        return self.charAt(0);
    }

    public static Double toDouble(CharSequence self) {
        return Double.valueOf(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Double toDouble(String self) {
        return toDouble((CharSequence) self);
    }

    public static Float toFloat(CharSequence self) {
        return Float.valueOf(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Float toFloat(String self) {
        return toFloat((CharSequence) self);
    }

    /**
     * @param self
     * @return
     */
    public static Integer toInteger(final CharSequence self) {
        return Integer.valueOf(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Integer toInteger(String self) {
        return toInteger((CharSequence) self);
    }

//    public static List<String> tokenize(CharSequence self) {
//        return InvokerHelper.asList(new StringTokenizer(self.toString()));
//    }

//    public static List<String> tokenize(CharSequence self, Character token) {
//        return tokenize((CharSequence) self, (CharSequence) token.toString());
//    }

//    public static List<String> tokenize(CharSequence self, CharSequence token) {
//        return InvokerHelper.asList(new StringTokenizer(self.toString(), token.toString()));
//    }

//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static List<String> tokenize(String self) {
//        return tokenize((CharSequence) self);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static List<String> tokenize(String self, Character token) {
//        return tokenize((CharSequence) self, (Character) token);
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public static List<String> tokenize(String self, String token) {
//        return tokenize((CharSequence) self, (CharSequence) token);
//    }

    /**
     * @param self
     * @return
     */
    public static List<String> toList(final CharSequence self) {
        final String selfString = self.toString();
        final int size = selfString.length();
        final List<String> listString = new ArrayList(size);
        for (int i = 0; i < size; ++i) {
            listString.add(selfString.substring(i, i + 1));
        }

        return listString;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static List<String> toList(final String self) {
        return toList((CharSequence) self);
    }

    public static Long toLong(final CharSequence self) {
        return Long.valueOf(self.toString().trim());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Long toLong(String self) {
        return toLong((CharSequence) self);
    }

    public static Set<String> toSet(CharSequence self) {
        return new HashSet(toList(self));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Set<String> toSet(String self) {
        return toSet((CharSequence) self);
    }

    public static Short toShort(CharSequence self) {
        return Short.valueOf(self.toString().trim());
    }

    public static Short toShort(String self) {
        return toShort((CharSequence) self);
    }


    public static String unexpanded(CharSequence self) {
        return unexpanded((CharSequence) self, 8);
    }

    public static String unexpanded(CharSequence self, int tabStop) {
        if (self.length() == 0) {
            return self.toString();
        } else {
            StringBuilder builder = new StringBuilder();
            Iterator itr = (new LineIterable(self)).iterator();

            while (itr.hasNext()) {
                String line = (String) itr.next();
                builder.append(unexpandLine((CharSequence) line, tabStop));
                builder.append("\n");
            }

            if (self.charAt(self.length() - 1) != '\n') {
                builder.deleteCharAt(builder.length() - 1);
            }

            return builder.toString();
        }
    }

    /**
     * @param self
     * @return
     */
    public static String unexpanded(String self) {
        return unexpanded((CharSequence) self);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String unexpanded(String self, int tabStop) {
        return unexpanded((CharSequence) self, tabStop);
    }

    public static String unexpandLine(CharSequence self, int tabStop) {
        StringBuilder builder = new StringBuilder(self.toString());
        int index = 0;

        while (index + tabStop < builder.length()) {
            String piece = builder.substring(index, index + tabStop);

            int count;
            for (count = 0; count < tabStop && Character.isWhitespace(piece.charAt(tabStop - (count + 1))); ++count) {
            }

            if (count > 0) {
                piece = piece.substring(0, tabStop - count) + '\t';
                builder.replace(index, index + tabStop, piece);
                index = index + tabStop - (count - 1);
            } else {
                index += tabStop;
            }
        }

        return builder.toString();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String unexpandLine(String self, int tabStop) {
        return unexpandLine((CharSequence) self, tabStop);
    }

}

