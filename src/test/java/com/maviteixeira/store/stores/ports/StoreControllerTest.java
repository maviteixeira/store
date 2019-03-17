package com.maviteixeira.store.stores.ports;

import com.maviteixeira.store.IntegrationTests;
import com.maviteixeira.store.helper.StoreMapOut;
import com.maviteixeira.store.stores.CompactAddress;
import com.maviteixeira.store.stores.PgPlaza;
import com.maviteixeira.store.stores.SimpleName;
import com.maviteixeira.store.stores.Store;
import com.maviteixeira.store.stores.Stores;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Map;

public class StoreControllerTest extends IntegrationTests {

    @Test
    public void givenControllerRequestWhenPostisMadeShouldCreateAStore() {
        StoresController controller = new StoresController(dataSource);
        ResponseEntity response = controller.createStore(new CreateStoreVO("Jose", "Address"));
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try (JsonReader jsonReader = Json.createReader(new StringReader(response.getBody().toString()))) {
            JsonObject json = jsonReader.readObject();
            Assert.assertEquals("Jose", json.getString("fullName"));
            Assert.assertEquals("Address", json.getString("address"));
        }
    }

    @Test
    public void givenControllerRequestWhenGetStoreByIdIsMadeShouldReturnAStore() {
        Store store =
            new PgPlaza(dataSource)
                .create(
                    new SimpleName("Joao"),
                    new CompactAddress("Address")
                );
        Map<String, String> printed = store.print(new StoreMapOut());

        StoresController controller = new StoresController(dataSource);
        ResponseEntity response = controller.getStore(printed.get("id"));
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try (JsonReader jsonReader = Json.createReader(new StringReader(response.getBody().toString()))) {
            JsonObject json = jsonReader.readObject();
            Assert.assertEquals(printed.get("id"), json.getString("id"));
            Assert.assertEquals("Joao", json.getString("fullName"));
            Assert.assertEquals("Address", json.getString("address"));
        }
    }

    @Test
    public void givenControllerRequestWhenStoreIsUpdatedShouldReturnUpdatedStore() {
        Store store =
            new PgPlaza(dataSource)
                .create(
                    new SimpleName("Joao"),
                    new CompactAddress("Address")
                );
        Map<String, String> printed = store.print(new StoreMapOut());

        StoresController controller = new StoresController(dataSource);
        ResponseEntity response = controller.updateStore(
            printed.get("id"),
            new UpdateStoreVO("Robert", "Changed Address")
        );
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try (JsonReader jsonReader = Json.createReader(new StringReader(response.getBody().toString()))) {
            JsonObject json = jsonReader.readObject();
            Assert.assertEquals(printed.get("id"), json.getString("id"));
            Assert.assertEquals("Robert", json.getString("fullName"));
            Assert.assertEquals("Changed Address", json.getString("address"));
        }
    }

    @Test
    public void givenControllerRequestWhenGetStoresByNameIsMadeShouldReturnFilteredStores() {
        Stores plaza = new PgPlaza(dataSource);
        plaza.create(
            new SimpleName("Joao"),
            new CompactAddress("Address")
        );
        plaza.create(
            new SimpleName("Robert"),
            new CompactAddress("Other Address")
        );

        StoresController controller = new StoresController(dataSource);
        ResponseEntity response = controller.getStores("Robe", null);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try (JsonReader jsonReader = Json.createReader(new StringReader(response.getBody().toString()))) {
            JsonArray jsonArray = jsonReader.readObject().getJsonArray("data");
            JsonObject json = jsonArray.getJsonObject(0);
            Assert.assertEquals("Robert", json.getString("fullName"));
            Assert.assertEquals("Other Address", json.getString("address"));
        }
    }

}
