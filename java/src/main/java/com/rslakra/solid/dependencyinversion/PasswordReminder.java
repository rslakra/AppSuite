package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 8:11 PM
 * Version: 1.0.0
 */
public class PasswordReminder {


    private Connection connection;
//    private MySqlConnection connection;

//    public PasswordReminder(MySqlConnection connection) {
//        this.connection = connection;
//    }

    public PasswordReminder(Connection connection) {
        this.connection = connection;
    }

}
