package com.rslakra.appsuite.adtech.feeds.mx3;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MX3SpendData {

    private String feedInterval;
    private Long advertiserId;
    private Long campaignId;
    private String timezone;
    private ProductType productType;
    private Integer testFlag;
    private BigDecimal monthlySpend;
    private BigDecimal lifetimeSpend;

    public String toString() {
        return ToString.of(MX3SpendData.class)
                .add("feedInterval", getFeedInterval())
                .add("advertiserId", getAdvertiserId())
                .add("campaignId", getCampaignId())
                .add("timezone", getTimezone())
                .add("productType", getProductType())
                .add("testFlag", getTestFlag())
                .add("monthlySpend", getMonthlySpend())
                .add("lifetimeSpend", getLifetimeSpend())
                .toString();
    }
}
