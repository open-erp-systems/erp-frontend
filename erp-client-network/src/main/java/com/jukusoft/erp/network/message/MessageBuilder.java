package com.jukusoft.erp.network.message;

import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.utils.Callback;
import com.jukusoft.erp.network.utils.NetworkResult;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class MessageBuilder {

    //instance of message
    protected  Message message = null;

    protected MessageBuilder (String event) {
        this.message = Message.createRequest(event);
    }

    public MessageBuilder putInt (String key, int value) {
        message.getData().put(key, value);

        return this;
    }

    public MessageBuilder putDouble (String key, double value) {
        message.getData().put(key, value);

        return this;
    }

    public MessageBuilder putFloat (String key, float value) {
        message.getData().put(key, value);

        return this;
    }

    public MessageBuilder putString (String key, String value) {
        message.getData().put(key, value);

        return this;
    }

    public MessageBuilder putJsonObject (String key, JsonObject json) {
        message.getData().put(key, json);

        return this;
    }

    public MessageBuilder putJsonArray (String key, JsonArray json) {
        message.getData().put(key, json);

        return this;
    }

    public void send (Callback<NetworkResult<Message>> callback) {
        NetworkManager.getInstance().send(this.message, callback);
    }

    public static MessageBuilder create (String event) {
        return new MessageBuilder(event);
    }

}
