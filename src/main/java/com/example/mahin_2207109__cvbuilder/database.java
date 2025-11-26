package com.example.mahin_2207109__cvbuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {

    private static final String DB_URL = "jdbc:sqlite:cv.db";
    private static database instance;
    private static Connection connection;


    private database() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                createTables();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static synchronized database getInstance() {
        if (instance == null) {
            instance = new database();
        }
        return instance;
    }

    private void createTables() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS cv_info (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone INTEGER NOT NULL,
                    address TEXT NOT NULL,
                    education TEXT NOT NULL,
                    skills TEXT NOT NULL,
                    work_experience TEXT  NULL,
                    projects TEXT NOT NULL,
                    pro_pic TEXT NULL
                );
                """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized void updateCV(int id, String name, String email, Integer phone, String address, String education, String skills, String workExperience,
                                       String projects, String proPicPath) {
        String sql = """
                UPDATE cv_info SET name = ?, email = ?, phone = ?, address = ?, education = ?, skills = ?, work_experience = ?, projects = ?, pro_pic = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, phone);
            stmt.setString(4, address);
            stmt.setString(5, education);
            stmt.setString(6, skills);
            stmt.setString(7, workExperience);
            stmt.setString(8, projects);
            stmt.setString(9, proPicPath);
            stmt.setInt(10, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertCV(String name, String email, Integer phone, String address, String education, String skills, String workExperience,
                             String projects, String proPicPath) {
        String sql = """
                INSERT INTO cv_info(name, email, phone, address, education, skills, work_experience, projects, pro_pic)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, phone);
            stmt.setString(4, address);
            stmt.setString(5, education);
            stmt.setString(6, skills);
            stmt.setString(7, workExperience);
            stmt.setString(8, projects);
            stmt.setString(9, proPicPath);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized void delete(int id) {
        String sql = "DELETE FROM cv_info WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet fetchAll() {
        String sql = "SELECT * FROM cv_info ORDER BY id DESC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            return stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public synchronized void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}