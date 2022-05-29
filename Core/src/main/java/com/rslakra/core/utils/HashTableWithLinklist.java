package com.rslakra.core.utils;

/**
 * @author Rohtash Lakra
 * @created 11/12/20 11:42 AM
 */
public class HashTableWithLinklist<Key, Value> {

    private int BUCKETS = 97;
    private Node[] linkList = new Node[BUCKETS];

    private static class Node {

        private Object key;
        private Object value;
        private Node next;

        Node(Object key, Object value, Node parent) {
            this.key = key;
            this.value = value;
            if (parent != null) {
                this.next = parent;
                parent.next = null;
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
        return ((key.hashCode() & 0x7fffffff) % BUCKETS);
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

}
