package com.maviteixeira.store.stores;


import com.maviteixeira.store.IntegrationTests;
import com.maviteixeira.store.helper.StoreMapOut;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class PgPlazaTest extends IntegrationTests {

    @Test
    public void givenStoreDataWhenSaveShouldBeSaved() {
        Store store = new PgPlaza(dataSource).create(
            new FullName("Jose"),
            new CompactAddress("Address Street")
        );
        Map<String, String> printed = store.print(new StoreMapOut());
        Assert.assertNotNull(printed.get("id"));
        Assert.assertEquals("Jose", printed.get("name"));
        Assert.assertEquals("Address Street", printed.get("address"));
    }

    @Test
    public void givenStoreDataWhenSaveShouldBeFoundSearchingById() {
        Stores plaza = new PgPlaza(dataSource);
        Store store = plaza.create(
            new FullName("Jose"),
            new CompactAddress("Address Street")
        );
        Map<String, String> printed = store.print(new StoreMapOut());
        Store savedStore = plaza.find(printed.get("id"));

        Map<String, String> savedPrinted = savedStore.print(new StoreMapOut());
        Assert.assertEquals(printed.get("id"), savedPrinted.get("id"));
        Assert.assertEquals(printed.get("name"), savedPrinted.get("name"));
        Assert.assertEquals(printed.get("address"), savedPrinted.get("address"));
    }

    @Test
    public void givenSomeStoresWhenIterateShoulbBeAllReturned() {
        Stores plaza = new PgPlaza(dataSource);
        plaza.create(
            new FullName("Jose"),
            new CompactAddress("Address Street")
        );
        plaza.create(
            new FullName("Joao"),
            new CompactAddress("Other Address Street")
        );
        int numberOfStores = 0;
        for (Store store : plaza) {
            numberOfStores++;
        }
        Assert.assertEquals(2, numberOfStores, 0);
    }

    @Test
    public void givenSomeStoresWhenFilteredShouldReturnFilteredStores() {
        final Stores plaza = new PgPlaza(dataSource);
        plaza.create(
            new FullName("Jose"),
            new CompactAddress("Address Street")
        );
        plaza.create(
            new FullName("Maria"),
            new CompactAddress("Other Address Street")
        );
        Stores filteredPlaza = plaza.filter(new FullName("M"), new EmptyAddress());
        for (Store store : filteredPlaza) {
            Map<String, String> filtered = store.print(new StoreMapOut());
            Assert.assertEquals("Maria", filtered.get("name"));
            Assert.assertEquals("Other Address Street", filtered.get("address"));
        }
    }

    @Test
    public void givenSomeStoresWhenNoFilterIsAppliedShouldReturnAllStores() {
        final Stores plaza = new PgPlaza(dataSource);
        plaza.create(
            new FullName("Jose"),
            new CompactAddress("Address Street")
        );
        plaza.create(
            new FullName("Maria"),
            new CompactAddress("Other Address Street")
        );
        Stores filteredPlaza = plaza.filter(new EmptyName(), new EmptyAddress());
        int numberOfStores = 0;
        for (Store store : filteredPlaza) {
            numberOfStores++;
        }
        Assert.assertEquals(2, numberOfStores, 0);
    }
}
