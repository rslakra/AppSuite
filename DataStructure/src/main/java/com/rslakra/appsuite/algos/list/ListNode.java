package com.rslakra.appsuite.algos.list;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 1:56 PM
 */

@Getter
public class ListNode<T> {

    private T value;
    public ListNode next;

    /**
     * @param value
     */
    public ListNode(T value) {
        this.value = value;
    }

    /**
     * @return
     */
    public static ListNode createSinglyLinkedList() {
        return ListUtils.createSinglyLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ListNode)) {
            return false;
        }

        ListNode listNode = (ListNode) object;

        if (value != listNode.value) {
            return false;
        }
        return next != null ? next.equals(listNode.next) : listNode.next == null;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getValue(), next);
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(ListNode.class)
            .add("value", getValue())
            .add("next", getNext())
            .toString();
    }

}
