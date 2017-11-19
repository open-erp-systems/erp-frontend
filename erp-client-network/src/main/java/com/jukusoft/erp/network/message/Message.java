package com.jukusoft.erp.network.message;

import com.jukusoft.erp.network.json.JsonSerializable;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
* object for an message
*/
public class Message implements JsonSerializable {

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

    public String getEvent () {
        return this.event;
    }

    public JsonObject getData() {
        return this.data;
    }

    public void setSSID (String ssid) {
        this.ssid = ssid;
    }

    public UUID getID () {
        return this.uuid;
    }

    public String getIDAsString () {
        return this.uuid.toString();
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

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();

        //add header information
        json.put("event", this.event);
        json.put("messageID", this.uuid.toString());

        //add message body
        json.put("data", this.data);

        if (!this.ssid.isEmpty()) {
            json.put("ssid", this.ssid);
        }

        return json;
    }

    @Override
    public String toString () {
        return toJSON().encode();
    }

    public String encode () {
        return toString();
    }

}
