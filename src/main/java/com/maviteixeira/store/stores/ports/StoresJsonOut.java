package com.maviteixeira.store.stores.ports;

import com.maviteixeira.store.stores.Store;
import com.maviteixeira.store.stores.Stores;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Iterator;

public class StoresJsonOut implements Stores.Out<JsonObject> {

    @Override
    public JsonObject print(Iterator<Store> stores) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        stores.forEachRemaining(store -> jsonArrayBuilder.add(store.print(new StoreJsonOut())));
        return jsonObjectBuilder.add("data", jsonArrayBuilder.build()).build();
    }
}
