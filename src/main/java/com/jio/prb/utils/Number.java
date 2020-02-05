package com.jio.prb.utils;

public class Number {

    private long number;

    private String message;

    public Number(long number)
    {
        this.number = number;
    }

    public long getNumber()
    {
        return number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
