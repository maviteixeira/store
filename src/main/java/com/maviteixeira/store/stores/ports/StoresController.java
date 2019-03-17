package com.maviteixeira.store.stores.ports;

import com.maviteixeira.store.stores.CompactAddress;
import com.maviteixeira.store.stores.LoggedStore;
import com.maviteixeira.store.stores.PgPlaza;
import com.maviteixeira.store.stores.PgStore;
import com.maviteixeira.store.stores.PgStoreId;
import com.maviteixeira.store.stores.SimpleName;
import com.maviteixeira.store.stores.Store;
import com.maviteixeira.store.stores.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/stores")
public class StoresController {

    private final DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(StoresController.class);

    public StoresController(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping(method = RequestMethod.GET,
        produces = {"application/json"})
    public ResponseEntity getStores(@RequestParam(required = false) final String name,
                                    @RequestParam(required = false) final String address) {
        Stores filteredStores =
            new PgPlaza(dataSource)
                .filter(
                    new SimpleName(name),
                    new CompactAddress(address)
                );
        return new ResponseEntity<>(filteredStores.print(new StoresJsonOut()).toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
        produces = {"application/json"})
    public ResponseEntity createStore(@RequestBody CreateStoreVO createStoreVO) {
        Store store = new PgPlaza(dataSource)
            .create(
                new SimpleName(createStoreVO.getName()),
                new CompactAddress(createStoreVO.getAddress())
            );
        return new ResponseEntity<>(store.print(new StoreJsonOut()).toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
        produces = {"application/json"},
        method = RequestMethod.PUT)
    public ResponseEntity updateStore(@PathVariable("id") final String id,
                                      @RequestBody UpdateStoreVO updateStoreVO) {
        Store store = new LoggedStore(
            new PgStore(
                new PgStoreId(id),
                dataSource
            ), logger
        );
        store.moveAndRename(
            new CompactAddress(updateStoreVO.getAddress()),
            new SimpleName(updateStoreVO.getName())
        );
        return new ResponseEntity<>(store.print(new StoreJsonOut()).toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
        produces = {"application/json"},
        method = RequestMethod.GET)
    public ResponseEntity getStore(@PathVariable("id") final String id) {
        return new ResponseEntity<>(
            new LoggedStore(
                new PgStore(
                    new PgStoreId(id),
                    this.dataSource
                ), logger
            ).print(new StoreJsonOut()).toString(),
            HttpStatus.OK
        );
    }

}
