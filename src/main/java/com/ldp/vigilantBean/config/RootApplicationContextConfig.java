package com.ldp.vigilantBean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.ldp.vigilantBean")
public class RootApplicationContextConfig {

    private DataSourceFactory dataSourceFactory;

    public RootApplicationContextConfig(
            @Autowired
            DataSourceFactory dataSourceFactory) {

        this.dataSourceFactory = dataSourceFactory;
    }

    @Bean(name = "dataSource")
    public DataSource embeddedDataSource() {

        EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();

        EmbeddedDatabase db = dbBuilder.setType(EmbeddedDatabaseType.HSQL)
                                       .setDataSourceFactory(dataSourceFactory)
                                       .addScript("classpath:db/hsql/create-table.sql")
                                       .addScript("classpath:db/hsql/insert-test-data.sql")
                                       .build();

        return db;
    }


}
