package com.maviteixeira.store.stores;

public class EmptyStore implements Store {

    @Override
    public void moveAndRename(Address address, Name name) {
        throw new RuntimeException("This find is empty");
    }

    @Override
    public <T> T print(Out<T> out) {
        return out.print(
            new EmptyStoreId(),
            new EmptyName(),
            new EmptyAddress()
        );
    }
}
