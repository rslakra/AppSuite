package com.rslakra.core.bos.advertising;

/**
 * Keep Alphabetical.
 *
 * @author Rohtash Lakra
 * @created 8/11/22 11:43AM
 */
public enum AdChargeType {

    /**
     * cost per action or cost per acquisition
     * <p>
     * advertisers pay every time a user completes a pre-determined action, be it a click-through, download, or
     * purchase.
     */
    CPA,

    /* Cost Per Click - the advertiser only pays when a user clicks on an ad */
    CPC,

    /* cost per lead (CPL) - advertisers pay when someone submits their contact information through a form. */
    CPL,

    /* Cost Per Thousands Views - cost per mille or cost per thousand impressions */
    CPM,

    /* Cost Per View */
    CPV,

    /* CTR (click-through-rate) defines how many users see an ad and click on it. */
    CTR,

    /* Pay Per Click */
    PPC;
}
