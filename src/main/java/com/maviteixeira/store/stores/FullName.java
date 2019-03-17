package com.maviteixeira.store.stores;

public class FullName implements Name {

    private final String fullName;

    public FullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String asString() {
        return fullName;
    }
}
