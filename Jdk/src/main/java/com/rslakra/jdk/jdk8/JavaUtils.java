package com.rslakra.jdk.jdk8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public enum JavaUtils {
	INSTANCE;

	/**
	 * @param inputList
	 * @param size
	 * @param <T>
	 * @return
	 */
	public final <T> Collection<List<T>> partitionListBySize(final List<T> inputList, final int size) {
		if (inputList == null || inputList.isEmpty() || size <= 0) {
			return Collections.emptyList();
		} else if (inputList.size() <= size) {
			return Collections.singletonList(inputList);
		} else {
			final AtomicInteger counter = new AtomicInteger(0);
			return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
		}
	}

//    /**
//     * @param inputSet
//     * @param size
//     * @param <T>
//     * @return
//     */
//    public final <T> Collection<Set<T>> partitionSetBySize(final Set<T> inputSet, final int size) {
//        if (inputSet == null || inputSet.isEmpty() || size <= 0) {
//            return Collections.emptyList();
//        } else if (inputSet.size() <= size) {
//            return Collections.singletonList(inputSet);
//        } else {
//            final AtomicInteger counter = new AtomicInteger(0);
//            return inputSet.stream()
//                .collect(Collectors.toSet()).values();
//        }
//    }
}
