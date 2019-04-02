package com.maviteixeira.store;

import com.maviteixeira.store.config.SpringMemoryConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {SpringMemoryConfig.class})
@SpringBootTest
public abstract class IntegrationTests {

    @Autowired
    protected DataSource dataSource;

    //Need to implement a truncate for each table to improve performance
    @Before
    public void setUp() throws IOException {
        String sqlSchema = new String(
            Files.readAllBytes(
                new ClassPathResource("schema.sql").getFile().toPath()
            )
        );
        new JdbcTemplate(dataSource).execute(sqlSchema);
    }

    @After
    public void cleanUp() {
        new JdbcTemplate(dataSource).execute("DROP ALL OBJECTS");
    }

}
