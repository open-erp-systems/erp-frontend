package com.jukusoft.erp.network.json;

import io.vertx.core.json.JsonObject;

/**
 * Created by Justin on 10.02.2017.
 */
public interface JsonLoadable {

    public void loadFromJSON(JsonObject json);

}
