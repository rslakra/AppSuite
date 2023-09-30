package com.rslakra.appsuite.adtech.feeds.mx3;

import com.rslakra.appsuite.core.IOUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MX3SpendFeedParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MX3SpendFeedParser.class);
    private static final String NATIVE = "native";
    private static final String SEARCH = "search";
    private Map<Long, MX3SpendFeedEntry> spendFeedMap = new HashMap<>();
    private static final String SPENDFEED_DELIMITER = "\001";
    private static final Long ADV_LV_ROLLUP_CAMPAIGN_ID = -9999L;
    private static final String TEST_FLAG_FALSE = "0";
    private InputStream feed;

    public void process() throws IOException {
        LOGGER.info("processing spend feed");
        processFeed(getFeed());
    }

    private void processFeed(InputStream spendFeed) throws IOException {
        BufferedReader reader = null;
        long proccessed = 0;
        long skipped = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(spendFeed, CharEncoding.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (pargeSpendFeedLine(line)) {
                    proccessed++;
                } else {
                    skipped++;
                }
            }
        } finally {
            LOGGER.info("number of lines processed={}, skipped={}", proccessed, skipped);
            IOUtils.closeSilently(reader);
        }
    }

    private boolean pargeSpendFeedLine(String line) {
        if (StringUtils.isBlank(line)) {
            return false;
        }
        MX3SpendFeedEntry spendFeedEntry = null;
        line = line.trim();
        try {
            String[] parts = line.split(SPENDFEED_DELIMITER);
            LOGGER.debug("parsing={}", Arrays.toString(parts));
            String feedInterval = parts[SpendFeedSchema.DATE.ordinal()];
            Long advertiserId = Long.parseLong(parts[SpendFeedSchema.ADVERTIESER_ID.ordinal()]);
            if (advertiserId < 0) {
                LOGGER.debug("adv id < 0, skipping={}", Arrays.toString(parts));
                return false;
            }
            Long campaignId = Long.parseLong(parts[SpendFeedSchema.CAMPAIGN_ID.ordinal()]);
            if (!campaignId.equals(ADV_LV_ROLLUP_CAMPAIGN_ID)) {
                LOGGER.info("not advertiser lv rollup, skipping={}", Arrays.toString(parts));
                return false;
            }
            String productType = parts[SpendFeedSchema.PRODUCT_TYPE.ordinal()];
            if (!StringUtils.equalsIgnoreCase(productType, NATIVE)
                && !StringUtils.equalsIgnoreCase(productType, SEARCH)) {
                LOGGER.info("unknown product type={}, skipping={}",
                            productType, Arrays.toString(parts));
                return false;
            }
            // test flag 1: test, 2: e2e test
            if (!StringUtils.equals(TEST_FLAG_FALSE, parts[SpendFeedSchema.TEST_FLAG.ordinal()])) {
                LOGGER.info("test flag is on, skipping={}", Arrays.toString(parts));
                return false;
            }
            String timezone = parts[SpendFeedSchema.TIME_ZONE.ordinal()];
            BigDecimal monthlyRevenue = new BigDecimal(parts[SpendFeedSchema.MONTHLY_REV.ordinal()]);
            BigDecimal lifetimeRevenue = new BigDecimal(parts[SpendFeedSchema.LIFETIME_REV.ordinal()]);
            spendFeedEntry = spendFeedMap.get(advertiserId);
            if (null == spendFeedEntry) {
                spendFeedEntry = new MX3SpendFeedEntry();
                spendFeedMap.put(advertiserId, spendFeedEntry);
                spendFeedEntry.setFeedInterval(feedInterval);
                spendFeedEntry.setAdvertiserId(advertiserId);
                spendFeedEntry.setTimezone(timezone);
            }
            if (StringUtils.equals(productType, NATIVE)) {
                BigDecimal monthlySpend = (spendFeedEntry.getNativeMonthlySpend() == null) ? monthlyRevenue
                                                                                           : spendFeedEntry
                                              .getNativeMonthlySpend()
                                              .add(monthlyRevenue);
                BigDecimal lifetimeSpend = (spendFeedEntry.getNativeLifetimeSpend() == null) ? lifetimeRevenue
                                                                                             : spendFeedEntry
                                               .getNativeLifetimeSpend()
                                               .add(lifetimeRevenue);
                spendFeedEntry.setNativeMonthlySpend(monthlySpend);
                spendFeedEntry.setNativeLifetimeSpend(lifetimeSpend);
            } else {
                BigDecimal monthlySpend = (spendFeedEntry.getSearchMonthlySpend() == null) ? monthlyRevenue
                                                                                           : spendFeedEntry
                                              .getSearchMonthlySpend()
                                              .add(monthlyRevenue);
                BigDecimal lifetimeSpend = (spendFeedEntry.getSearchLifetimeSpend() == null) ? lifetimeRevenue
                                                                                             : spendFeedEntry
                                               .getSearchLifetimeSpend()
                                               .add(lifetimeRevenue);
                spendFeedEntry.setSearchMonthlySpend(monthlySpend);
                spendFeedEntry.setSearchLifetimeSpend(lifetimeSpend);
            }
        } catch (Exception ex) {
            LOGGER.error("error parsing={}, error={}", line, ex);
            throw ex;
        }
        return true;
    }

    public InputStream getFeed() {
        return feed;
    }

    public void setFeed(InputStream feed) {
        this.feed = feed;
    }

    public Map<Long, MX3SpendFeedEntry> getSpendFeedMap() {
        return this.spendFeedMap;
    }

    private enum SpendFeedSchema {
        DATE,
        ADVERTIESER_ID,
        CAMPAIGN_ID,
        PRODUCT_TYPE,
        TIME_ZONE,
        TEST_FLAG,
        EOD,
        EOM,
        IMPRESSION,
        CLICKS,
        CONVERSION,
        HOURLY_REV,
        DAILY_REV,
        MONTHLY_REV,
        LIFETIME_REV,
        SERVES,
        IO_ID,
        IO_LINE_ID,
        INVALID_HOURLY_REV,
        INVALID_DAILY_REV,
        INVALID_MONTHLY_REV,
        INVALID_LIFETIME_REV,
        FEED_SRC,
        PREVIOUS_DAY_OVERSPEND
    }

}
