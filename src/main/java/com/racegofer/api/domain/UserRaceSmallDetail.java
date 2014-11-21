package com.racegofer.api.domain;

/**
 * Created by Michael on 11/16/2014.
 */
public class UserRaceSmallDetail {

    private final String _raceName;
    private final String _raceId;
    private final String _type;

    public UserRaceSmallDetail(String raceName, String raceId, String type)
    {
        this._raceName = raceName;
        this._raceId = raceId;
        this._type = type;
    }

    public String getRaceName(){return _raceName;}
    public String getRaceId(){return _raceId;}
    public String getType(){return _type;}
}
