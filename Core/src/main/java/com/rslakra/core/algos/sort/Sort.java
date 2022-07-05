package com.rslakra.core.algos.sort;

/**
 * @author Rohtash Lakra (rlakra)
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
