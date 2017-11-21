package com.jukusoft.erp.gui.logging;

import io.vertx.core.json.JsonObject;

public class LocalConsoleLogger implements ILogging {

    public LocalConsoleLogger() {
        //
    }

    @Override
    public void debug(long messageID, String tag, String message) {
        this.log("DEBUG", messageID, tag, message);
    }

    @Override
    public void debug(String tag, String message) {
        this.log("DEBUG", -1, tag, message);
    }

    @Override
    public void info(long messageID, String tag, String message) {
        this.log("INFO", messageID, tag, message);
    }

    @Override
    public void info(String tag, String message) {
        this.log("INFO", -1, tag, message);
    }

    @Override
    public void warn(long messageID, String tag, String message) {
        this.log("WARN", messageID, tag, message);
    }

    @Override
    public void warn(String tag, String message) {
        this.log("INFO", -1, tag, message);
    }

    @Override
    public void error(long messageID, String tag, String message) {
        this.log("ERROR", messageID, tag, message);
    }

    @Override
    public void error(String tag, String message) {
        this.log("INFO", -1, tag, message);
    }

    protected void log (String logLevel, long messageID, String tag, String message) {
        JsonObject json = new JsonObject();

        if (messageID != -1) {
            json.put("is_message_log", true);
        }

        json.put("timestamp", System.currentTimeMillis());
        json.put("log_level", logLevel);
        json.put("messageID", message);
        json.put("tag", tag);
        json.put("message", message);

        //also log to console
        if (messageID == -1) {
            System.out.println("[messageID=null] " + logLevel.toUpperCase() + ": " + tag + ": " + message);
        } else {
            System.out.println("[messageID=" + messageID + "] " + logLevel.toUpperCase() + ": " + tag + ": " + message);
        }
    }

}
