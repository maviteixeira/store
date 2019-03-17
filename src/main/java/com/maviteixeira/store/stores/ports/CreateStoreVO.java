package com.maviteixeira.store.stores.ports;

public class CreateStoreVO {

    private final String name;
    private final String address;

    public CreateStoreVO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}