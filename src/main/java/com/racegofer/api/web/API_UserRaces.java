package com.racegofer.api.web;

import com.racegofer.api.domain.RaceInformation;
import com.racegofer.api.domain.RaceNameAndId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Michael on 11/8/2014.
 */

@RestController
public class API_UserRaces { //he just wants the race and race id

    @RequestMapping("/UserRaces")
    public ArrayList<RaceNameAndId> userRaces(
            //@RequestParam(value="userName", required=false, defaultValue="") String userName
    ) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName(); //get logged in username
            Query query = new Query();
            String queryString = "SELECT raceId, title FROM RaceGofer.Race" +
                    " WHERE RaceID IN" +
                    " (SELECT RaceID FROM RaceGofer.`UserRunsRace`" +
                    " WHERE UserID = '" + userName + "');";
            ResultSet resultSet = query.ExecuteQuery(queryString);
            ArrayList<RaceNameAndId> raceNameAndIds = new ArrayList<RaceNameAndId>();
            while (resultSet.next()) {
                raceNameAndIds.add(new RaceNameAndId(
                        resultSet.getString("title"),
                        resultSet.getString("raceId")
                ));
            }
            return raceNameAndIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
