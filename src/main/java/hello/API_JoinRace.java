package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michael on 11/8/2014.
 */

@RestController
public class API_JoinRace {

    @RequestMapping("/JoinRace")
    public String JoinRace(
            @RequestParam(value="userName", required=true) String userName,
            @RequestParam(value="raceId", required=true) String raceId,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="userType", required=true) String userType)
    {
        System.out.println(1);
        Query query = new Query();
        System.out.println(2);
        String queryString = "INSERT INTO `RaceGofer`.`UserRunsRace` (`RaceID`, `UserID`, `Type`) VALUES ('"
                + raceId + "', '" + userName + "', '" + userType + "');";
        System.out.println(3);
        int result = query.ExecuteUpdate(queryString);
        System.out.println(4);
        return "Race was Joined"; //only returns positive, needs to be edited
    }
}