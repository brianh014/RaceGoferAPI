package com.racegofer.api.web;

import com.racegofer.api.domain.LiveRacerCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * Created by Michael on 11/21/2014.
 */

@RestController
public class API_GetRacerCoordinates {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @RequestMapping("/GetRacerCoordinates")
    public ArrayList<LiveRacerCoordinate> GetRacerCoordinates(
            @RequestParam(value="raceId", required=true) String raceId)
    {
        //Query query = new Query();
        /*String queryString = "Select t.CoordinateNumber, "
                                + "t.UserName, "
                                + "t.Latitude, "
                                + "t.Longitude "
                                + "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` AS t "
                                + "Inner Join ( "
                                +     "SELECT max(CoordinateNumber) as CoordinateNumber "
                                +     ", UserName "
                                +     "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` "
                                +     "GROUP BY UserName "
                                +     ") AS q "
                                + "ON t.CoordinateNumber = q.CoordinateNumber "
                                + "AND t.UserName = q.UserName";*/


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username

        String queryString = "SELECT userId, type FROM RaceGofer.UserRunsRace "
        + "WHERE raceId = '109317cc-965a-4c4a-a45e-bc55d5e451a2' "
        + "AND userID = '" + userName + "';";

        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(queryString);
        String type = "";
        try{
            resultSet.next();
            type = resultSet.getString("type");
        }catch(Exception e){
        }

        if(type == "Participant")
            queryString = "Select l.CoordinateNumber, "
            + "l.UserName, "
            + "k.firstName, "
            + "k.lastName, "
            + "l.Latitude, "
            + "l.Longitude "
            + "FROM users as k "
            + "Inner join "
            + "( "
            + "Select w.CoordinateNumber, "
            +    "w.UserName, "
            +    "w.Latitude, "
            +    "w.Longitude "
            + "FROM RaceGofer.`UserRunsRace` as v "
            + "Inner Join ( "
            +    "Select t.CoordinateNumber, "
            +    "t.UserName, "
            +    "t.Latitude, "
            +    "t.Longitude "
            + "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` AS t "
            + "Inner Join ( "
            +    "SELECT max(CoordinateNumber) as CoordinateNumber "
            +    ", UserName "
            + "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` "
            + "GROUP BY UserName "
            + ") AS q "
            + "ON t.CoordinateNumber = q.CoordinateNumber "
            + "AND t.UserName = q.UserName "
            + ") AS w "
            + "ON v.userId = w.UserName "
            + "AND v.raceId = '" + raceId + "' "
            + "AND v.hidden = 'false' "
            + ") as l "
            + "ON k.userName = l.userName";
        else
            queryString = "Select l.CoordinateNumber, "
            + "l.UserName, "
            + "k.firstName, "
            + "k.lastName, "
            + "l.Latitude, "
            + "l.Longitude "
            + "FROM users as k "
            + "Inner join "
            + "( "
            + "Select w.CoordinateNumber, "
            +    "w.UserName, "
            +    "w.Latitude, "
            +    "w.Longitude "
            + "FROM RaceGofer.`UserRunsRace` as v "
            + "Inner Join ( "
            +    "Select t.CoordinateNumber, "
            +    "t.UserName, "
            +    "t.Latitude, "
            +    "t.Longitude "
            + "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` AS t "
            + "Inner Join ( "
            +    "SELECT max(CoordinateNumber) as CoordinateNumber "
            +    ", UserName "
            + "FROM RaceGofer.`LiveCoordinatesFor" + raceId + "` "
            + "GROUP BY UserName "
            + ") AS q "
            + "ON t.CoordinateNumber = q.CoordinateNumber "
            + "AND t.UserName = q.UserName "
            + ") AS w "
            + "ON v.userId = w.UserName "
            + "AND v.raceId = '" + raceId + "'  "
            + ") as l "
            + "ON k.userName = l.userName";


        System.out.println(queryString);
        //int result = query.ExecuteUpdate(queryString);
        resultSet = jdbcTemplate.queryForRowSet(queryString);
        ArrayList<LiveRacerCoordinate> liveRacerCoordinates = new ArrayList<LiveRacerCoordinate>();
        while(resultSet.next())
        {
            liveRacerCoordinates.add(new LiveRacerCoordinate(
                    resultSet.getString("UserName"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getDouble("Latitude"),
                    resultSet.getDouble("Longitude")
            ));
        }

        return liveRacerCoordinates;
    }
}
