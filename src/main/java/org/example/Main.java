package org.example;

import org.example.db.DB;

import java.sql.*;


public class Main {
    static void main() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DB.getConnection();

            String str =  "INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                        + "VALUES" + "(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(str);

            preparedStatement.setString(1, "Judson Paiva");
            preparedStatement.setString(2, "judson.paiva@isptec.co.ao");
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDouble(4, 9000000);
            preparedStatement.setInt(5, 4);

            int linhas = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }









    static void insercao() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
