package com.maviteixeira.store.stores;

import com.maviteixeira.store.helper.StoreMapOut;
import com.maviteixeira.store.shared.exceptions.AppException;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class LoggedStoreTest {

    @Test
    public void givenLoggedDecoratorForUpdateWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Store store = Mockito.mock(Store.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(store).moveAndRename(Mockito.any(), Mockito.any());
        try {
            new LoggedStore(store, logger)
                .moveAndRename(new EmptyAddress(), new EmptyName());
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

    @Test
    public void givenLoggedDecoratorForPrintingWhenExceptionHappenThenShouldBeLogged() {
        Logger logger = Mockito.mock(Logger.class);
        Store store = Mockito.mock(Store.class);
        Mockito.doThrow(new AppException(new RuntimeException("Teste")))
            .when(store).print(Mockito.any());
        try {
            new LoggedStore(store, logger)
                .print(new StoreMapOut());
        } catch (AppException ex) {
            //Verify should be executed (must be a better way to do this)
        }
        Mockito.verify(logger).error(Mockito.anyString());
    }

}
