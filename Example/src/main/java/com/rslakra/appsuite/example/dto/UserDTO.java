package com.rslakra.appsuite.example.dto;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:42 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;

    private AddressDTO address;

    /**
     * @param id
     * @param userName
     * @param firstName
     * @param address
     * @return
     */
    public static UserDTO of(final Long id, final String userName, final String firstName, final String lastName,
                             final AddressDTO address) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUserName(userName);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setAddress(address);
        return userDTO;
    }

    /**
     * @return
     */
    public static UserDTO ofDefault() {
        return of(101L, "rlakra", "Rohtash", "Lakra", AddressDTO.ofDefault());
    }

    @Override
    public String toString() {
        return ToString.of(UserDTO.class)
            .add("id", id)
            .add("userName", userName)
            .add("firstName", firstName)
            .add("middleName", middleName)
            .add("lastName", lastName)
            .add("address", address)
            .toString();
    }
}
