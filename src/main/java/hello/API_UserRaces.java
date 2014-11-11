package hello;

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
    public RaceInformation userRaces(@RequestParam(value="userName", required=false, defaultValue="") String userName) {
        try {
            Query query = new Query();
            String queryString = "SELECT * FROM RaceGofer.Race" +
                    " WHERE RaceID IN" +
                    " (SELECT RaceID FROM RaceGofer.`UserRunsRace`" +
                    " WHERE UserID = '" + userName + "');";
            ResultSet resultSet = query.ExecuteQuery(queryString);
            ArrayList<RaceInformation> raceInformations = new ArrayList<RaceInformation>();
            while (resultSet.next()) {
                raceInformations.add(new RaceInformation(
                        resultSet.getString("Title"),
                        resultSet.getString("Type"),
                        resultSet.getString("Location")
                ));
            }
            RaceInformation raceInformation = new RaceInformation("me","you","us");
            return raceInformation;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
