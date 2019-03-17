package com.maviteixeira.store.stores.ports;

import com.maviteixeira.store.stores.Address;
import com.maviteixeira.store.stores.Name;
import com.maviteixeira.store.stores.Store;
import com.maviteixeira.store.stores.StoreId;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class StoreJsonOut implements Store.Out<JsonObject> {

    @Override
    public JsonObject print(StoreId id, Name name, Address address) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("id", id.value());
        jsonBuilder.add("fullName", name.asString());
        jsonBuilder.add("address", address.asString());
        return jsonBuilder.build();
    }
}
