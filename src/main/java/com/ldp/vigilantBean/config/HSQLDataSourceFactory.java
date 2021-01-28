package com.ldp.vigilantBean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Driver;

@Component
@PropertySource("classpath:embeddedConnectionConfig.properties")
class HSQLDataSourceFactory implements DataSourceFactory {

    @Value("${embeddedConnection.url}")
    private String embeddedConnectionURL;

    @Override
    public ConnectionProperties getConnectionProperties() {
        return new CustomConnectionProperties();
    }

    @Override
    public DataSource getDataSource() {

        org.hsqldb.jdbc.JDBCDataSource ds =
                new org.hsqldb.jdbc.JDBCDataSource();

        ds.setURL(embeddedConnectionURL);
        return ds;
    }

    static class CustomConnectionProperties implements ConnectionProperties {
        private Class<? extends Driver> driverClass;
        private String url;
        private String username;
        private String password;

        @Override
        public void setDriverClass(Class<? extends Driver> aClass) {
            this.driverClass = aClass;
        }

        @Override
        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
