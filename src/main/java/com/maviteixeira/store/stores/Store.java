package com.maviteixeira.store.stores;

public interface Store {

    void moveAndRename(Address address, Name name);

    <T> T print(Store.Out<T> out);

    interface Out<T> {
        T print(StoreId id, Name name, Address address);
    }
}
