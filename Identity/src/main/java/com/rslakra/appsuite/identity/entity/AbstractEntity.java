package com.rslakra.appsuite.identity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 2/25/22 10:59 AM
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity implements Auditable {

    private Long id;
    private Date createdAt;
    private String createdBy;
    private Long createdOn;
    private Date updatedAt;
    private String updatedBy;
    private Long updatedOn;

}
