package hello;

import java.util.ArrayList;

/**
 * Created by Michael on 11/5/2014.
 */
public class Race {
    private String _raceName;
    private String _raceType;
    private String _location;
    private String _password;
    private String _managerPassword;
    private ArrayList<CheckPoint> _checkPoints;

    public Race(String raceName,
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
