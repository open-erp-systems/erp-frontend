package com.jukusoft.erp.network.message;

public interface MessageReceiver<T> {

    /**
    * receive message
    */
    public void onReceive (T msg);

}
