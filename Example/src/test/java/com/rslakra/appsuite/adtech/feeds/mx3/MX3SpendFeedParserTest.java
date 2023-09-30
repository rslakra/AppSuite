package com.rslakra.appsuite.example.feeds.mx3;

import com.rslakra.appsuite.core.IOUtils;
import com.rslakra.appsuite.adtech.feeds.mx3.MX3SpendFeedParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 4/22/21 11:09 AM
 */
public class MX3SpendFeedParserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MX3SpendFeedParserTest.class);

    @Test
    public void lineTokenize() {
        String str = "\001";
        String delimiter = "\u0001";
        LOGGER.info("str:{}, delimiter:{}", str, delimiter);
        String
            line =
            "202110121330\u00014\u0001-9999\u0001search\u0001America/New_York\u00012\u00010\u00010\u00010\u00010\u00010\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010\u0001-9999\u0001-9999\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010\u0001\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010.0000000000\u00010.0000000000\u0001\n";
        String[] parts = line.split(str);
        LOGGER.info("parts:{}", Arrays.toString(parts));
    }

    @Test
    public void parseMX3SpendFeed() {
        String fileName = "/Users/rlakra/Downloads/WorkData/MX3Feeds/part-v011-o000-r-00007.headers";
        try {
            MX3SpendFeedParser spendFeedParser = new MX3SpendFeedParser();
            spendFeedParser.setFeed(IOUtils.newInputStream(fileName));
            spendFeedParser.process();
            LOGGER.info("spendFeedMap:{}", spendFeedParser.getSpendFeedMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
