package com.ldp.vigilantBean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.ldp.vigilantBean")
public class RootApplicationContextConfig {

    @Bean(name = "dataSource")
    public DataSource embeddedDataSource() {

        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();

        EmbeddedDatabase db = dbBuilder.setType(EmbeddedDatabaseType.HSQL)
                                        .addScript("classpath:db/hsql/create-table.sql")
                                        .addScript("classpath:db/hsql/insert-test-data.sql")
                                        .build();

        return db;
    }

}
