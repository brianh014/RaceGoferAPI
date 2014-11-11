package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael on 11/9/2014.
 */

@RestController
public class API_UpdatePosition {
    @RequestMapping("/UpdatePosition")
    public String getRaceInfo(@RequestParam(value="raceId", required=false, defaultValue="") String raceId,
                              @RequestParam(value="userName", required=false, defaultValue="") String userName,
                              @RequestParam(value="latitude", required=false, defaultValue="") String latitude,
                              @RequestParam(value="longitude", required=false, defaultValue="") String longitude) {
        try {
            Query query = new Query();
            String queryString = "INSERT INTO `RaceGofer`.`LiveCoordinatesFor" + raceId + "`(`UserName`, `Latitude`, `Longitude`) VALUES(" +
                    "'" + userName + "', " +
                    "'" + latitude + "', " +
                    "'" + longitude + "');";
            System.out.println(queryString);
            query.ExecuteUpdate(queryString);
            return "Coordinates Saved";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Coordinates Not Saved";
    }
}
