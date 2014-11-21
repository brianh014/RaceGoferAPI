package com.racegofer.api.web;

import com.racegofer.api.domain.RaceNameAndId;
import com.racegofer.api.domain.UserRaceSmallDetail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Michael on 11/16/2014.
 */

@RestController
public class API_GetRaces {

    @RequestMapping("/GetRaces")
    public ArrayList<RaceNameAndId> GetRaces(
            @RequestParam(value="search", required=false, defaultValue="") String search
    )
    {
        Query query = new Query();
        String queryString = "";
        String searchString = "%" + search + "%";
        queryString = "SELECT raceId, title FROM RaceGofer.Race WHERE title LIKE '" + searchString + "';";

        ResultSet resultSet = query.ExecuteQuery(queryString);
        ArrayList<RaceNameAndId> raceNameAndIds = new ArrayList<RaceNameAndId>();
        try {
            while(resultSet.next())
            {
                raceNameAndIds.add(new RaceNameAndId(
                        resultSet.getString("title"),
                        resultSet.getString("raceId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raceNameAndIds;
    }

}
