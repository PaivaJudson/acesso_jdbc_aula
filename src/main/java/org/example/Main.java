package org.example;

import org.example.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    static void main() {

        Connection connection = DB.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;





        DB.closeConnection();
    }
}
