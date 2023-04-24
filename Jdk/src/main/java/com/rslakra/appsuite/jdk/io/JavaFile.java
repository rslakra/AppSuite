package com.rslakra.appsuite.jdk.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author Rohtash Lakra
 * @created 10/19/21 3:07 PM
 */
public class JavaFile {

    // LOGGER
    private final static Logger LOGGER = LoggerFactory.getLogger(JavaFile.class);

    public void printMX3Feeds() {
        final String pathString = "/Users/rlakra/Downloads/WorkData/MX3Feeds";
        File file = new File(pathString);
        File[] allFiles = file.listFiles();
        Arrays.sort(allFiles, Collections.reverseOrder());
        TreeSet<String> allFilePaths = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
                return o2.compareTo(o1);
            }
        });
        for (File feed : allFiles) {
            allFilePaths.add(feed.getAbsolutePath());
        }
        LOGGER.info("allFilePaths={}", allFilePaths);
        String feedToDelete = null;
        Iterator<String> itr = allFilePaths.iterator();
        for (int i = 0; i < allFiles.length; i++) {
            feedToDelete = itr.next();
            LOGGER.info("feedToDelete={}", feedToDelete);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        JavaFile javaFile = new JavaFile();
        javaFile.printMX3Feeds();
    }
}
