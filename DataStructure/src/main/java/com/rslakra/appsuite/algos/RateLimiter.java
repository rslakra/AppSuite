package com.rslakra.appsuite.algos;

import com.rslakra.appsuite.core.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Design a hit counter/ a rate limiter Let say we have a service, and we want to monitor the count of client hitting
 * the service. So you gonna to implement a hit counter class to have two basic functionality:
 *
 * <pre>
 * void incr(String ip): increment the counter for this ip
 * int getCount(String ip): return the total counter for this ip for last 60 sec
 * </pre>
 * <p>
 * <p>
 * timestamp: yyyy-mm-dd hh:mm:ss
 *
 *
 * <pre>
 *
 * [12, 13, 14, 15, 16]
 * incr('1.2.3.4') at 0th sec
 * incr('1.2.3.4') at 10th sec
 * getCount('1.2.3.4') at 50th sec, => 2
 * getCount('1.2.3.4') at 60th sec => 1
 * </pre>
 * <p>
 * <p>
 * 69 - 60 = 9
 *
 * @author Rohtash Lakra
 * @created 9/11/23 2:58 PM
 */
public class RateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiter.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss:SSS");
    private Random random = new Random();

    private static Map<String, LinkedList<Long>> hits = new HashMap<>();

    /**
     * @param date
     * @return
     */
    public String asString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public String asString(Long time) {
        return DATE_FORMAT.format(time);
    }

    public void logHits() {

    }

    void incr(String ip) {
        LOGGER.debug("+incr({})", ip);
        LinkedList<Long> times = hits.get(ip);
        if (times == null) {
            times = new LinkedList<>();
            hits.put(ip, times);
        }

        times.add(0, System.currentTimeMillis());
        LOGGER.debug("-incr()");
    }


    int getCount(String ip) {
        LOGGER.debug("+getCount({})", ip);
        int count = 0;
        LinkedList<Long> times = hits.get(ip);
        if (times != null) {
            while ((System.currentTimeMillis() - times.getLast()) >= 60000) {
                LOGGER.debug("lastTime: {}", asString(times.getLast()));
                times.removeLast();
            }
        }

        count = times.size();
        LOGGER.debug("-getCount(), count:{}", count);
        return count;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        Random r = new Random();
        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(5L, TimeUnit.MINUTES);
        while (System.nanoTime() < endTime) {
            String ip = NetUtils.randomIPAddress(2);
            try {
//                System.out.println("ip:" + ip);
                rateLimiter.incr(ip);
                Thread.sleep(r.nextInt(100));
            } catch (InterruptedException e) {
            }

            try {
                System.out.println(String.format("Time: %s, ip:%s, hits:%d", rateLimiter.asString(new Date()), ip,
                                                 rateLimiter.getCount(ip)));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
