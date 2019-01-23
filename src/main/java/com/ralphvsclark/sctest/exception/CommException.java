package com.ralphvsclark.sctest.exception;

public class CommException extends Exception {

    protected int code = 0;

    protected String message;

    public CommException(int code, String message) {
        super( String.format("%d - %s", code, message) );
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", code, message);
    }
}
