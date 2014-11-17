package com.racegofer.api.web;

import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

import com.racegofer.api.domain.Greeting;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    int resultInt;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
                             @RequestParam(value="age", required=false, defaultValue="0") String ageString) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_name = auth.getName(); //get logged in username
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, user_name),
                            Integer.parseInt(ageString));
    }

    @RequestMapping("/AddMyCoordinates")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
                           @RequestParam(value="latitude", required=false, defaultValue="0") String latitude,
                           @RequestParam(value="longitude", required=false, defaultValue="0") String longitude) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://productiondb.covqdeip5d2j.us-west-2.rds.amazonaws.com/RaceGofer?user=admin&password=howdyteam1");
            statement = connect.createStatement();
            resultInt = statement.executeUpdate("INSERT INTO DemoTable (Name, Latitude, Longitude) VALUES (\"" + name + "\"," + latitude + "," + longitude + ");");
        } catch (SQLException e) {
            System.out.println("ERROR 1");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR 2");
            e.printStackTrace();
        }

        return "Your coordinates have been saved.";
    }
}
