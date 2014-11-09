package hello;

/**
 * Created by Michael on 11/5/2014.
 */
public class CheckPoint {
    double _latitude;
    double _longitude;

    CheckPoint(double latitude, double longitude)
    {
        _latitude = latitude;
        _longitude = longitude;
    }

    double GetLatitude(){return _latitude;}
    double GetLongitude(){return _longitude;}
    void ChangeLatitude(double latitude){_latitude = latitude;}
    void ChangeLongitude(double longitude){_longitude= longitude;}
}
