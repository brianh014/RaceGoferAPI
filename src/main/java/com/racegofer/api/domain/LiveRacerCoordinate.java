package com.racegofer.api.domain;

/**
 * Created by Michael on 11/21/2014.
 */
public class LiveRacerCoordinate {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final double longitude;
    private final double latitude;

    public LiveRacerCoordinate(String userName, String firstName, String lastName, double latitude, double longitude)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserName(){return userName;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public double getLongitude(){return longitude;}
    public double getLatitude(){return latitude;}
}
