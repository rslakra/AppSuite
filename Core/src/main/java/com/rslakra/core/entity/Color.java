package com.rslakra.core.entity;

import com.rslakra.core.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: rlakra
 * @Since: 9/11/19 1:36 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    private int id;
    private String name;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Color.class)
            .add("id", id)
            .add("name", name)
            .toString();
    }
}
