package com.rslakra.appsuite.algos.sort;

/**
 * @author Rohtash Lakra
 * @created 6/16/22 3:27 PM
 */
public interface Sort {

    enum Direction {
        ASC,
        DESC;
    }

    /**
     * @return
     */
    public default Direction getDirection() {
        return Direction.ASC;
    }
}
