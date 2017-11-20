package com.jukusoft.erp.gui.eventbus;

import com.jukusoft.erp.network.message.Message;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {

    //map with all specific event listeners
    protected Map<String,List<EventListener>> eventListenerMap = new ConcurrentHashMap<>();

    //list with all global listeners
    protected List<EventListener> globalListeners = Collections.synchronizedList(new ArrayList<>());

    //singleton instance of class
    protected static EventBus instance = null;

    /**
    * protected constructor, because this class uses the singleton design pattern
    */
    protected EventBus () {
        //
    }

    /**
    * raise event
    */
    public void raiseEvent (String event, JsonObject eventData) {
        List<EventListener> list = this.eventListenerMap.get(event);

        if (list == null) {
            //no event listener found
            System.out.println("INFO: No event listener found for internal event '" + event + "'.");

            return;
        }

        //call all listeners
        for (EventListener listener : list) {
            listener.onEvent(event, eventData);
        }
    }

    public void raiseEvent (Message msg) {
        //first check, if it is an internal event
        if (!msg.getEvent().startsWith("internal:")) {
            throw new IllegalArgumentException("message isnt an internal event.");
        }

        //get event
        String event = msg.getEvent().replace("internal:", "");

        //check, if event name should be overriden
        if (msg.getData().containsKey("internal_event")) {
            event = msg.getData().getString("internal_event");
        }

        //get event data
        JsonObject eventData = msg.getData().getJsonObject("event_data");

        //raise internal event
        this.raiseEvent(event, eventData);
    }

    public void addListener (String event, EventListener listener) {
        //create list, if neccessary
        if (this.eventListenerMap.get(event) == null) {
            this.eventListenerMap.put(event, new ArrayList<>());
        }

        //get list first
        List<EventListener> list = this.eventListenerMap.get(event);

        list.add(listener);
    }

    public void removeListener (String event, EventListener listener) {
        if (this.eventListenerMap.get(event) == null) {
            //we dont need to remove listener, because no listener is registered for this event
            return;
        }

        //get list
        List<EventListener> list = this.eventListenerMap.get(event);

        //remove listener from list
        list.remove(listener);
    }

    public void addGlobalListener (EventListener listener) {
        this.globalListeners.add(listener);
    }

    public void removeGlobalListener (EventListener listener) {
        this.globalListeners.remove(listener);
    }

    public static EventBus getInstance () {
        if (EventBus.instance == null) {
            //create new instance
            EventBus.instance = new EventBus();
        }

        //return singleton instance
        return EventBus.instance;
    }

}
