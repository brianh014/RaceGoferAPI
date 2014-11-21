package com.racegofer.api.domain;

/**
 * Created by Michael on 11/5/2014.
 */
public class CheckPoint {
    private double latitude;
    private double longitude;

    public CheckPoint(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double GetLatitude(){return latitude;}
    public double GetLongitude(){return longitude;}
    public void ChangeLatitude(double latitude){
        this.latitude = latitude;}
    public void ChangeLongitude(double longitude){
        this.longitude = longitude;}

    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
}
