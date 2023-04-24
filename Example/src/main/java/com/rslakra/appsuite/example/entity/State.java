package com.rslakra.appsuite.example.entity;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:40 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class State {

    private String code;
    private String name;

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(State.class)
            .add("code", code)
            .add("name", name)
            .toString();
    }
}
