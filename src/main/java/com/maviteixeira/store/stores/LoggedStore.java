package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.exceptions.AppException;
import org.slf4j.Logger;

//Need to detail better
public class LoggedStore implements Store {

    private final Store origin;
    private final Logger logger;

    public LoggedStore(Store origin, Logger logger) {
        this.origin = origin;
        this.logger = logger;
    }

    @Override
    public void moveAndRename(Address address, Name name) {
        try {
            origin.moveAndRename(address, name);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }

    @Override
    public <T> T print(Out<T> out) {
        try {
            return origin.print(out);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }
}
