package com.racegofer.api.web;

import com.racegofer.api.domain.CheckPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 * Created by Michael on 11/5/2014.
 */

@RestController
public class API_CreateRace {

    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet;

    @RequestMapping("/CreateRace")
    public String CreateRace(
            @RequestParam(value="raceName", required=true) String raceName,
            @RequestParam(value="raceType", required=true) String raceType,
            @RequestParam(value="raceLocation", required=true) String raceLocation,
            @RequestParam(value="racePassword", required=false, defaultValue = "") String racePassword,
            @RequestParam(value="managerPassword", required=true) String managerPassword,
            @RequestParam(value="checkPoints", required=true) String checkPointsString
            )
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://productiondb.covqdeip5d2j.us-west-2.rds.amazonaws.com/RaceGofer?user=admin&password=howdyteam1");
            statement = connect.createStatement();
            UUID guid = UUID.randomUUID();

            String query = "INSERT INTO `RaceGofer`.`Race` VALUES(" +
                    "'" + guid.toString() + "', " +
                    "'" + raceName + "'," +
                    "'" + raceType + "'," +
                    "'" + raceLocation + "'," +
                    "'" + racePassword + "'," +
                    "'" + managerPassword + "');";
            System.out.println(query);
            statement.executeUpdate(query);

            query = "CREATE TABLE `RaceGofer`.`CoordinatesFor" + guid.toString() + "` (" +
            "`CoordinateNumber` INT NOT NULL," +
            "`Latitude` FLOAT(10,6) NULL," +
            "`Longitutde` FLOAT(10,6) NULL," +
            "PRIMARY KEY (`CoordinateNumber`));";
            System.out.println(query);
            statement.executeUpdate(query);

            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(checkPointsString);
            ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();
            for(Integer i = 0; i < array.size(); i++)
            {
                JSONObject arrayElement = (JSONObject)array.get(i);
                query = "INSERT INTO `RaceGofer`.`CoordinatesFor" + guid.toString() +"`  VALUES (" +
                            "'" + i.toString() + "', " +
                            "'" + ((Double)arrayElement.get("latitude")).toString() + "', " +
                            "'" + ((Double)arrayElement.get("longitude")).toString() + "');";
                System.out.println(query);
                statement.executeUpdate(query);

            }

            query = "CREATE TABLE `RaceGofer`.`LiveCoordinatesFor" + guid.toString() + "` (" +
                    "`CoordinateNumber` INT NOT NULL AUTO_INCREMENT," +
                    "`UserName` VARCHAR(45) NULL," +
                    "`Latitude` FLOAT(10,6) NULL," +
                    "`Longitude` FLOAT(10,6) NULL," +
                    "PRIMARY KEY (`CoordinateNumber`));";
            System.out.println(query);
            statement.executeUpdate(query);

            return "Your race was created";


        } catch (SQLException e) {
            System.out.println("ERROR 1");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR 2");
            e.printStackTrace();
        } //catch (ParseException e) {
        catch (ParseException e) {
            e.printStackTrace();
        }
        // e.printStackTrace();
        //}

        return "Your race was not created";
    }
}
