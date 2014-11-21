package com.racegofer.api.web;

import com.racegofer.api.domain.LiveRacerCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
        String queryString = "Select t.CoordinateNumber, "
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
                                + "AND t.UserName = q.UserName";

        System.out.println(queryString);
        //int result = query.ExecuteUpdate(queryString);
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(queryString);
        ArrayList<LiveRacerCoordinate> liveRacerCoordinates = new ArrayList<LiveRacerCoordinate>();
        while(resultSet.next())
        {
            liveRacerCoordinates.add(new LiveRacerCoordinate(
                    resultSet.getString("UserName"),
                    resultSet.getDouble("Latitude"),
                    resultSet.getDouble("Longitude")
            ));
        }

        return liveRacerCoordinates;
    }
}
