package hello;

import java.sql.*;

/**
 * Created by Michael on 11/8/2014.
 */
public class Query {

    Connection _connect;
    Statement _statement;
    ResultSet _resultSet;
    final String _serverLocation = "jdbc:mysql://productiondb.covqdeip5d2j.us-west-2.rds.amazonaws.com/RaceGofer?user=admin&password=howdyteam1";

    Query()
    {
        _connect = null;
        _statement = null;
        _resultSet = null;
    }

    ResultSet ExecuteQuery(String query)
    {
        try {
            _connect = DriverManager
                    .getConnection(_serverLocation);
            _statement = _connect.createStatement();
            _resultSet = _statement.executeQuery(query);
            return _resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    int ExecuteUpdate(String query)
    {
        try {
            _connect = DriverManager
                    .getConnection(_serverLocation);
            _statement = _connect.createStatement();
            int result = _statement.executeUpdate(query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
