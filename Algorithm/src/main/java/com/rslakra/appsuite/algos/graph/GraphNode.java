package com.rslakra.appsuite.algos.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/18/23 6:47 PM
 */
public class GraphNode<T> {

    private T value;
    private List<GraphNode<T>> neighbors;

    public GraphNode(T value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
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

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        GraphNode that = (GraphNode) object;
        if (value != that.value) {
            return false;
        }

        return Objects.equals(neighbors, that.neighbors);
    }
}
