package com.racegofer.api.web;

import com.racegofer.api.domain.LiveRacerCoordinate;
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
 * Created by Michael on 11/29/2014.
 */

@RestController
public class API_GetNamesAndNumbersForRace {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping("/GetNamesAndNumbersForRace")
    public ArrayList<NameAndPhoneNumber> GetNamesAndNumbersForRace(
            @RequestParam(value="raceId", required=true) String raceId,
            @RequestParam(value="type", required=true) String type
            )
    {
        String queryString = "SELECT t.userId, u.firstName, u.lastName, u.phoneNumber FROM RaceGofer.UserRunsRace AS t "
                             + "INNER JOIN ( "
                             +   "SELECT * FROM RaceGofer.users "
                             + ") AS u "
                             + "ON t.userID = u.userName "
                             + "WHERE t.raceId = '" + raceId + "' "
                             + "AND t.type = '" + type + "'";

        System.out.println(queryString);
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(queryString);
        ArrayList<NameAndPhoneNumber> namesAndPhoneNumbers = new ArrayList<NameAndPhoneNumber>();
        while(resultSet.next())
        {
            namesAndPhoneNumbers.add(new NameAndPhoneNumber(
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phoneNumber")
            ));
        }

        return namesAndPhoneNumbers;
    }
}
