package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.exceptions.AppException;
import com.maviteixeira.store.stores.ports.StoresJsonOut;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class LoggedStoresTest {

    @Test
    public void givenLoggedDecoratorForPrintingWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Stores stores = Mockito.mock(Stores.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(stores).print(Mockito.any());
        try {
            new LoggedStores(stores, logger)
                .print(new StoresJsonOut());
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

    @Test
    public void givenLoggedDecoratorForFindingStoreWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Stores stores = Mockito.mock(Stores.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(stores).find(Mockito.any());
        try {
            new LoggedStores(stores, logger)
                .find("");
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

    @Test
    public void givenLoggedDecoratorForCreatingStoreWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Stores stores = Mockito.mock(Stores.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(stores).create(Mockito.any(), Mockito.any());
        try {
            new LoggedStores(stores, logger)
                .create(null, null);
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

    @Test
    public void givenLoggedDecoratorForFilteringStoreWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Stores stores = Mockito.mock(Stores.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(stores).filter(Mockito.any(), Mockito.any());
        try {
            new LoggedStores(stores, logger)
                .filter(null, null);
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

    @Test
    public void givenLoggedDecoratorForIteratingStoresWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Stores stores = Mockito.mock(Stores.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(stores).iterator();
        try {
            new LoggedStores(stores, logger)
                .iterator();
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

}
