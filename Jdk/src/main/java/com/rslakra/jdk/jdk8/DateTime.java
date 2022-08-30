package com.rslakra.jdk.jdk8;

import com.rslakra.core.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 9/11/19 3:45 PM
 */
public class DateTime {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(DateTime.class);

    /**
     * @param cStartDate
     * @param cEndDate
     * @param earliest
     * @param latest
     * @return
     */
    public boolean needToUpdateTimes(Calendar cStartDate, Calendar cEndDate, Calendar earliest,
                                     Calendar latest) {
        LOGGER.debug("cStartDate:{} ", TimeUtils
            .toString(TimeUtils.DATE_FORMAT, cStartDate));
        LOGGER.debug("earliest: {}", TimeUtils
            .toString(TimeUtils.DATE_FORMAT, earliest));
        LOGGER.debug("cEndDate:{}", TimeUtils
            .toString(TimeUtils.DATE_FORMAT, cEndDate));
        LOGGER.debug("latest: {}", TimeUtils.toString(TimeUtils.DATE_FORMAT, latest));
//        return !Objects.equals(cStartDate, earliest) || !Objects.equals(cEndDate, latest);
        if ((TimeUtils.isMarkerDate(cStartDate) && TimeUtils.isDateAfterEpoch(earliest)) || (
            TimeUtils.isMarkerDate(earliest) && TimeUtils.isDateAfterEpoch(cStartDate))) {
            return true;
        } else if ((TimeUtils.isMarkerDate(cEndDate) && TimeUtils.isDateAfterEpoch(latest)) || (
            TimeUtils.isMarkerDate(latest) && TimeUtils.isDateAfterEpoch(cEndDate))) {
            return true;
        } else if (TimeUtils.isDateAfterEpoch(cStartDate) && TimeUtils.isDateAfterEpoch(earliest) && !Objects.equals(
            cStartDate, earliest)) {
            return true;
        } else if (TimeUtils.isDateAfterEpoch(cEndDate) && TimeUtils.isDateAfterEpoch(latest) && !Objects
            .equals(cEndDate, latest)) {
            return true;
        }

        return false;
    }

}
