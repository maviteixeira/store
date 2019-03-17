package com.maviteixeira.store.stores;

import java.util.UUID;

public class PgStoreId implements StoreId {

    private final UUID id;

    public PgStoreId(final UUID id) {
        this.id = id;
    }

    public PgStoreId(final String id) {
        this(UUID.fromString(id));
    }

    @Override
    public String value() {
        return id.toString();
    }
}
