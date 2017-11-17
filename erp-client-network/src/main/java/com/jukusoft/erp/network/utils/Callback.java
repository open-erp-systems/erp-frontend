package com.jukusoft.erp.network.utils;

@FunctionalInterface
public interface Callback<T> {

    public void handle (T param);

}
