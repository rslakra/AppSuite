package com.rslakra.appsuite.pdftools;

import com.rslakra.appsuite.core.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 18, 2023 14:10:59
 */
public class PdfTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfTools.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        String htmlFileName = "Transaction-Fees.html";
        String pdfFileName = "Transaction-Fees.pdf";
        String imageFileName = "Transaction-Fees";
//        try {
        String filePath = IOUtils.pathString(IOUtils.getUserDir(), "/pdf-tools/target/classes");
        LOGGER.debug("filePath: {}", filePath);
        pdfFileName = IOUtils.pathString(filePath, pdfFileName);
        LOGGER.debug("pdfFileName: {}", pdfFileName);
        htmlFileName = IOUtils.pathString(filePath, htmlFileName);
        LOGGER.debug("htmlFileName: {}", htmlFileName);
//            PdfUtils.pdfToHtmlGenerator(pdfFileName, htmlFileName);
//            PdfUtils.pdfToImageGenerator(pdfFileName, imageFileName, PdfUtils.DOT_JPG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
