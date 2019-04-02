package com.maviteixeira.store.stores;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

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
        filters.put("name", name.asText().asString());
        filters.put("address", address.asText().asString());
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
        StringBuffer SQL = new StringBuffer(" SELECT id FROM stores WHERE 1=1 ");
        JdbcOperations jdbc = new JdbcTemplate(dataSource);
        if (filters.get("name") != null && !filters.get("name").equals("")) {
            SQL.append(" AND fullName like '%").append(filters.get("name")).append("%' ");
        }
        if (filters.get("address") != null && !filters.get("address").equals("")) {
            SQL.append(" AND address like '%").append(filters.get("address")).append("%' ");
        }

        List<Store> stores = new ArrayList<>();
        for(Map<String, Object> rows : jdbc.queryForList(SQL.toString())){
            stores.add(
                new PgStore(
                    new PgStoreId(
                        rows.get("id").toString()
                    ), this.dataSource
                )
            );
        }
        return stores.iterator();
    }
}
