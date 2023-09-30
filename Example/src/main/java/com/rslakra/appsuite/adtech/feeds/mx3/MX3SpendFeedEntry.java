package com.rslakra.appsuite.adtech.feeds.mx3;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

@Getter
@Setter
public class MX3SpendFeedEntry extends MX3SpendData {

    private BigDecimal searchMonthlySpend;
    private BigDecimal nativeMonthlySpend;
    private BigDecimal searchLifetimeSpend;
    private BigDecimal nativeLifetimeSpend;

    @Override
    public int hashCode() {
        return Objects.hash(getAdvertiserId(), getNativeLifetimeSpend(), getNativeMonthlySpend(), getSearchLifetimeSpend(), getSearchMonthlySpend());
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
