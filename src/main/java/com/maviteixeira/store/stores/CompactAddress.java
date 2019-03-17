package com.maviteixeira.store.stores;

public class CompactAddress implements Address {

    private final String compactAddress;

    public CompactAddress(String compactAddress) {
        this.compactAddress = compactAddress;
    }

    @Override
    public String asString() {
        return compactAddress;
    }
}
