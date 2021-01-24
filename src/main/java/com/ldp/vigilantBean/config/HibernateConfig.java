package com.ldp.vigilantBean.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernateConfig.properties")
public class HibernateConfig {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.format_sql}")
    private boolean formatSql;

    @Value("${hibernate.use_sql_comments}")
    private boolean useSqlComments;

    @Value("${hibernate.show_sql}")
    private boolean showSql;

    @Value("${hibernate.max_fetch_depth}")
    private Integer maxFetchDepth;

    @Value("${hibernate.jdbc.batch_size}")
    private Integer batchSize;

    @Value("${hibernate.jdbc.fetch_size}")
    private Integer fetchSize;

    @Value("${c3p0.timeout}")
    private Integer c3p0Timeout;

    @Autowired
    private DataSource dataSource;

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.format_sql", formatSql);
        hibernateProperties.put("hibernate.use_sql_comments", useSqlComments);
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("hibernate.max_fetch_depth", maxFetchDepth);
        hibernateProperties.put("hibernate.jdbc.batch_size", batchSize);
        hibernateProperties.put("hibernate.jdbc.fetch_size", fetchSize);
        hibernateProperties.put("c3p0.timeout", c3p0Timeout);

        return hibernateProperties;
    }

    @Bean
    @DependsOn("dataSource")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean  sessionFactoryBean =
                new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.ldp.vigilantBean.domain");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();

        return sessionFactoryBean.getObject();
    }

}
