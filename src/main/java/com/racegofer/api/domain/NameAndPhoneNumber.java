package com.racegofer.api.domain;

/**
 * Created by Michael on 12/1/2014.
 */
public class NameAndPhoneNumber {
    private final String _firstName;
    private final String _lastName;
    private final String _phoneNumber;

    public NameAndPhoneNumber(String firstName, String lastName, String phoneNumber)
    {
        this._firstName = firstName;
        this._lastName = lastName;
        this._phoneNumber = phoneNumber;
    }

    public String getFirstName(){return _firstName;}
    public String getLastName(){return _lastName;}
    public String getPhoneNumber(){return _phoneNumber;}
}
