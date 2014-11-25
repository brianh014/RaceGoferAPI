package com.racegofer.api.domain;

/**
 * Created by Michael on 11/21/2014.
 */
public class LiveRacerCoordinate {
    private final String userName;
    private final double longitude;
    private final double latitude;

    public LiveRacerCoordinate(String userName, double latitude, double longitude)
    {
        this.userName = userName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserName(){return userName;}
    public double getLongitude(){return longitude;}
    public double getLatitude(){return latitude;}
}
