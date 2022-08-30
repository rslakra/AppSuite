package com.rslakra.core.entity;

import com.rslakra.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 1/3/22 5:20 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class TestEntity {

    private User user;
    private State state;
    private Color color;

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of()
            .add("user=", user)
            .add("state=", state)
            .add("color=", color)
            .toString();
    }
}
