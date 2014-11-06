package com.racegofer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Miles on 11/6/2014.
 */
public class DatabaseConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://productiondb.covqdeip5d2j.us-west-2.rds.amazonaws.com/RaceGofer");
        driverManagerDataSource.setUsername("admin");
        driverManagerDataSource.setPassword("howdyteam1");
        return driverManagerDataSource;
    }

}
