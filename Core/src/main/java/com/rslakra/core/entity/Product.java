package com.rslakra.core.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Dec 18, 2022 21:19:10
 */
@Getter
@Setter
public class Product {
    private String barCode;
    private String upcNumber;
    private String itemNumber;
    private BarCodeType barCodeType;

}
