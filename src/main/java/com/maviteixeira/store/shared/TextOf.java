package com.maviteixeira.store.shared;

public class TextOf implements Text {

    private final String value;

    public TextOf(String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value;
    }
}
