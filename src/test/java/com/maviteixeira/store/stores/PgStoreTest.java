package com.maviteixeira.store.stores;

import com.maviteixeira.store.IntegrationTests;
import com.maviteixeira.store.helper.StoreMapOut;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class PgStoreTest extends IntegrationTests {

    @Test
    public void givenStoreWhenChangedNameAndAddressThenStoreShouldBeChanged() {
        Store store = new PgPlaza(dataSource).create(
            new SimpleName("Jose"),
            new CompactAddress("Address Street")
        );
        store.moveAndRename(
            new CompactAddress("Changed Address"),
            new SimpleName("Joao")
        );
        Map<String, String> printed = store.print(new StoreMapOut());
        Assert.assertEquals("Joao", printed.get("name"));
        Assert.assertEquals("Changed Address", printed.get("address"));
    }
}
