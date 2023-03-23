package storeData;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fetchData.University;

public class DatabaseHandler {

    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=JDBC Universities Project;"
            + "encrypt=true;" + "trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "root";

    private static Connection conn;

    public DatabaseHandler() {
        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUniversities(List<University> universityList) {
        // insert universities data into the database
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Universities VALUES (?, ?, ?, ?, ?, ?)");
            for (University university : universityList) {
                pstmt.setString(1, university.getName());
                pstmt.setString(2, university.getCountry());
                pstmt.setString(3, university.getStateProvince());
                pstmt.setString(4, university.getAlphaTwoCode());
                pstmt.setString(5, String.join(",", university.getDomains()));
                pstmt.setString(6, String.join(",", university.getWebPages()));
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printUniversitiesByCountry(String countryName) {
        try {
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM universities WHERE country = '" + countryName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("*******************************************************************");
            System.out.println("                Universities in " + countryName + ":");
            System.out.println("*******************************************************************");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String webPages = resultSet.getString("webPages");
                String domains = resultSet.getString("domains");
                System.out.println("----------------------------------------------------------");

                System.out.println(name + "\t" + " :: " + domains + "\t" + " :: " + webPages);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // other methods remain unchanged
}
