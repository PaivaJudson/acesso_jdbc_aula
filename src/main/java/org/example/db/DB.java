package org.example.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.usuario");
                String password = properties.getProperty("db.senha");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){

        try {
            Properties properties = new Properties();
            String fileName = "application.properties";
            var stream = DB.class.getClassLoader().getResourceAsStream(fileName);

            if(stream == null){
                throw new DbException("Ficheiro " + fileName + " nao encontrado ");
            }

            properties.load(stream);
            return properties;

        }catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }


}
