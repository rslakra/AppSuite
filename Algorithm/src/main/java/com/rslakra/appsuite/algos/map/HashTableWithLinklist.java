package com.rslakra.appsuite.algos.map;

import com.rslakra.appsuite.core.HashUtils;

/**
 * @author Rohtash Lakra
 * @created 11/12/20 11:42 AM
 */
public class HashTableWithLinklist<Key, Value> {

    private static final int BUCKETS = 97;
    private Node[] linkList = new Node[BUCKETS];

    private static class Node {

        private Object key;
        private Object value;
        private Node next;

        /**
         * @param key
         * @param value
         * @param next
         */
        Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            if (next != null) {
                this.next = next;
                next.next = null;
            } else {
                this.next = null;
            }
        }
    }

    public HashTableWithLinklist() {
    }

    /**
     * @param key
     * @return
     */
    private int hashCode(Key key) {
        return HashUtils.getHashIndex(key, BUCKETS);
    }

    /**
     * @param key
     * @return
     */
    public Value get(Key key) {
        int index = hashCode(key);
        for (Node node = linkList[index]; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return (Value) node.value;
            }
        }

        return null;
    }

    /**
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        int index = hashCode(key);
        for (Node node = linkList[index]; node != null; node = node.next) {
            if (key.equals(node.key)) {
                linkList[index].value = value;
                return;
            }
        }
        // always add at the beginning.
        linkList[index] = new Node(key, value, linkList[index]);
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder("{");
        for (int index = 0; index < linkList.length; index++) {
            Node node = linkList[index];
            if (node != null) {
                if (strBuilder.length() > 1) {
                    strBuilder.append(", ");
                }
                strBuilder.append(node.key).append("=").append(node.value);
                Node current = node.next;
                while (current != null) {
                    strBuilder.append(", ").append(current.key).append("=").append(current.value);
                    current = current.next;
                }
            }
        }

        strBuilder.append("}");

        return strBuilder.toString();
    }
}
