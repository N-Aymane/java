package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/libraryy?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver JDBC
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Le driver JDBC MySQL est introuvable !");
            throw new SQLException("Driver JDBC introuvable", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
