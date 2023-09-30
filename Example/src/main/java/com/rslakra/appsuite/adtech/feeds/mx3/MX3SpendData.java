package com.rslakra.appsuite.gemini.feeds.mx3;

import com.rslakra.appsuite.core.ToString;

import java.math.BigDecimal;

public class MX3SpendData {

    private String feedInterval;
    private Long advertiserId;
    private Long campaignId;
    private String timezone;
    private ProductType productType;
    private Integer testFlag;
    private BigDecimal monthlySpend;
    private BigDecimal lifetimeSpend;

    public String getFeedInterval() {
        return feedInterval;
    }

    public void setFeedInterval(String feedInterval) {
        this.feedInterval = feedInterval;
    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Integer testFlag) {
        this.testFlag = testFlag;
    }

    public BigDecimal getMonthlySpend() {
        return monthlySpend;
    }

    public void setMonthlySpend(BigDecimal monthlySpend) {
        this.monthlySpend = monthlySpend;
    }

    public BigDecimal getLifetimeSpend() {
        return lifetimeSpend;
    }

    public void setLifetimeSpend(BigDecimal lifetimeSpend) {
        this.lifetimeSpend = lifetimeSpend;
    }

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
