package com.jukusoft.erp.network.backend.impl;

import com.jukusoft.erp.network.backend.NetworkBackend;
import com.jukusoft.erp.network.message.MessageReceiver;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxNetworkBackend implements NetworkBackend<String> {

    //vertx options
    protected VertxOptions options = null;

    //instance of vertx
    protected Vertx vertx = null;

    public VertxNetworkBackend () {
        this.options = new VertxOptions();
        this.vertx = Vertx.vertx(this.options);
    }

    @Override
    public void connect(String server, int port, Callback<NetworkResult<Boolean>> callback) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void send(String msg) {

    }

    @Override
    public void setMessageReceiver(MessageReceiver<String> receiver) {

    }

}
