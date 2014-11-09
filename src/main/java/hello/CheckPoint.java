package hello;

/**
 * Created by Michael on 11/5/2014.
 */
public class CheckPoint {
    private double _latitude;
    private double _longitude;

    CheckPoint(double latitude, double longitude)
    {
        _latitude = latitude;
        _longitude = longitude;
    }

    public double GetLatitude(){return _latitude;}
    public double GetLongitude(){return _longitude;}
    public void ChangeLatitude(double latitude){_latitude = latitude;}
    public void ChangeLongitude(double longitude){_longitude= longitude;}
}
