package com.rslakra.appsuite.example.feeds.mx3;

import com.rslakra.appsuite.core.ToString;

import java.math.BigDecimal;
import java.util.function.Function;

public class MX3SpendFeedEntry extends MX3SpendData {

    private BigDecimal searchMonthlySpend;
    private BigDecimal nativeMonthlySpend;
    private BigDecimal searchLifetimeSpend;
    private BigDecimal nativeLifetimeSpend;

    public BigDecimal getSearchMonthlySpend() {
        return searchMonthlySpend;
    }

    public void setSearchMonthlySpend(BigDecimal searchMonthlySpend) {
        this.searchMonthlySpend = searchMonthlySpend;
    }

    public BigDecimal getNativeMonthlySpend() {
        return nativeMonthlySpend;
    }

    public void setNativeMonthlySpend(BigDecimal nativeMonthlySpend) {
        this.nativeMonthlySpend = nativeMonthlySpend;
    }

    public BigDecimal getSearchLifetimeSpend() {
        return searchLifetimeSpend;
    }

    public void setSearchLifetimeSpend(BigDecimal searchLifetimeSpend) {
        this.searchLifetimeSpend = searchLifetimeSpend;
    }

    public BigDecimal getNativeLifetimeSpend() {
        return nativeLifetimeSpend;
    }

    public void setNativeLifetimeSpend(BigDecimal nativeLifetimeSpend) {
        this.nativeLifetimeSpend = nativeLifetimeSpend;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdvertiserId() == null) ? 0 : getAdvertiserId().hashCode());
        result = prime * result + ((nativeLifetimeSpend == null) ? 0 : nativeLifetimeSpend.hashCode());
        result = prime * result + ((nativeMonthlySpend == null) ? 0 : nativeMonthlySpend.hashCode());
        result = prime * result + ((searchLifetimeSpend == null) ? 0 : searchLifetimeSpend.hashCode());
        result = prime * result + ((searchMonthlySpend == null) ? 0 : searchMonthlySpend.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MX3SpendFeedEntry other = (MX3SpendFeedEntry) obj;
        if (getAdvertiserId() == null) {
            if (other.getAdvertiserId() != null) {
                return false;
            }
        } else if (!getAdvertiserId().equals(other.getAdvertiserId())) {
            return false;
        }
        if (nativeLifetimeSpend == null) {
            if (other.nativeLifetimeSpend != null) {
                return false;
            }
        } else if (!nativeLifetimeSpend.equals(other.nativeLifetimeSpend)) {
            return false;
        }
        if (nativeMonthlySpend == null) {
            if (other.nativeMonthlySpend != null) {
                return false;
            }
        } else if (!nativeMonthlySpend.equals(other.nativeMonthlySpend)) {
            return false;
        }
        if (searchLifetimeSpend == null) {
            if (other.searchLifetimeSpend != null) {
                return false;
            }
        } else if (!searchLifetimeSpend.equals(other.searchLifetimeSpend)) {
            return false;
        }
        if (searchMonthlySpend == null) {
            if (other.searchMonthlySpend != null) {
                return false;
            }
        } else if (!searchMonthlySpend.equals(other.searchMonthlySpend)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return ToString.of(MX3SpendFeedEntry.class)
            .add("feedInterval", getFeedInterval())
            .add("advertiserId", getAdvertiserId())
            .add("timezone", getTimezone())
            .add("searchMonthlySpend", getSearchMonthlySpend())
            .add("nativeMonthlySpend", getNativeMonthlySpend())
            .add("searchLifetimeSpend", getSearchLifetimeSpend())
            .add("nativeLifetimeSpend", getNativeLifetimeSpend())
            .toString();
    }

    public static final Function<MX3SpendFeedEntry, Long> ADVERTISER_ID = new Function<MX3SpendFeedEntry, Long>() {
        @Override
        public Long apply(MX3SpendFeedEntry input) {
            return input == null ? null : input.getAdvertiserId();
        }
    };
}
