package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
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
public class GetRecord {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(GetRecord.class);

    // column-families
    private static final byte[] PERSONAL_CF = Bytes.toBytes("personal");
    private static final byte[] PROFESSIONAL_CF = Bytes.toBytes("professional");

    // columns
    private static final byte[] EMAIL = Bytes.toBytes("email");
    private static final byte[] FIRST_NAME = Bytes.toBytes("firstName");
    private static final byte[] MIDDLE_NAME = Bytes.toBytes("middleName");
    private static final byte[] LAST_NAME = Bytes.toBytes("lastName");
    private static final byte[] PROFESSION = Bytes.toBytes("profession");
    private static final byte[] EDUCATION = Bytes.toBytes("education");

    public static void main(String[] args) {
        Connection connection = null;
        Table censusTable = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            //table
            censusTable = connection.getTable(TableName.valueOf("census"));

            Get get = new Get(Bytes.toBytes("1"));
            get.addColumn(PERSONAL_CF, FIRST_NAME);
            get.addColumn(PROFESSIONAL_CF, EDUCATION);
            Result result = censusTable.get(get);
            String firstName = new String(result.getValue(PERSONAL_CF, FIRST_NAME));
            String education = new String(result.getValue(PROFESSIONAL_CF, EDUCATION));
            LOGGER.debug("firstName:" + firstName + ", education:" + education);

            Get get2 = new Get(Bytes.toBytes("2"));
            get2.addColumn(PERSONAL_CF, FIRST_NAME);
            get2.addColumn(PERSONAL_CF, LAST_NAME);
            get2.addColumn(PROFESSIONAL_CF, PROFESSION);

            Get get3 = new Get(Bytes.toBytes("3"));
            get3.addColumn(PERSONAL_CF, FIRST_NAME);
            get3.addColumn(PERSONAL_CF, LAST_NAME);
            get3.addColumn(PROFESSIONAL_CF, PROFESSION);

            // Get collection in table.
            Result[] results = censusTable.get(Arrays.asList(get2, get3));
            for (Result temp : results) {
                firstName = new String(temp.getValue(PERSONAL_CF, FIRST_NAME));
                String lastName = new String(temp.getValue(PERSONAL_CF, LAST_NAME));
                String profession = new String(temp.getValue(PROFESSIONAL_CF, PROFESSION));
                LOGGER.debug("Name:" + firstName + " " + lastName + ", profession:" + profession);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, censusTable);
        }
    }
}
