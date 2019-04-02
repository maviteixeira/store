package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.exceptions.UnprintableException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PgStore implements Store {

    private final StoreId id;
    private final DataSource dataSource;

    public PgStore(final StoreId id, final DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
    }

    @Override
    public void moveAndRename(Address address, Name name) {
        String SQL = " UPDATE Stores "
            + " SET fullName = ?, address = ? "
            + " WHERE id = ? ";

        JdbcOperations jdbc = new JdbcTemplate(dataSource);
        jdbc.update(SQL,
            name.asText().asString(),
            address.asText().asString(),
            id.value()
        );
    }

    @Override
    public <T> T print(Out<T> out) {
        String SQL = "SELECT fullName, address FROM stores WHERE id = ?";
        JdbcOperations jdbc = new JdbcTemplate(dataSource);
        return jdbc.queryForObject(SQL, new Object[] { id.value() }, (result, i) -> {
            try {
                return out.print(
                    id,
                    new SimpleName(result.getString("fullName")),
                    new CompactAddress(result.getString("address"))
                );
            } catch (SQLException e) {
                throw new UnprintableException("Cannot print");
            }
        });
    }
}
