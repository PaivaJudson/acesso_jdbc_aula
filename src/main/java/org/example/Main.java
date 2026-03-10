package org.example;

import org.example.db.DB;

import java.sql.Connection;



public class Main {
    static void main() {

        Connection connection = DB.getConnection();
        DB.closeConnection();
    }
}
