package com.racegofer.api.domain;

import java.util.ArrayList;

/**
 * Created by Michael on 11/8/2014.
 */
public class RaceInformation {
    private final String _raceName;
    private final String _raceType;
    private final String _location;
    private final ArrayList<CheckPoint> _checkPoints;
    private final boolean _raceHasPassword;

    public RaceInformation(String raceName,
                           String raceType,
                           String location,
                           ArrayList<CheckPoint> checkpoints,
                           boolean raceHasPassword
    )
    {
        this._raceName = raceName;
        this._raceType = raceType;
        this._location = location;
        this._checkPoints = checkpoints;
        this._raceHasPassword = raceHasPassword;
    }

    public String getRaceName(){return _raceName;}
    public String getRaceType(){return _raceType;}
    public String getLocation(){return _location;}
    public ArrayList<CheckPoint> getCheckPoints(){return _checkPoints;}
    public boolean getRaceHasPassword(){return _raceHasPassword;}

}
