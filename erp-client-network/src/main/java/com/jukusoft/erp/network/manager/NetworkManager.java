package com.jukusoft.erp.network.manager;

import com.jukusoft.erp.network.manager.impl.DefaultNetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.user.Account;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;

public interface NetworkManager {

    /**
    * initialize network manager
    */
    public void init ();

    public void connect (String ip, int port, Callback<NetworkResult<Boolean>> callback);

    public void disconnect ();

    public boolean isConnected ();

    public boolean isConnecting ();

    public void send (Message msg, Callback<NetworkResult<Message>> callback);

    public void addSubscriber (String event, Callback<Message> callback);

    public void removeSubscriber (String event, Callback<Message> callback);

    public void addGlobalSubscriber (Callback<Message> callback);

    public void removeGlobalSubscriber (Callback<Message> callback);

    public void login (String username, String password, Callback<NetworkResult<Account>> callback);

    public void executeBlocking (Runnable runnable);

    public void shutdown ();

    public static NetworkManager getInstance () {
        return DefaultNetworkManager.getManagerInstance();
    }

}
