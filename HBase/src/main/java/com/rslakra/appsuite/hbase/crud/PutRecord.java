package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:51 AM
 */
public class PutRecord {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(PutRecord.class);

    private static final byte[] USER_NAME = Bytes.toBytes("userName");
    private static final byte[] PASSWORD = Bytes.toBytes("password");
    private static final byte[] EMAIL = Bytes.toBytes("email");
    private static final byte[] FIRST_NAME = Bytes.toBytes("firstName");
    private static final byte[] MIDDLE_NAME = Bytes.toBytes("middleName");
    private static final byte[] LAST_NAME = Bytes.toBytes("lastName");
    private static final byte[] PROFESSION = Bytes.toBytes("profession");
    private static final byte[] EDUCATION = Bytes.toBytes("education");

    // column-families
    private static final byte[] SECURE_CF = Bytes.toBytes("secure");
    private static final byte[] PERSONAL_CF = Bytes.toBytes("personal");
    private static final byte[] PROFESSIONAL_CF = Bytes.toBytes("professional");

    /**
     * @param table
     * @throws IOException
     */
    public void addUserRecords(Table table) throws IOException {
        Put put = new Put(Bytes.toBytes("1"));
        put.addColumn(SECURE_CF, USER_NAME, Bytes.toBytes("rslakra"));
        put.addColumn(SECURE_CF, PASSWORD, Bytes.toBytes("password"));
        put.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Rohtash"));
        put.addColumn(PERSONAL_CF, MIDDLE_NAME, Bytes.toBytes("Singh"));
        put.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));
        put.addColumn(PERSONAL_CF, EMAIL, Bytes.toBytes("rslakra.work@gmail.com"));
        table.put(put);
        LOGGER.debug("Inserted record for Rohtash");

        Put put2 = new Put(Bytes.toBytes("2"));
        put2.addColumn(SECURE_CF, USER_NAME, Bytes.toBytes("slakra"));
        put2.addColumn(SECURE_CF, PASSWORD, Bytes.toBytes("password"));
        put2.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Sangita"));
        put2.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));

        Put put3 = new Put(Bytes.toBytes("3"));
        put3.addColumn(SECURE_CF, USER_NAME, Bytes.toBytes("hlakra"));
        put3.addColumn(SECURE_CF, PASSWORD, Bytes.toBytes("password"));
        put3.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Harsh"));
        put3.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));

        // add collection in table.
        table.put(Arrays.asList(put2, put3));
        LOGGER.debug("Inserted records for Sangita and Harsh");
    }


    public void addCensusRecords(Table table) throws IOException {
        // columns
        Put put = new Put(Bytes.toBytes("1"));
        put.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Rohtash"));
        put.addColumn(PERSONAL_CF, MIDDLE_NAME, Bytes.toBytes("Singh"));
        put.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));
        put.addColumn(PERSONAL_CF, EMAIL, Bytes.toBytes("rslakra.work@gmail.com"));

        put.addColumn(PROFESSIONAL_CF, PROFESSION, Bytes.toBytes("Software Development"));
        put.addColumn(PROFESSIONAL_CF, EDUCATION, Bytes.toBytes("Post Graduate"));
        table.put(put);
        LOGGER.debug("Inserted record for Rohtash");

        Put put2 = new Put(Bytes.toBytes("2"));
        put2.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Sangita"));
        put2.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));
        put2.addColumn(PROFESSIONAL_CF, PROFESSION, Bytes.toBytes("Home Maker"));
        put2.addColumn(PROFESSIONAL_CF, EDUCATION, Bytes.toBytes("Intermediate"));

        Put put3 = new Put(Bytes.toBytes("3"));
        put3.addColumn(PERSONAL_CF, FIRST_NAME, Bytes.toBytes("Anuj"));
        put3.addColumn(PERSONAL_CF, LAST_NAME, Bytes.toBytes("Lakra"));
        put3.addColumn(PROFESSIONAL_CF, PROFESSION, Bytes.toBytes("Student"));
        put3.addColumn(PROFESSIONAL_CF, EDUCATION, Bytes.toBytes("High School"));

        // add collection in table.
        table.put(Arrays.asList(put2, put3));
        LOGGER.debug("Inserted records for Sangita and Anuj");
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection connection = null;
        Table userTable = null;
        Table censusTable = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            PutRecord createTable = new PutRecord();
            //table
            userTable = connection.getTable(TableName.valueOf("users"));
            createTable.addUserRecords(userTable);

            censusTable = connection.getTable(TableName.valueOf("census"));
            createTable.addCensusRecords(censusTable);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, userTable, censusTable);
        }
    }
}
