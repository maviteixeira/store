package com.maviteixeira.store.stores;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;

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
        try {
            new JdbcSession(dataSource)
                .sql(SQL)
                .set(name.asString())
                .set(address.asString())
                .set(id.value())
                .update(Outcome.VOID);
        } catch (SQLException ex) {
            //Log
        }
    }

    @Override
    public <T> T print(Out<T> out) {
        try {
            return new JdbcSession(dataSource)
                .sql("SELECT fullName, address FROM stores WHERE id = ?")
                .set(this.id.value())
                .select((resultSet, statement) -> {
                        resultSet.next();
                        return out.print(
                            this.id,
                            new FullName(resultSet.getString(1)),
                            new CompactAddress(resultSet.getString(2))
                        );
                    }
                );
        } catch (SQLException ex) {
            //Log
            return out.print(
                new EmptyStoreId(),
                new EmptyName(),
                new EmptyAddress()
            );
        }
    }
}
