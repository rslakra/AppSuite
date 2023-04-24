package com.rslakra.appsuite.identity.entity;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 7/19/22 1:30 PM
 */
public interface Auditable {

    Date getCreatedAt();

    String getCreatedBy();

    Long getCreatedOn();

    Date getUpdatedAt();

    String getUpdatedBy();

    Long getUpdatedOn();

}
