package com.racegofer.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * Created by Michael on 12/1/2014.
 */

@RestController
public class API_DeleteRace {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @RequestMapping("/DeleteRace")
    public int DeleteRace(
            @RequestParam(value="raceId", required=true) String raceId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username

        String queryString = "DELETE FROM UserRunsRace "
                + "WHERE raceId = '" + raceId + "';";
        int result = jdbcTemplate.update(queryString);
        System.out.println(result);

        queryString = "DELETE FROM Race "
                + "WHERE raceId = '" + raceId + "';";
        result = jdbcTemplate.update(queryString);
        System.out.println(result);

        return result; //only returns positive, needs to be edited
    }
}
