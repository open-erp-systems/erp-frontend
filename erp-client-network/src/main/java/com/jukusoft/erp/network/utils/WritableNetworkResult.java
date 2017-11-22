package com.jukusoft.erp.network.utils;

public class WritableNetworkResult<T> implements NetworkResult<T> {

    protected volatile boolean failed = false;
    protected volatile boolean successful = false;

    protected T result = null;
    protected Throwable cause = null;
    protected String causeMessage = "";

    @Override
    public T result() {
        if (!succeeded()) {
            throw new IllegalStateException("Cannot get result, because future operation wasnt successful.");
        }

        return this.result;
    }

    @Override
    public Throwable cause() {
        return this.cause;
    }

    @Override
    public String causeMessage() {
        if (this.causeMessage.isEmpty()) {
            if (this.cause() != null) {
                return this.cause.getMessage();
            } else {
                return "Unknown";
            }
        } else {
            return this.causeMessage;
        }
    }

    @Override
    public boolean succeeded() {
        return this.successful;
    }

    @Override
    public boolean failed() {
        return this.failed;
    }

    public void complete (T result) {
        this.result = result;
        this.successful = true;
    }

    public void fail (Throwable cause) {
        this.cause = cause;
        this.failed = true;
    }

    public void fail (String causeMessage) {
        this.cause = new Exception(causeMessage);
        this.causeMessage = causeMessage;
        this.failed = true;
    }

}
