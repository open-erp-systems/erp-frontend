package com.jukusoft.erp.network.backend;

import com.jukusoft.erp.network.message.MessageReceiver;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;

public interface NetworkBackend<T> {

    /**
    * try to connect to network server asynchronous
     *
     * @param server ip of server
     * @param port port of server
     * @param callback callback to execute, if connection has established or failed
    */
    public void connect (String server, int port, Callback<NetworkResult<Boolean>> callback);

    public void disconnect ();

    /**
    * send message to server
     *
     * @param msg instance of message
    */
    public void send (T msg);

    /**
    * set message receiver to listen to messages and handle them
     *
     * @param receiver instance of message receiver
    */
    public void setMessageReceiver (MessageReceiver<T> receiver);

}
