package com.jukusoft.erp.network.manager;

import com.jukusoft.erp.network.manager.impl.DefaultNetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;
import io.vertx.core.AsyncResult;

public interface NetworkManager {

    /**
    * initialize network manager
    */
    public void init ();

    public void connect (String ip, int port, Callback<NetworkResult<Boolean>> callback);

    public boolean isConnected ();

    public boolean isConnecting ();

    public void send (Message msg, Callback<Message> callback);

    public void addSubscriber (String event, Callback<Message> callback);

    public void removeSubscriber (String event, Callback<Message> callback);

    public void login ();

    public void executeBlocking (Runnable runnable);

    public static NetworkManager getInstance () {
        return DefaultNetworkManager.getManagerInstance();
    }

}
