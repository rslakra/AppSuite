package com.rslakra.appsuite.example.dto;

import com.rslakra.appsuite.core.ToString;
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
public class AddressDTO {

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private StateDTO state;
    private String city;
    private String zipCode;

    /**
     * @param id
     * @param addressLine1
     * @param addressLine2
     * @param state
     * @param city
     * @param zipCode
     * @return
     */
    public static AddressDTO of(final Long id, final String addressLine1, final String addressLine2,
                                final StateDTO state, final String city, final String zipCode) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(id);
        addressDTO.setAddressLine1(addressLine1);
        addressDTO.setAddressLine2(addressLine2);
        addressDTO.setState(state);
        addressDTO.setCity(city);
        addressDTO.setZipCode(zipCode);
        return addressDTO;
    }

    /**
     * @return
     */
    public static AddressDTO ofDefault() {
        return of(101L, "123 Sunnyvale Dr.", "Unit #16", StateDTO.ofDefault(), "Sunnyvale", "94089");
    }

    @Override
    public String toString() {
        return ToString.of(AddressDTO.class)
            .add("id", id)
            .add("addressLine1", addressLine1)
            .add("addressLine2", addressLine2)
            .add("state", state)
            .add("city", city)
            .add("zipCode", zipCode)
            .toString();
    }
}
