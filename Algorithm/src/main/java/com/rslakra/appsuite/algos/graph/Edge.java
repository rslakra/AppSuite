package com.rslakra.appsuite.algos.graph;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 9/9/23 4:59 PM
 */
@NoArgsConstructor
@Getter
@Setter
public class Edge<S, T> {

    private S source;
    private T target;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Edge.class)
            .add("source", getSource())
            .add("target", getTarget())
            .toString();
    }
}
