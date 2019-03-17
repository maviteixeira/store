package com.maviteixeira.store.stores;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;
import com.maviteixeira.store.shared.exceptions.AppException;
import com.maviteixeira.store.shared.exceptions.UnprintableException;

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
                .set(name.asText().asString())
                .set(address.asText().asString())
                .set(id.value())
                .update(Outcome.VOID);
        } catch (SQLException ex) {
            throw new AppException(ex);
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
                            new SimpleName(resultSet.getString(1)),
                            new CompactAddress(resultSet.getString(2))
                        );
                    }
                );
        } catch (SQLException ex) {
            throw new UnprintableException(ex);
        }
    }
}
