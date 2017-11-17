package com.jukusoft.erp.network.manager.impl;

import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;

public class DefaultNetworkManager implements NetworkManager {

    protected static DefaultNetworkManager instance = null;

    public static DefaultNetworkManager getManagerInstance () {
        if (instance == null) {
            instance = new DefaultNetworkManager();
        }

        return instance;
    }

    @Override
    public void init() {
        //
    }

    @Override
    public void connect(String ip, int port, Callback<NetworkResult<Boolean>> callback) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isConnecting() {
        return false;
    }

    @Override
    public void send(Message msg, Callback<Message> callback) {

    }

    @Override
    public void addSubscriber(String event, Callback<Message> callback) {

    }

    @Override
    public void removeSubscriber(String event, Callback<Message> callback) {

    }

    @Override
    public void login() {

    }

}
