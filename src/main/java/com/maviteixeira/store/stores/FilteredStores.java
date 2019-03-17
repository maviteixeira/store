package com.maviteixeira.store.stores;

import com.jcabi.jdbc.JdbcSession;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

class FilteredStores implements Stores {

    private final DataSource dataSource;
    private final Stores origin;
    private final Map<String, String> filters;

    public FilteredStores(DataSource dataSource, Stores origin, Map<String, String> filters) {
        this.dataSource = dataSource;
        this.origin = origin;
        this.filters = filters;
    }

    @Override
    public Store create(Name name, Address address) {
        return origin.create(name, address);
    }

    @Override
    public Stores filter(Name name, Address address) {
        filters.put("name", name.asString());
        filters.put("address", address.asString());
        return new FilteredStores(dataSource, this, filters);
    }

    @Override
    public Store find(String id) {
        return this.origin.find(id);
    }

    @Override
    public <T> T print(Out<T> out) {
        return out.print(this.iterator());
    }

    @Override
    public Iterator<Store> iterator() {
        StringBuffer sql = new StringBuffer(" SELECT id FROM stores WHERE 1=1 ");
        JdbcSession jdbc = new JdbcSession(dataSource);
        if (filters.get("name") != null && !filters.get("name").equals("")) {
            sql.append(" AND fullName like ? ");
            jdbc.set("%" + filters.get("name") + "%");
        }
        if (filters.get("address") != null && !filters.get("address").equals("")) {
            sql.append(" AND address like '?' ");
            jdbc.set("%" + filters.get("name") + "%");
        }
        jdbc.sql(sql.toString());
        try {
            return jdbc.select((resultSet, statement) -> {
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
            //Log
            return Collections.emptyIterator();
        }
    }
}
