package com.maviteixeira.store.stores;

import com.jcabi.jdbc.JdbcSession;
import com.maviteixeira.store.shared.exceptions.AppException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class PgPlaza implements Stores {

    private final DataSource dataSource;

    public PgPlaza(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Store create(final Name name, final Address address) {
        StoreId id = new PgStoreId(UUID.randomUUID());

        String SQL = " INSERT INTO Stores "
            + " (id, fullName, address, creation) VALUES "
            + " (?,?,?,?) ";
        try {
            new JdbcSession(dataSource)
                .sql(SQL)
                .set(id.value())
                .set(name.asText().asString())
                .set(address.asText().asString())
                .set(ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT))
                .execute();
        } catch (SQLException ex) {
            throw new AppException(ex);
        }
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
        try {
            return new JdbcSession(dataSource)
                .sql("SELECT id FROM stores")
                .select((resultSet, statement) -> {
                        final Collection<Store> stores = new ArrayList<>();
                        while (resultSet.next()) {
                            stores.add(
                                new PgStore(
                                    new PgStoreId(
                                        resultSet.getString(1)
                                    ),
                                    this.dataSource
                                )
                            );
                        }
                        return stores.iterator();
                    }
                );
        } catch (SQLException ex) {
            throw new AppException(ex);
        }
    }
}
