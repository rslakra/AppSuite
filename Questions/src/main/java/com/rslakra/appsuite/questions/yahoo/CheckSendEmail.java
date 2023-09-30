package com.rslakra.appsuite.questions.yahoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Each account is allowed to send 200 recipients hourly and 2000 recipients daily.
 * <p>
 * Implement a counting check method that can tell if the email can be sent out based on hourly quota and daily quota.
 * <p>
 * Input are based on one email {String id, int recipients_count, String timestamp}
 * <p>
 * Output is boolean
 * <pre>
 *
 * # A, 2,   '01/01 01:55:00' - True
 * # A, 190, '01/01 01:59:58' - True
 * # A, 90,  '01/01 01:59:59' - False
 * # A, 10,  '01/01 02:00:00' - True
 * # A, 100, '01/03 02:02:02' - True
 * # B, 2,   '01/03 02:00:00' - True
 * # A, 100, '01/03 02:03:02' - True
 *
 * (MM/DD HH:MM:SS)
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 8/31/23 3:45 PM
 */
public class CheckSendEmail {


    static class Account {

        static final int HOURLY_LIMIT = 200;
        static final int DAILY_LIMIT = 2000;
        SimpleDateFormat hourlyFormat = new SimpleDateFormat("MM/DD HH");
        SimpleDateFormat dailyFormat = new SimpleDateFormat("MM/DD");

        String id;
        int recipientCount;
        String timestamp;
        int dailyCount;
        Date lastDailyTimestamp;
        int hourlyCount;
        Date lastHourlyTimestamp;

        /**
         * @param id
         * @param recipientCount
         * @param timestamp
         */
        public Account(String id, int recipientCount, String timestamp) {
            this.id = id;
            this.recipientCount = recipientCount;
            this.timestamp = timestamp;
            this.hourlyCount = 0;
            this.dailyCount = 0;
        }

        /**
         * @param dateFormat
         * @param date
         * @return
         */
        public Date parseDate(SimpleDateFormat dateFormat, String date) {
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * @return
         */
        public int hashCode() {
            return id.hashCode();
        }

        /**
         * @param other
         * @return
         */
        @Override
        public boolean equals(Object other) {
            if (this != other) {
                return false;
            }

            if (getClass() != other.getClass()) {
                return false;
            }

            return id.equals(((Account) other).id);
        }

        /**
         * @param timestamp
         * @return
         */
        public boolean withInHourlyDateRange(int recipientCount, String timestamp) {
            if (lastHourlyTimestamp == null || lastHourlyTimestamp.equals(parseDate(hourlyFormat, timestamp))) {
                return (hourlyCount + recipientCount) < HOURLY_LIMIT;
            }

            return false;
        }

        /**
         * @param timestamp
         * @return
         */
        public boolean withInDailyDateRange(int recipientCount, String timestamp) {
            if (lastDailyTimestamp == null || lastDailyTimestamp.equals(parseDate(dailyFormat, timestamp))) {
                return (dailyCount + recipientCount) < DAILY_LIMIT;
            }

            return false;
        }

        /**
         * @param recipientCount
         * @param timestamp
         */
        public void updateAccount(int recipientCount, String timestamp) {
            lastHourlyTimestamp = parseDate(hourlyFormat, timestamp);
            this.hourlyCount += recipientCount;

            if (lastDailyTimestamp == null) {
                lastDailyTimestamp = parseDate(dailyFormat, timestamp);
            }
            this.dailyCount += recipientCount;
        }
    }

    static Map<String, Account> accounts = new HashMap<>();

    /**
     * @param id
     * @param recipientCount
     * @param timestamp
     */
    public boolean sendEmail(String id, int recipientCount, String timestamp) {
        boolean result = false;
        Account account = accounts.get(id);
        if (account == null) {
            account = new Account(id, recipientCount, timestamp);
            accounts.put(id, account);
        }

        if (account.withInHourlyDateRange(recipientCount, timestamp)) {
            // allowed to send email and update map
            account.updateAccount(recipientCount, timestamp);
            result = true;
        } else if (account.withInDailyDateRange(recipientCount, timestamp)) {
            // allowed to send email and update map
            account.updateAccount(recipientCount, timestamp);
            result = true;
        } else {
            // not allowed to send email and update map
        }

        return result;
    }


    /**
     * <pre>
     *
     * # A, 2,   '01/01 01:55:00' - True
     * # A, 190, '01/01 01:59:58' - True
     * # A, 90,  '01/01 01:59:59' - False
     * # A, 1000, '01/01 02:00:00' - True
     * # A, 1001, '01/01 02:02:02' - False
     * # A, 1000, '01/03 02:02:02' - True
     * # B, 2,   '01/03 02:00:00' - True
     * # A, 100, '01/03 02:03:02' - True
     * # C, 16,  '01/03 02:00:00' - True
     * # A, 1500, '01/04 02:03:02' - True
     * </pre>
     *
     * @return
     */
    public Map<Account, Boolean> buildSenders() {
        Map<Account, Boolean> input = new HashMap<>();
        input.put(new Account("A", 2, "01/01 01:55:00"), true);
        input.put(new Account("A", 190, "01/01 01:59:58"), true);
        input.put(new Account("A", 90, "01/01 01:59:59"), false);
        input.put(new Account("A", 1000, "01/01 02:00:00"), true);
        input.put(new Account("A", 1001, "01/01 02:02:02"), false);
        input.put(new Account("A", 1000, "01/03 02:02:02"), true);
        input.put(new Account("B", 2, "01/03 02:00:00"), true);
        input.put(new Account("A", 100, "01/03 02:03:02"), true);
        input.put(new Account("A", 100, "01/03 02:00:00"), true);
        input.put(new Account("A", 100, "01/04 02:03:02"), true);
        return input;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CheckSendEmail checkSendEmail = new CheckSendEmail();
        checkSendEmail.buildSenders().forEach((input, expected) -> {
            if (checkSendEmail.sendEmail(input.id, input.recipientCount, input.timestamp) == expected) {
                System.out.println("Send email allowed:" + expected);
            } else {
                System.out.println("Send email allowed:" + expected);
            }
        });
    }

}
