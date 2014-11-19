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
 * Created by Michael on 11/8/2014.
 */

@RestController
public class API_JoinRace {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @RequestMapping("/JoinRace")
    public String JoinRace(
            //@RequestParam(value="userName", required=true) String userName,
            @RequestParam(value="raceId", required=true) String raceId,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="userType", required=true) String userType)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username

        //Query query = new Query();
        String queryString = "INSERT INTO `RaceGofer`.`UserRunsRace` (`RaceID`, `UserID`, `Type`) VALUES ('"
                + raceId + "', '" + userName + "', '" + userType + "');";

        //int result = query.ExecuteUpdate(queryString);
        int result = jdbcTemplate.update(queryString);
        return "Race was Joined"; //only returns positive, needs to be edited
    }
}