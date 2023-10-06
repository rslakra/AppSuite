package com.rslakra.appsuite.algos.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/18/23 7:00 PM
 */
@Getter
@Setter
public class NaryNode<T> {

    private T value;
    private List<Node> children;

    public NaryNode() {
        this(null, new ArrayList<>());
    }

    /**
     * @param value
     */
    public NaryNode(T value) {
        this(value, new ArrayList<>());
    }

    /**
     * @param value
     * @param children
     */
    public NaryNode(T value, List<Node> children) {
        this.value = value;
        this.children = children;
    }

    //todo: implement this method

    /**
     * return a N-ary tree based on the preorder values
     */
    public static Node createNaryTree(List<Integer> preorderValues) {
        return null;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        NaryNode naryNode = (NaryNode) object;
        return (getValue() == naryNode.getValue() && Objects.equals(children, naryNode.getChildren()));
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getValue(), children);
    }

}
