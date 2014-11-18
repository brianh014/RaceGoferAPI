package com.racegofer.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Miles on 11/9/2014.
 */

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Long getUserID(){
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean enabled;

    public User(){
        //needed for JPA
    }
}
