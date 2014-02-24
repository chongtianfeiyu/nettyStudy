package com.qunar.model;

import java.util.Date;

/**
 * Date: 2014-02-24 19:14
 *
 * @author sen.chai
 */
public class UnixTime {

    private final int value;

    public UnixTime() {
        this((int) (System.currentTimeMillis() / 1000L + 2208988800L));
    }

    public UnixTime(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
