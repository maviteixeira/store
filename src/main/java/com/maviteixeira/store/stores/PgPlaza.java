package com.maviteixeira.store.stores;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PgPlaza implements Stores {

    private final DataSource dataSource;

    public PgPlaza(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Store create(final Name name, final Address address) {
        StoreId id = new PgStoreId(UUID.randomUUID());

        JdbcOperations jdbc = new JdbcTemplate(dataSource);
        String SQL = " INSERT INTO Stores "
            + " (id, fullName, address, creation) VALUES "
            + " (?,?,?,?) ";

        jdbc.update(SQL,
                id.value(),
                name.asText().asString(),
                address.asText().asString(),
                ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
        );
        return new PgStore(id, this.dataSource);
    }

    @Override
    public Stores filter(final Name name, final Address address) {
        Map<String, String> filter = new HashMap<>();
        filter.put("name", name.asText().asString());
        filter.put("address", address.asText().asString());
        return new FilteredStores(dataSource, this, filter);
    }

    @Override
    public Store find(final String id) {
        return new PgStore(
            new PgStoreId(id),
            this.dataSource
        );
    }

    @Override
    public <T> T print(Stores.Out<T> out) {
        return out.print(this.iterator());
    }

    @Override
    public Iterator<Store> iterator() {
        JdbcOperations jdbc = new JdbcTemplate(dataSource);
        List<Store> stores = new ArrayList<>();
        for(Map<String, Object> rows : jdbc.queryForList("SELECT id FROM stores")){
            stores.add(new PgStore(
                new PgStoreId(
                    rows.get("id").toString()
                ), this.dataSource
                )
            );
        }
        return stores.iterator();
    }
}
