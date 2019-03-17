package com.maviteixeira.store.helper;

import com.maviteixeira.store.stores.Address;
import com.maviteixeira.store.stores.Name;
import com.maviteixeira.store.stores.Store;
import com.maviteixeira.store.stores.StoreId;

import java.util.HashMap;
import java.util.Map;

public class StoreMapOut implements Store.Out<Map<String, String>> {

    @Override
    public Map<String, String> print(StoreId id, Name name, Address address) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", id.value());
        attributes.put("name", name.asString());
        attributes.put("address", address.asString());
        return attributes;
    }

}
