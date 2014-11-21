package com.racegofer.api.web;

import com.racegofer.api.domain.CheckPoint;
import com.racegofer.api.domain.RaceInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Michael on 11/9/2014.
 */

@RestController
public class API_GetRaceInfo { //return more information
    @RequestMapping("/GetRaceInfo")
    public RaceInformation getRaceInfo(@RequestParam(value="raceId", required=false, defaultValue="") String raceId) {
        try {
            Query query = new Query();
            String queryString = "SELECT * FROM `RaceGofer`.`Race`" +
                    "WHERE RaceId = '" + raceId + "';";
            System.out.println(queryString);
            ResultSet resultSet = query.ExecuteQuery(queryString);
            resultSet.next();
            String title = resultSet.getString("Title");
            String type = resultSet.getString("Type");
            String location = resultSet.getString("Location");
            boolean raceHasPassword = resultSet.getString("password") != "";
            queryString = "SELECT * FROM RaceGofer.`CoordinatesFor"+ raceId + "`";
            resultSet = query.ExecuteQuery(queryString);
            ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();
            while(resultSet.next())
            {
                checkPoints.add(new CheckPoint(
                        resultSet.getDouble("Latitude"),
                        resultSet.getDouble("Longitutde")
                ));
            }
            return new RaceInformation(title, type, location, checkPoints, raceHasPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
