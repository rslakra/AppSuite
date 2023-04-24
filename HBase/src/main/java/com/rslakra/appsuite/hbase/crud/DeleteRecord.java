package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:51 AM
 */
public class DeleteRecord {

    // column-families
    private static final byte[] PERSONAL_CF = Bytes.toBytes("personal");
    private static final byte[] PROFESSIONAL_CF = Bytes.toBytes("professional");

    // columns
    private static final byte[] EMAIL = Bytes.toBytes("email");
    private static final byte[] JOB = Bytes.toBytes("job");

    public static void main(String[] args) {
        Connection connection = null;
        Table censusTable = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            //table
            censusTable = connection.getTable(TableName.valueOf("census"));

            //delete record 1
            Delete delete = new Delete(Bytes.toBytes("1"));
            delete.addColumn(PERSONAL_CF, EMAIL);
            delete.addColumn(PROFESSIONAL_CF, JOB);

            censusTable.delete(delete);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, censusTable);
        }
    }
}
