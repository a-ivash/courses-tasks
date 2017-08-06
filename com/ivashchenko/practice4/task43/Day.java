package com.ivashchenko.practice4.task43;

import java.util.Random;

/**
 * Simple class containing int fields with different access level.
 * This class is used to demonstrate work with Reflection API.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class Day {
    private Random random = new Random();
    private int dayOfWeek;
    protected int dayOfMonth;
    public int dayOfYear;

    public Day(int dayOfWeek, int dayOfMonth, int dayOfYear) {
        this(dayOfWeek, dayOfMonth);
        this.dayOfYear = dayOfYear;
    }

    public Day(int dayOfWeek, int dayOfMonth) {
        this(dayOfWeek);
        this.dayOfMonth = dayOfMonth;
    }

    public Day(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = random.nextInt(100);
        this.dayOfYear = random.nextInt(100);
    }

    public Day() {
        this.dayOfWeek = random.nextInt(100);
        this.dayOfMonth = random.nextInt(100);
        this.dayOfYear = random.nextInt(100);
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }
}
