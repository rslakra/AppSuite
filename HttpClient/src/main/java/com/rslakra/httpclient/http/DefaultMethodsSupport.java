package com.rslakra.httpclient.http;

import com.rslakra.core.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:36 PM
 */
public class DefaultMethodsSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMethodsSupport.class);
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    public DefaultMethodsSupport() {
    }

    protected static Range subListBorders(int size, Range range) {
//        if (range instanceof IntRange) {
//            return ((IntRange) range).subListBorders(size);
//        } else {
//            int from = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
//            int to = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getTo()), size);
//            boolean reverse = range.isReverse();
//            if (from > to) {
//                int tmp = to;
//                to = from;
//                from = tmp;
//                reverse = !reverse;
//            }
//
//            return new Range(from, to + 1, reverse);
//        }
        return null
            ;
    }

//    protected static Range subListBorders(int size, EmptyRange range) {
//        int from = normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), size);
//        return new Range(from, from, false);
//    }

    protected static int normaliseIndex(int i, int size) {
        int temp = i;
        if (i < 0) {
            i += size;
        }

        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(
                "Negative array index [" + temp + "] too large for array size " + size);
        } else {
            return i;
        }
    }

    public static void closeWithWarning(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException var2) {
                LOGGER.warn("Caught com.rslakra.algos.exception during close(): " + var2);
            }
        }

    }


    protected static <T> Collection<T> cloneSimilarCollection(Collection<T> orig, int newCapacity) {
        Collection<T> answer = (Collection) cloneObject(orig);
        if (answer != null) {
            return answer;
        } else {
            answer = createSimilarCollection(orig, newCapacity);
            answer.addAll(orig);
            return answer;
        }
    }

    private static Object cloneObject(Object orig) {
//        if (orig instanceof Cloneable) {
//            try {
//                return InvokerHelper.invokeMethod(orig, "clone", EMPTY_OBJECT_ARRAY);
//            } catch (Exception var2) {
//            }
//        }

        return null;
    }

    protected static Collection createSimilarOrDefaultCollection(Object object) {
        return (Collection) (object instanceof Collection ? createSimilarCollection((Collection) object)
                                                          : new ArrayList());
    }

    protected static <T> Collection<T> createSimilarCollection(Iterable<T> iterable) {
        return (Collection) (iterable instanceof Collection ? createSimilarCollection((Collection) iterable)
                                                            : new ArrayList());
    }

    protected static <T> Collection<T> createSimilarCollection(Collection<T> collection) {
        return createSimilarCollection(collection, collection.size());
    }

    protected static <T> Collection<T> createSimilarCollection(Collection<T> orig, int newCapacity) {
        if (orig instanceof Set) {
            return createSimilarSet((Set) orig);
        } else if (orig instanceof List) {
            return createSimilarList((List) orig, newCapacity);
        } else {
            return (Collection) (orig instanceof Queue ? new LinkedList() : new ArrayList(newCapacity));
        }
    }

    protected static <T> List<T> createSimilarList(List<T> orig, int newCapacity) {
        if (orig instanceof LinkedList) {
            return new LinkedList();
        } else if (orig instanceof Stack) {
            return new Stack();
        } else {
            return (List) (orig instanceof Vector ? new Vector() : new ArrayList(newCapacity));
        }
    }

//    protected static <T> T[] createSimilarArray(T[] orig, int newCapacity) {
//        Class<T> componentType = orig.getClass().getComponentType();
//        return (Object[]) ((Object[]) Array.newInstance(componentType, newCapacity));
//    }

    protected static <T> Set<T> createSimilarSet(Set<T> orig) {
        return (Set) (orig instanceof SortedSet ? new TreeSet(((SortedSet) orig).comparator()) : new LinkedHashSet());
    }

    protected static <K, V> Map<K, V> createSimilarMap(Map<K, V> orig) {
        if (orig instanceof SortedMap) {
            return new TreeMap(((SortedMap) orig).comparator());
        } else if (orig instanceof Properties) {
//            return new Properties();
            return null;
        } else {
            return (Map) (orig instanceof Hashtable ? new Hashtable() : new LinkedHashMap());
        }
    }

    protected static <K, V> Map<K, V> cloneSimilarMap(Map<K, V> orig) {
        Map<K, V> answer = (Map) cloneObject(orig);
        if (answer != null) {
            return answer;
        } else if (orig instanceof TreeMap) {
            return new TreeMap(orig);
        } else if (orig instanceof Properties) {
//            Map<K, V> map = new Properties();
//            map.putAll(orig);
//            return map;
            return null;
        } else {
            return (Map) (orig instanceof Hashtable ? new Hashtable(orig) : new LinkedHashMap(orig));
        }
    }

    protected static boolean sameType(Collection[] cols) {
        List all = new LinkedList();
        Collection[] var2 = cols;
        int var3 = cols.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Collection col = var2[var4];
            all.addAll(col);
        }

        if (all.isEmpty()) {
            return true;
        } else {
            Object first = all.get(0);
            Class baseClass;
            if (first instanceof Number) {
                baseClass = Number.class;
            } else if (first == null) {
                baseClass = null;// NullObject.class;
            } else {
                baseClass = first.getClass();
            }

            Collection[] var12 = cols;
            int var13 = cols.length;

            for (int var6 = 0; var6 < var13; ++var6) {
                Collection col = var12[var6];
                Iterator var8 = col.iterator();

                while (var8.hasNext()) {
                    Object o = var8.next();
                    if (!baseClass.isInstance(o)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
