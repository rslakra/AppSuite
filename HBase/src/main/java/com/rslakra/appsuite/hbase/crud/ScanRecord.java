package com.rslakra.appsuite.hbase.crud;

import com.rslakra.appsuite.hbase.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 9/18/20 8:51 AM
 */
public class ScanRecord {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ScanRecord.class);

    // column-families
    private static final byte[] PERSONAL_CF = Bytes.toBytes("personal");
    private static final byte[] PROFESSIONAL_CF = Bytes.toBytes("professional");

    // columns
    private static final byte[] FIRST_NAME = Bytes.toBytes("firstName");
    private static final byte[] MIDDLE_NAME = Bytes.toBytes("middleName");
    private static final byte[] LAST_NAME = Bytes.toBytes("lastName");
    private static final byte[] PROFESSION = Bytes.toBytes("profession");
    private static final byte[] EDUCATION = Bytes.toBytes("education");

    public static void main(String[] args) {
        Connection connection = null;
        Table censusTable = null;
        ResultScanner resultScanner = null;
        try {
            connection = HBaseUtil.getHBaseConnection();
            //table
            censusTable = connection.getTable(TableName.valueOf("census"));

            Scan scan = new Scan();
            resultScanner = censusTable.getScanner(scan);
            for (Result result : resultScanner) {
                for (Cell cell : result.listCells()) {
                    String row = new String(CellUtil.cloneRow(cell));
                    String family = new String(CellUtil.cloneFamily(cell));
                    String column = new String(CellUtil.cloneQualifier(cell));
                    String value = new String(CellUtil.cloneValue(cell));
                    LOGGER.debug(row + " " + family + ":" + column + " " + value);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            HBaseUtil.closeSilently(connection, censusTable, resultScanner);
        }
    }
}
