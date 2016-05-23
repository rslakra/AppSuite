package com.apparatus;

/**
 * The <code>ClockDisplay</code> class implements a digital clock display for 24
 * hours (European-style, by default) clock. The clock shows hours and minutes.
 * The range of the clock is 00:00 (midnight) to 23:59 (one minute before
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute and
 * reacts by incrementing the display. This is done in the usual clock fashion:
 * the hour increments when the minutes roll over to zero.
 * 
 * 
 * TODO: The clock display as written is a 24-hour clock, times ranging from
 * 00:00 (midnight) to 23:59. The exercise is to change the display so that the
 * times appear in 12-hour format. A 12-hour clock shows "AM" and "PM" to
 * distinguish before noon from after noon. The convention for exactly noon is
 * to display it as 12:00 PM. The convention for exactly midnight is to display
 * it as 12:00 AM.
 * 
 * So in converting the display from 24-hour to 12-hour it is not enough to
 * simply subtract 12 from the hour. You need to determine the appropriate
 * suffix ("AM" or "PM"), and you need to determine if it is midnight or not.
 * 
 * As to how the classes interact, the ClockDisplay is composed of two
 * NumberDisplay objects: hour and minute. When the clock updates its display it
 * asks both hour and minute objects for their current display time, and puts
 * them together. It does this using the String objects returned by the
 * NumberDisplay getDisplayValue() method. However, when you want to manipulate
 * the hour, e.g. by subtracting 12, you will find it easier to work with the
 * <code>int</code> value returned by the NumberDisplay getValue() method.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClockDisplay {
	/**
	 * hours and minutes for storing its time.
	 */
	private NumberDisplay hours;
	private NumberDisplay minutes;
	/**
	 * It shows the current time of the clock as string. In real clock this is
	 * the time that would be shown on its display.
	 */
	private String currentTime;

	/**
	 * Checks whether the clock supports 12-hour format or not.
	 */
	private boolean support12HourFormat;

	/** contains Meridian (AM/PM) value */
	private String meridian;

	// /**
	// * Keeps track of Meridian (AM/PM)
	// */
	// private boolean morning = true;

	/**
	 * The default constructor (no parameters) to create a new clock with
	 * default time 00:00.
	 */
	public ClockDisplay() {
		this(0, 0, false);
	}

	/**
	 * The default constructor (with a parameters) to create a new clock with
	 * default time 00:00.
	 */
	public ClockDisplay(boolean support12HourFormat) {
		this(0, 0, support12HourFormat);
	}

	/**
	 * 
	 * The default constructor (with parameters) to create a new clock with the
	 * time specified by the parameters.
	 * 
	 * @param hours
	 * @param minutes
	 */
	public ClockDisplay(int hour, int minute, boolean support12HourFormat) {
		hours = new NumberDisplay(support12HourFormat ? 12 : 24);
		minutes = new NumberDisplay(60);
		this.support12HourFormat = support12HourFormat;
		// /*
		// * The constructors, by default will initialize the time of day to be
		// * morning.
		// */
		// setMorning(true);
		setTime(hour, minute);
	}

	/**
	 * Returns the current hours.
	 * 
	 * @return hours
	 */
	public String getHours() {
		return hours.getDisplayValue();
	}

	/**
	 * Returns the current minutes.
	 * 
	 * @return minutes
	 */
	public String getMinutes() {
		return minutes.getDisplayValue();
	}

	/**
	 * Returns the currentTime.
	 * 
	 * @return currentTime
	 */
	public String getCurrentTime() {
		return currentTime;
	}

	/**
	 * Returns true if it supports the 12-hour format otherwise false.
	 * 
	 * @return
	 */
	public boolean isSupport12HourFormat() {
		return support12HourFormat;
	}

	/**
	 * Returns the current meridian value.
	 * 
	 * @return
	 */
	public String getMeridian() {
		return meridian;
	}

	/**
	 * The meridian to be set.
	 * 
	 * @return
	 */
	public void setMeridian(String meridian) {
		this.meridian = meridian;
	}

	// /**
	// * Returns true if it's morning (time period between the dawn and noon)
	// * otherwise false.
	// *
	// * @return
	// */
	// public boolean isMorning() {
	// return morning;
	// }
	//
	// /**
	// * Sets the morning value.
	// *
	// * @param morning
	// */
	// public void setMorning(boolean morning) {
	// this.morning = morning;
	// }

	/**
	 * Sets the time of the display to the specified hour and minute. It
	 * validates whether the hours are between 0 (inclusive) to 24.
	 * 
	 * @param hour
	 * @param minute
	 */
	public void setTime(int hour, int minute) {
		if ((hour >= 0) && (hour < 24)) {
			if (isSupport12HourFormat() && (12 == hour)) {
				hours.setValue(0);
			} else {
				hours.setValue(hour);
			}
		}
		if (minute > 0) {
			minutes.setValue(minute);
		}
		updateCurrentTime();
	}

	/**
	 * It does not seem to do anything. It would be called regularly (once a
	 * minute) by the clock timer to advance the clock. It advances the clock by
	 * one minute.
	 * 
	 * First, it increments the minutes by calling
	 * <code>minutes.increment()</code> method. And then it deals with the case
	 * where the hours are also incremented (for example, the time advances from
	 * "02:59" to "03:00"). We know that this should happen when the minutes
	 * roll over to zero, so it calls <code>hours.increment()</code>.
	 * 
	 * It checks whether the hours rolled over, meaning it either changed from
	 * AM to PM or vice-versa. Note the use of:
	 * <code>setMorning(!isMorning())<code>.
	 */
	public void timeTick() {
		/* Increments the minutes. */
		minutes.increment();
		/*
		 * Increments an hour(for example, the time advances from "02:59" to
		 * "03:00") when the minutes roll over to zero.
		 */
		if (minutes.getValue() == 0) {
			hours.increment();
			// /*
			// * update morning variable when the hours rolled over, meaning it
			// * either changed from AM to PM or vice-versa.
			// */
			// if (hours.getValue() >= 12) {
			// setMorning(!isMorning());
			// }
		}

		/**/
		updateCurrentTime();
	}

	/**
	 * Updates the current time.
	 */
	private void updateCurrentTime() {
		if (isSupport12HourFormat()) {
			/* version 1 implementation. */
			int hour = hours.getValue();
			if (hour < 12) {
				setMeridian("AM");
				if (hour == 0) {
					hour = 12;
				}
			} else {
				setMeridian("PM");
				if (hour > 12) {
					hour -= 12;
				}
			}

			/* version 2 implementation. */
			// /* update meridian depending on whether morning is true or false.
			// */
			// meridian = (isMorning() ? "AM" : "PM");
			currentTime = hour + ":" + minutes.getDisplayValue() + " "
					+ getMeridian();
		} else {
			currentTime = hours.getDisplayValue() + ":"
					+ minutes.getDisplayValue();
		}
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ClockDisplay<hours=" + getHours() + ", minutes=" + getMinutes()
				+ ">";
	}
}