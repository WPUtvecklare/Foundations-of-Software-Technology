/*
 * Date: 2020-09-08
 * File Name: Time.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

/**
 * Class Description: Represents time by hours, minutes and seconds
 * @version 1.0
 * @author Adam Bergman
 */
public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Constructor without arguments, setting time to 0:0:0
     */
    public Time () {
        this.setHours(0);
        this.setMinutes(0);
        this.setSeconds(0);
    }

    /**
     * Constructor with arguments
     * @param h hours to be set
     * @param m minutes to be set
     * @param s seconds to be set
     */
    public Time (int h, int m, int s) {
        this.setHours(h);
        this.setMinutes(m);
        this.setSeconds(s);
    }

    /**
     * Constructor taking seconds as argument
     * @param s seconds
     */
    public Time (int s) {
        this.setClock(s);
    }

    /**
     * Get hours
     * @return hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Get minutes
     * @return minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Get seconds
     * @return seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Set hours and validate range
     * @param h hours
     * @throws IllegalArgumentException when given hours are out of range
     */
    public void setHours(int h) throws IllegalArgumentException {
        if (h > 23 || h < 0) {
            throw new IllegalArgumentException("Hours must be between the range 0-23");
        }

        this.hours = h;
    }

    /**
     * Set minutes and validate range
     * @param m minutes
     * @throws IllegalArgumentException when given minutes are out of range
     */
    public void setMinutes(int m) throws IllegalArgumentException {
        if (m > 59 || m < 0) {
            throw new IllegalArgumentException("Minutes must be between the range 0-59");
        }

        this.minutes = m;
    }

    /**
     * Set seconds and validate range
     * @param s seconds
     * @throws IllegalArgumentException when given seconds are out of range
     */
    public void setSeconds(int s) throws IllegalArgumentException {
        if (s > 59 || s < 0) {
            throw new IllegalArgumentException("Seconds must be between the range 0-59");
        }

        this.seconds = s;
    }

    /**
     * Sets time based on seconds
     * @param seconds seconds
     * @throws IllegalArgumentException when seconds are greater than 24 hours or negative
     */
    private void setClock(int seconds) throws IllegalArgumentException {
        if (seconds >= 86400) {
            throw new IllegalArgumentException("Time cannot be longer than 24 hours");
        }

        if (seconds < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        this.setHours(seconds / 3600);
        this.setMinutes((seconds % 3600) / 60);
        this.setSeconds(seconds % 60);
    }

    /**
     * Increases time by one second
     */
    public void tick() {
        int totalTimeInSeconds = this.getTotalTimeInSeconds(this);
        this.setClock(totalTimeInSeconds + 1);
    }

    /**
     * Decreases time by one second
     */
    public void tickDown() {
        int totalTimeInSeconds = this.getTotalTimeInSeconds(this);
        this.setClock(totalTimeInSeconds - 1);
    }

    /**
     * Calculates the time from HH:MM:SS into seconds
     * @return time in seconds
     */
    public int getTotalTimeInSeconds (Time time) {
        return (time.hours * 60 * 60) + (time.minutes * 60) + time.seconds;
    }

    /**
     * Summarizes the time with a provided one
     * @param time Time to summarize with
     * @return a new Time object
     */
    public Time addTime(Time time) {
        int timeInSeconds = this.getTotalTimeInSeconds(this) + this.getTotalTimeInSeconds(time);
        return new Time(timeInSeconds);
    }

    /**
     * Subtracts the time by a given time
     * @param time Time to subtract with
     * @return a new Time object
     */
    public Time subtractTime(Time time) {
        int difference = this.getTotalTimeInSeconds(this) - this.getTotalTimeInSeconds(time);

        // Change it to positive
        difference = difference < 0 ? -difference : difference;

        return new Time(difference);
    }

    /**
     * Prints the time in a user-friendly format: HH:MM:SS
     * @return time
     */
    @Override
    public String toString() {
        return this.stringify(this.hours) + ":" + this.stringify(this.minutes) + ":" + this.stringify(this.seconds);
    }

    /**
     * Adds 0's if the number is under 10
     * @param number to add 0 to
     * @return time in "SS" format
     */
    private String stringify (int number) {
        return number < 10 ? "0" + number : String.valueOf(number);
    }
}
