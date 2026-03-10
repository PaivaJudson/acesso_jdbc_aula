package org.example;

import org.example.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    static void main() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");

            while(resultSet.next()){
                System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
