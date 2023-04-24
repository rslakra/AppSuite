package com.rslakra.appsuite.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:52 AM
 */
public enum HBaseUtil {
    INSTANCE;

    /**
     * @return
     * @throws IOException
     */
    public Connection createHBaseConnection() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        return ConnectionFactory.createConnection(configuration);
    }

    /**
     * @return
     * @throws IOException
     */
    public static Connection getHBaseConnection() throws IOException {
        return INSTANCE.createHBaseConnection();
    }

    /**
     * @param connection
     * @throws IOException
     */
    public void closeSilently(Connection connection) throws IOException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * @param results
     */
    public void closeSilently(ResultScanner results) {
        if (results != null) {
            results.close();
        }
    }

    /**
     * @param table
     * @throws IOException
     */
    public void closeSilently(Table table) throws IOException {
        if (table != null) {
            table.close();
        }
    }

    /**
     * @param connection
     * @param table
     * @param resultScanner
     */
    public static void closeSilently(Connection connection, Table table, ResultScanner resultScanner) {
        try {
            INSTANCE.closeSilently(connection);
            INSTANCE.closeSilently(table);
            INSTANCE.closeSilently(resultScanner);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeSilently(Connection connection, Table... tables) {
        try {
            INSTANCE.closeSilently(connection);
            if (tables != null) {
                for (Table table : tables) {
                    INSTANCE.closeSilently(table);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
