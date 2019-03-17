package com.maviteixeira.store.stores;

import java.util.Iterator;

public interface Stores extends Iterable<Store> {

    Store create(final Name name, final Address address);

    Stores filter(final Name name, final Address address);

    Store find(final String id);

    <T> T print(Stores.Out<T> out);

    interface Out<T> {
        T print(Iterator<Store> stores);
    }

}
