package com.racegofer.api.domain;

/**
 * Created by Michael on 11/16/2014.
 */
public class RaceNameAndId {

    private final String _raceName;
    private final String _raceId;

    public RaceNameAndId(String raceName, String raceId)
    {
        this._raceName = raceName;
        this._raceId = raceId;
    }

    public String getRaceName(){return _raceName;}
    public String getRaceId(){return _raceId;}
}
