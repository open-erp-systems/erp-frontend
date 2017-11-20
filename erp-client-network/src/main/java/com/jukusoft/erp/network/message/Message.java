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

    public boolean succeeded () {
        return this.type == ResponseType.OK;
    }

    public ResponseType getType() {
        return this.type;
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
        if (data.containsKey("password")) {
            return "Message (event: " + event + ", messageID: " + uuid.toString() + ", data: ********)";
        } else {
            return "Message (event: " + event + ", messageID: " + uuid.toString() + ", data: " + data.encode() + ")";
        }
    }

    public String encode () {
        return toString();
    }

    public static Message createFromJSON (JsonObject json) {
        Message message = new Message();

        //get header information
        message.event = json.getString("event");
        String messageID = json.getString("messageID");
        int statusCode = json.getInteger("statusCode");
        message.type = ResponseType.getByString(json.getString("status"));
        message.data = json.getJsonObject("data");
        message.ssid = json.getString("ssid");

        if (!messageID.isEmpty() && !messageID.equals("none")) {
            //get UUID
            message.uuid = UUID.fromString(messageID);
        }

        return message;
    }

}
