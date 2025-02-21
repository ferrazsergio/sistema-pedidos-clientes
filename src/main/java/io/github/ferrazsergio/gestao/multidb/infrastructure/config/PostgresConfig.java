package io.github.ferrazsergio.gestao.multidb.infrastructure.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

@EnableJpaRepositories(
        basePackages = {
                "io.github.ferrazsergio.gestao.multidb.pedidos",
                "io.github.ferrazsergio.gestao.multidb.clientes"
        },
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Bean
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/multidb?user=postgres&password=postgres")
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("io.github.ferrazsergio.gestao.multidb.pedidos", "io.github.ferrazsergio.gestao.multidb.clientes");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }
}
