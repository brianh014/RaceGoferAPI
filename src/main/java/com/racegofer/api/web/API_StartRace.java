package com.racegofer.api.web;

import com.racegofer.api.domain.NameAndPhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Created by Michael on 12/5/2014.
 */

@RestController
public class API_StartRace {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping("/StartRace")
    public int StartRace(
            @RequestParam(value="raceId", required=true) String raceId
    )
    {
        String queryString = "UPDATE `RaceGofer`.`Race` SET `raceStarted`='true' WHERE `raceID`='" + raceId + "'";

        System.out.println(queryString);
        int resultSet = jdbcTemplate.update(queryString);

        return resultSet;
    }
}
