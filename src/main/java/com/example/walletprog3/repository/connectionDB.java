package com.example.walletprog3.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class connectionDB {
    static Connection connection;

    public connectionDB() {
        try {
            this.connection = DriverManager.getConnection(
                    System.getenv("PG_URL"),
                    System.getenv("PG_USER"),
                    System.getenv("PG_PASSWORD")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return null;
    };

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
