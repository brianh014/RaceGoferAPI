package hello;

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
            if (resultSet.next()) {
                return new RaceInformation(
                        resultSet.getString("Title"),
                        resultSet.getString("Type"),
                        resultSet.getString("Location")
                );
            }
            else
            {
                return new RaceInformation("","","");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
