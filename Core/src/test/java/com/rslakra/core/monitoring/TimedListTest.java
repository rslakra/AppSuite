package com.rslakra.core.monitoring;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Rohtash Lakra
 * @created 5/12/21 5:35 PM
 */
public class TimedListTest {

    private long referenceTime;
    private TimedList<ListItem> listItems;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public void setUp() throws Exception {
        listItems = new TimedList<>();
    }

    private class ListItem implements Delayed {

        private final long time;

        ListItem(long time) {
            this.time = time;
        }

        /**
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        public int compareTo(Delayed that) {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - that.getDelay(TimeUnit.MILLISECONDS));
        }

        /**
         * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
         */
        @Override
        public long getDelay(TimeUnit unit) {
            long value = time - referenceTime;
            return unit.convert(value, TimeUnit.MILLISECONDS);
        }

    }

    @Test
    public void testQueueItemExpires() throws InterruptedException {

        ListItem item = new ListItem(10);
        boolean result = listItems.add(item);
        Assert.assertTrue(result);

        assertEquals(1, listItems.size());

        // increase the time
        referenceTime = 50;

        // Allow a thread switch if necessary
        TimeUnit.MILLISECONDS.sleep(100);

        // The queue should now be empty
        assertEquals(0, listItems.size());
    }

    @Test
    public void testQueueOneItemExpires() throws InterruptedException {

        ListItem item = new ListItem(10);
        boolean result = listItems.add(item);
        assertTrue(result);

        item = new ListItem(100);
        result = listItems.add(item);
        assertTrue(result);

        assertEquals(2, listItems.size());

        // increase the time
        referenceTime = 50;

        // Allow a thread switch if necessary
        TimeUnit.MILLISECONDS.sleep(100);

        // The queue should now contain one item
        assertEquals(1, listItems.size());
    }
}
