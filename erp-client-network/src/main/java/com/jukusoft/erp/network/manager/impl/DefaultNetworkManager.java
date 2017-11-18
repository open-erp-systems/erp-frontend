package com.jukusoft.erp.network.manager.impl;

import com.jukusoft.erp.network.backend.NetworkBackend;
import com.jukusoft.erp.network.backend.impl.VertxNetworkBackend;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.message.MessageReceiver;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;

public class DefaultNetworkManager implements NetworkManager, MessageReceiver<String> {

    protected static DefaultNetworkManager instance = null;

    //network backend
    protected NetworkBackend<String> networkBackend = null;

    protected volatile boolean connecting = false;

    protected DefaultNetworkManager () {
        //create new vertx network backend
        this.networkBackend = new VertxNetworkBackend();

        //set message receiver
        this.networkBackend.setMessageReceiver(this);
    }

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
        if (this.connecting) {
            throw new IllegalStateException("Cannot connect to server, because network backend is already in connecting state.");
        }

        //set connecting flag
        this.connecting = true;

        this.networkBackend.connect(ip, port, (NetworkResult<Boolean> res) -> {
            //reset connecting flag
            this.connecting = false;

            //call handler
            callback.handle(res);
        });
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isConnecting() {
        return this.connecting;
    }

    @Override
    public void send(Message msg, Callback<Message> callback) {
        //
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

    @Override
    public void executeBlocking(Runnable runnable) {
        this.networkBackend.executeBlocking(runnable);
    }

    @Override
    public void onReceive(String msg) {
        //
    }

}
