//package com.rslakra.core.utils;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * @author Rohtash Lakra (rlakra)
// * @created 8/25/21 5:47 PM
// */
//public enum UnsafeUtils {
//    INSTANCE;
//
//    private static final Unsafe UNSAFE;
//
//    static {
//        Unsafe unsafe;
//        try {
//            Class classUnsafe = Class.forName("sun.misc.Unsafe");
//            Field field = classUnsafe.getDeclaredField("theUnsafe");
//            field.setAccessible(true);
//            unsafe = (Unsafe) field.get(null);
//        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
//            unsafe = null;
//        } catch (IllegalAccessException e) {
//            throw new AssertionError(e);
//        }
//
//        UNSAFE = unsafe;
//    }
//
//    /**
//     * @return
//     */
//    public static boolean isUnsafeSupported() {
//        return (UNSAFE != null);
//    }
//}
