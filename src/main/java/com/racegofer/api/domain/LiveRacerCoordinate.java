package com.racegofer.api.domain;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by Michael on 11/21/2014.
 */
public class LiveRacerCoordinate {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final double longitude;
    private final double latitude;
    private final String type;
    private final Date timeStamp;

    public LiveRacerCoordinate(String userName,
                               String firstName,
                               String lastName,
                               double latitude,
                               double longitude,
                               String type,
                               Timestamp timeStamp)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public String getUserName(){return userName;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public double getLongitude(){return longitude;}
    public double getLatitude(){return latitude;}
    public String getType(){return type;}
    public Date getTimeStamp(){return timeStamp;}
}
