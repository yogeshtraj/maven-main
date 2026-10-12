package com.example.maven_java_project;

import java.io.InputStream;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        try {

            InputStream input = App.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            Properties properties = new Properties();
            properties.load(input);

            System.out.println("Application Name: "
                    + properties.getProperty("app.name"));

            System.out.println("Version: "
                    + properties.getProperty("app.version"));

            System.out.println("Author: "
                    + properties.getProperty("app.author"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// Jenkins webhook trigger test
