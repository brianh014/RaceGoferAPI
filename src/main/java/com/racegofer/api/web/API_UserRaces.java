package com.racegofer.api.web;

import com.racegofer.api.domain.UserRaceSmallDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ArrayList<UserRaceSmallDetail> userRaces(
            //@RequestParam(value="userName", required=false, defaultValue="") String userName
    ) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName(); //get logged in username
            Query query = new Query();
            /*String queryString = "SELECT raceId, title FROM RaceGofer.Race" +
                    " WHERE RaceID IN" +
                    " (SELECT RaceID FROM RaceGofer.`UserRunsRace`" +
                    " WHERE UserID = '" + userName + "');";
            */
            String queryString = "SELECT t.raceId, t.title, u.type "
                                + "FROM RaceGofer.Race AS t "
                                + "INNER JOIN "
                                + "(	SELECT RaceID, "
                                + "            type "
                                + "     FROM RaceGofer.`UserRunsRace` "
                                + "     WHERE UserID = '" + userName + "') AS u "
                                + "ON t.raceID = u.raceID";

            ResultSet resultSet = query.ExecuteQuery(queryString);
            ArrayList<UserRaceSmallDetail> raceNameAndIds = new ArrayList<UserRaceSmallDetail>();
            while (resultSet.next()) {
                raceNameAndIds.add(new UserRaceSmallDetail(
                        resultSet.getString("title"),
                        resultSet.getString("raceId"),
                        resultSet.getString("type")
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
