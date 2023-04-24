package com.rslakra.appsuite.example.dto;

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
public class StateDTO {

    private String code;
    private String name;

    /**
     * @param code
     * @param name
     * @return
     */
    public static StateDTO of(final String code, final String name) {
        StateDTO stateDTO = new StateDTO();
        stateDTO.setCode(code);
        stateDTO.setName(name);
        return stateDTO;
    }

    /**
     * @return
     */
    public static StateDTO ofDefault() {
        return of("CA", "California");
    }

    @Override
    public String toString() {
        return ToString.of(StateDTO.class)
            .add("code", code)
            .add("name", name)
            .toString();
    }
}
