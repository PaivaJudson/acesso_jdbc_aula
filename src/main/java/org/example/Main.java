package org.example;

import org.example.db.DB;
import org.example.db.DbException;
import org.example.db.DbIntegrityException;

import java.sql.*;


public class Main {

    static void main() {

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DB.getConnection();
            String query = "DELETE FROM Department "
                    + "WHERE Id = ?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, 2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }


    }


    static void atualizar() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DB.getConnection();
            String query = "UPDATE Seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE DepartmentID = ?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setDouble(1, 200.0);
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }

    static void inserir() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();

            String str = "INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId)"
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
        } finally {
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
