package com.rslakra.core.entity;

import com.rslakra.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:43 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Address {

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private State state;
    private String city;
    private String zipCode;

    @Override
    public String toString() {
        return ToString.of(Address.class)
            .add("id", id)
            .add("addressLine1", addressLine1)
            .add("addressLine2", addressLine2)
            .add("state", state)
            .add("city", city)
            .add("zipCode", zipCode)
            .toString();
    }
}
