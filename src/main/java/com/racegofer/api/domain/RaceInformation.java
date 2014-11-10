package com.racegofer.api.domain;

/**
 * Created by Michael on 11/8/2014.
 */
public class RaceInformation {
    private final String raceName;
    private final String raceType;
    private final String location;

    public RaceInformation(String raceName, String raceType, String location)
    {
        this.raceName = raceName;
        this.raceType = raceType;
        this.location = location;
    }

    public String getRaceName(){return raceName;}
    public String getRaceType(){return raceType;}
    public String getLocation(){return location;}

}
