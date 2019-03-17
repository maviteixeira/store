package com.maviteixeira.store.shared;

public class NoNullText implements Text {

    private final Text origin;

    public NoNullText(Text origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        try {
            return origin.asString();
        } catch (NullPointerException ex) {
            return "";
        }
    }
}
