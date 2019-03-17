package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.exceptions.AppException;
import org.slf4j.Logger;

import java.util.Iterator;

public class LoggedStores implements Stores {

    private final Stores origin;
    private final Logger logger;

    public LoggedStores(Stores origin, Logger logger) {
        this.origin = origin;
        this.logger = logger;
    }

    @Override
    public Store create(Name name, Address address) {
        try {
            return this.origin.create(name, address);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }

    @Override
    public Stores filter(Name name, Address address) {
        try {
            return this.origin.filter(name, address);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }

    @Override
    public Store find(String id) {
        try {
            return this.origin.find(id);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }

    @Override
    public <T> T print(Out<T> out) {
        try {
            return this.origin.print(out);
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }

    @Override
    public Iterator<Store> iterator() {
        try {
            return this.origin.iterator();
        } catch (AppException ex) {
            logger.error("Error " + ex.getCause());
            throw ex;
        }
    }
}
