package com.jukusoft.erp.network.message;

import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
* object for an message
*/
public class Message {

    //session ID
    protected String ssid = "";

    //event name
    protected String event = "";

    //data
    protected JsonObject data = new JsonObject();

    //unique message ID
    protected UUID uuid = UUID.randomUUID();

    protected ResponseType type = null;

    protected Message () {
        //
    }

    public static Message createRequest (String event) {
        //create new message
        Message message = new Message();

        message.event = event;

        return message;
    }

    public static Message createResponse (String event, ResponseType type) {
        //create new message
        Message message = new Message();

        message.event = event;
        message.type = type;

        return message;
    }

}
