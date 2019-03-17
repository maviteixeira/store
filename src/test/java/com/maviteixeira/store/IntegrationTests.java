package com.maviteixeira.store;

import com.jcabi.jdbc.JdbcSession;
import com.maviteixeira.store.config.SpringMemoryConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {SpringMemoryConfig.class})
@SpringBootTest
public abstract class IntegrationTests {

    @Autowired
    protected DataSource dataSource;

    //Need to implement a truncate for each table to improve performance
    @Before
    public void setUp() throws IOException, SQLException {
        String sqlSchema = new String(
            Files.readAllBytes(
                new ClassPathResource("schema.sql").getFile().toPath()
            )
        );
        new JdbcSession(dataSource).sql(sqlSchema).execute();
    }

    @After
    public void cleanUp() throws SQLException {
        new JdbcSession(dataSource).sql("DROP ALL OBJECTS").execute();
    }

}
