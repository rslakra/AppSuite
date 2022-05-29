package com.rslakra.core.entity;

import com.rslakra.core.utils.ToString;
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
public class User {

    private Long id;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;

    private Address address;

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(User.class)
            .add("id", id)
            .add("userName", userName)
            .add("firstName", firstName)
            .add("middleName", middleName)
            .add("lastName", lastName)
            .add("address", address)
            .toString();
    }

    /**
     * @param id
     * @param userName
     * @param firstName
     * @param middleName
     * @param lastName
     * @param address
     * @return
     */
    public static User of(final Long id, final String userName, final String firstName, final String middleName,
                          final String lastName, final Address address) {
        final User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setAddress(address);

        return user;
    }

    /**
     * @param id
     * @param userName
     * @param firstName
     * @param lastName
     * @return
     */
    public static User of(final Long id, final String userName, final String firstName, final String lastName) {
        return of(id, userName, firstName, null, lastName, null);
    }
}
