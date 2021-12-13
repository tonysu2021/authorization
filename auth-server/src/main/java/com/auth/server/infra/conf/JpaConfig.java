package com.auth.server.infra.conf;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableJpaRepositories(basePackages = { "com.auth.server.domain.repository" })
@Configuration
public class JpaConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSourceProperties dataSourceProperties) {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource(dataSourceProperties));
		em.setPackagesToScan("com.auth.server.domain.entity");
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}

	@Primary
	@Bean(destroyMethod = "close")
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dataSourceProperties.getUrl());
		config.setUsername(dataSourceProperties.getUsername());
		config.setPassword(dataSourceProperties.getPassword());
		config.setDriverClassName(org.postgresql.Driver.class.getName());
		config.setMaximumPoolSize(5);
		return new HikariDataSource(config);
	}
}
