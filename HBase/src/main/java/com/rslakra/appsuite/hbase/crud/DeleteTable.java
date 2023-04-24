package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:51 AM
 */
public class DeleteTable {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(DeleteTable.class);

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            Admin admin = connection.getAdmin();
            TableName tableName = TableName.valueOf("users");
            if (admin.tableExists(tableName)) {
                LOGGER.debug("Deleting table:" + tableName.getNameAsString());
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            } else {
                LOGGER.debug("Table [" + tableName.getNameAsString() + "] does not exist.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, null, null);
        }
    }
}
