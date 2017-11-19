package com.jukusoft.erp.network.manager.impl;

import com.jukusoft.erp.network.backend.NetworkBackend;
import com.jukusoft.erp.network.backend.impl.VertxNetworkBackend;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.message.Message;
import com.jukusoft.erp.network.message.MessageReceiver;
import com.jukusoft.erp.network.user.Account;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultNetworkManager implements NetworkManager, MessageReceiver<String> {

    protected static DefaultNetworkManager instance = null;

    //network backend
    protected NetworkBackend<String> networkBackend = null;

    protected volatile boolean connecting = false;

    //session ID
    protected String ssid = "";

    //map with all asynchronous callback handlers
    protected Map<UUID,Callback<NetworkResult<Message>>> callbackMap = new ConcurrentHashMap<>();

    protected Account account = null;

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
    public void send(Message msg, Callback<NetworkResult<Message>> callback) {
        if (!this.ssid.isEmpty()) {
            //set session id
            msg.setSSID(this.ssid);
        }

        //add callback to map
        this.callbackMap.put(msg.getID(), callback);

        //send message
        this.networkBackend.send(msg.encode());
    }

    @Override
    public void addSubscriber(String event, Callback<Message> callback) {

    }

    @Override
    public void removeSubscriber(String event, Callback<Message> callback) {

    }

    @Override
    public void login(String username, String password, Callback<NetworkResult<Account>> callback) {
        //log
        System.out.println("Try to login user '" + username + "'...");

        //create new message
        Message msg = Message.createRequest("auth");

        //fill message with authentification data
        msg.getData().put("username", username);
        msg.getData().put("password", password);

        this.send(msg, res -> {
            if (!res.succeeded()) {
                callback.handle(NetworkResult.fail(res.cause()));
            } else {
                //get response
                Message response = res.result();

                //check, if user is logged in
                if (!response.getData().getString("login_state").equals("success")) {
                    callback.handle(NetworkResult.fail(res.cause()));
                } else {
                    //get data
                    String loginMessage = response.getData().getString("login_message");
                    long userID = response.getData().getLong("userID");
                    String username1 = response.getData().getString("username");

                    //create account
                    this.account = new Account(userID, username1);

                    //call callback
                    callback.handle(NetworkResult.complete(this.account));
                }
            }
        });
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
