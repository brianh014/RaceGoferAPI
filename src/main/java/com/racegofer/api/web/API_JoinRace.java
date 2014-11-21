package com.racegofer.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
    public int JoinRace(
            //@RequestParam(value="userName", required=true) String userName,
            @RequestParam(value="raceId", required=true) String raceId,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="userType", required=true) String userType)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username

        String queryString = "SELECT password FROM RaceGofer.Race "
                + "WHERE raceId = '" + raceId +  "'";
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(queryString);
        resultSet.next();
        String actualPassword = resultSet.getString("password");
        System.out.println("Password = " + password);
        System.out.println("Actual Password = " + actualPassword);
        if(!password.equals(actualPassword))
            return 0;

        queryString = "INSERT INTO `RaceGofer`.`UserRunsRace` (`RaceID`, `UserID`, `Type`) VALUES ('"
                + raceId + "', '" + userName + "', '" + userType + "');";

        int result = jdbcTemplate.update(queryString);
        return result; //only returns positive, needs to be edited
    }
}