package jm.task.core.jdbc.util;

import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.System.getProperties;

public class Util {

    public static Connection connect() {
        Properties properties = new Properties();
        try {
            properties.load(Util.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadDriverClass();

        try {
            return DriverManager.getConnection(properties
                            .getProperty("host"),
                    properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException("Проверьте настройки, Вы не смогли подключиться к БД");
        }
    }

    private static void loadDriverClass() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //public static SessionFactory setUp() {
    //    Properties properties = getProperties();
/*
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", properties.getProperty("host"))
                .applySetting("hibernate.connection.username", properties.getProperty("user"))
                .applySetting("hibernate.connection.password", properties.getProperty("password"))
                .applySetting("hibernate.show_sql", properties.getProperty("hibernate.show_sql"))
                .applySetting("hibernate.format_sql", properties.getProperty("hibernate.format_sql"))
                .applySetting("hibernate.use_sql_comments", properties.getProperty("hibernate.use_sql_comments")).build()


 */

    }

