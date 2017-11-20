package com.jukusoft.erp.gui.eventbus;

import io.vertx.core.json.JsonObject;

@FunctionalInterface
public interface EventListener {

    /**
    * this method is called, if an event was raised
     *
     * @param event name of internal event
     * @param eventData event data
    */
    public void onEvent (String event, JsonObject eventData);

}
