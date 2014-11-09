package hello;

/**
 * Created by Michael on 11/8/2014.
 */
public class RaceInformation {
    String raceName;
    String raceType;
    String location;

    RaceInformation(String raceName, String raceType, String location)
    {
        this.raceName = raceName;
        this.raceType = raceType;
        this.location = location;
    }

    String GetRaceName(){return raceName;}
    String GetRaceType(){return raceType;}
    String GetLocation(){return location;}

}
