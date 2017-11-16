package com.jukusoft.erp.network.manager;

import com.jukusoft.erp.network.manager.impl.DefaultNetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.utils.Callback;

public interface NetworkManager {

    /**
    * initialize network manager
    */
    public void init ();

    public void send (Message msg, Callback<Message> callback);

    public void addSubscriber (String event, Callback<Message> callback);

    public void removeSubscriber (String event, Callback<Message> callback);

    public void login ();

    public static NetworkManager getInstance () {
        return DefaultNetworkManager.getManagerInstance();
    }

}
