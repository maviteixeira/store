package com.maviteixeira.store.stores.ports;

import com.maviteixeira.store.stores.CompactAddress;
import com.maviteixeira.store.stores.EmptyAddress;
import com.maviteixeira.store.stores.EmptyName;
import com.maviteixeira.store.stores.EmptyStoreId;
import com.maviteixeira.store.stores.FullName;
import com.maviteixeira.store.stores.PgStoreId;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import java.util.UUID;

public class StoreJsonOutTest {

    @Test
    public void givenEmptyDataWhenPrintShouldDisplayNothing() {
        final JsonObject json = new StoreJsonOut().print(
            new EmptyStoreId(),
            new EmptyName(),
            new EmptyAddress()
        );
        Assert.assertEquals("", json.getString("id"));
        Assert.assertEquals("", json.getString("fullName"));
        Assert.assertEquals("", json.getString("address"));
    }

    @Test
    public void givenAllDataWhenPrintShouldDisplayEverything() {
        String id = UUID.randomUUID().toString();
        final JsonObject json = new StoreJsonOut().print(
            new PgStoreId(id),
            new FullName("Jose"),
            new CompactAddress("Endereco")
        );
        Assert.assertEquals(id, json.getString("id"));
        Assert.assertEquals("Jose", json.getString("fullName"));
        Assert.assertEquals("Endereco", json.getString("address"));
    }
}
