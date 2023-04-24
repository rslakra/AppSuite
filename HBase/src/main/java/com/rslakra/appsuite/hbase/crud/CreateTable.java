package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:51 AM
 */
public class CreateTable {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(CreateTable.class);

    public void createUserTable(Admin admin) throws IOException {
        // table
        TableDescriptor tableDescriptor = TableDescriptorBuilder
            .newBuilder(TableName.valueOf("users"))
            .setColumnFamilies(Arrays.asList(
                ColumnFamilyDescriptorBuilder.of("secure"),
                ColumnFamilyDescriptorBuilder.of("personal")))
            .build();

        if (admin.tableExists(tableDescriptor.getTableName())) {
             LOGGER.debug("Table [" + tableDescriptor.getTableName() + "] already exists.");
        } else {
            //creat table
            admin.createTable(tableDescriptor);
             LOGGER.debug("Created [" + tableDescriptor.getTableName() + "] table");
        }
    }

    public void createCensusTable(Admin admin) throws IOException {
        // table
        TableDescriptor tableDescriptor = TableDescriptorBuilder
            .newBuilder(TableName.valueOf("census"))
            .setColumnFamilies(Arrays.asList(
                ColumnFamilyDescriptorBuilder.of("personal"),
                ColumnFamilyDescriptorBuilder.of("professional")))
            .build();
        if (admin.tableExists(tableDescriptor.getTableName())) {
             LOGGER.debug("Table [" + tableDescriptor.getTableName() + "] already exists.");
        } else {
            //creat table
            admin.createTable(tableDescriptor);
             LOGGER.debug("Created [" + tableDescriptor.getTableName() + "] table");
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            Admin admin = connection.getAdmin();

            CreateTable createTable = new CreateTable();
            createTable.createUserTable(admin);
            createTable.createCensusTable(admin);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, null, null);
        }
    }
}
