package com.jukusoft.erp.network.manager;

import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.utils.Callback;

public interface NetworkManager {

    public void send (Message msg, Callback<Message> callback);

    public void addSubscriber (String event, Callback<Message> callback);

    public void removeSubscriber (String event, Callback<Message> callback);

    public void login ();

}
