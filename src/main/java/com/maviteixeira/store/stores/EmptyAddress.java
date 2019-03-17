package com.maviteixeira.store.stores;

public class EmptyAddress implements Address {

    @Override
    public String asString() {
        return "";
    }
}
