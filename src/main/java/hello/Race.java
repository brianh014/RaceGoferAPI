package hello;

import java.util.ArrayList;

/**
 * Created by Michael on 11/5/2014.
 */
public class Race {
    String _raceName;
    String _raceType;
    String _location;
    String _password;
    String _managerPassword;
    ArrayList<CheckPoint> _checkPoints;

    Race(String raceName,
         String raceType,
         String location,
         String password,
         String managerPassword,
         ArrayList<CheckPoint> checkPoints)
    {
        _raceName = raceName;
        _raceType = raceType;
        _location = location;
        _password = password;
        _managerPassword = managerPassword;
        _checkPoints = new ArrayList<CheckPoint>(checkPoints);
    }

    Race(int raceId)
    {

    }
}
