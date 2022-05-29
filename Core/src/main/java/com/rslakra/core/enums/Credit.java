package com.rslakra.core.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 12/6/21 9:30 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Credit {

    private BigDecimal creditLimit;
    private BigDecimal creditAvailable;

}
