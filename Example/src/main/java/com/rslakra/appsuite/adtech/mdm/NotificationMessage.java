package com.rslakra.appsuite.adtech.mdm;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 11/19/21 4:29 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificationMessage {

    private Long oldMDMId;
    private Long newMDMId;
    private String name;
    private Long yamId;
    private String eventType;

    @Override
    public String toString() {
        return ToString.of(NotificationMessage.class)
                .add("oldMDMId", oldMDMId)
                .add("newMDMId", newMDMId)
                .add("name", name)
                .add("yamId", yamId)
                .add("eventType", eventType)
                .toString();
    }
}
