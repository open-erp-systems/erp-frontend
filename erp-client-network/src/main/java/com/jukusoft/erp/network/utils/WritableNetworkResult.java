package com.jukusoft.erp.network.utils;

public class WritableNetworkResult<T> implements NetworkResult<T> {

    protected volatile boolean failed = false;
    protected volatile boolean successful = false;

    protected T result = null;
    protected Throwable cause = null;

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

}
