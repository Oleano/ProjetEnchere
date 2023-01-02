package fr.eni.tfProjetEnchere.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ENCHERES;encrypt=false";
              private static final String USERNAME = "userjdbc";
      private static final String PASSWORD = "thomas";

      public static Connection getConnection() {
        Connection connection = null;
        try {
          Class.forName(DRIVER);
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();	
        }
        return connection;
      }

     }