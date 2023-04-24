package com.rslakra.appsuite.pdftools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fit.pdfdom.PDFDomTree;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 18, 2023 13:39:11
 */
public enum PdfUtils {
    INSTANCE;

    public static final String UTF_8 = "UTF-8";
    public static final String DOT_PDF = ".pdf";
    public static final String DOT_JPG = ".jpg";

    /**
     * @param pdfFileName
     * @param htmlFileName
     * @throws IOException
     */
    public static void pdfToHtmlGenerator(String pdfFileName, String htmlFileName) throws IOException {
        PDDocument pdDocument = PDDocument.load(new File(pdfFileName));
        Writer output = new PrintWriter(htmlFileName, UTF_8);
        PDFDomTree pdfDomTree = new PDFDomTree();
        pdfDomTree.writeText(pdDocument, output);
        output.close();
    }

    /**
     * @param htmlFileName
     * @param pdfFileName
     * @throws IOException
     * @throws DocumentException
     */
    public static void htmlToPDFGenerator(String htmlFileName, String pdfFileName) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(htmlFileName));
        document.close();
    }


    /**
     * @param pdfFileName
     * @param imageFileName
     * @param extension
     * @throws IOException
     */
    public static void pdfToImageGenerator(String pdfFileName, String imageFileName, String extension) throws IOException {
        PDDocument pdDocument = PDDocument.load(new File(pdfFileName));
        PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
        for (int page = 0; page < pdDocument.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, String.format("%s-%d.%s", imageFileName, page + 1, extension), 300);
        }
        pdDocument.close();
    }


    /**
     * @param imageFileName
     * @param pdfFileName
     * @throws IOException
     * @throws DocumentException
     */
    public static void imageToPDFGenerator(String imageFileName, String pdfFileName) throws IOException, DocumentException {
        int dotIndex = imageFileName.lastIndexOf(".");
        if (dotIndex == -1) {
            throw new RuntimeException("fileName is missing extension!");
        }

        final String imageFileExtension = imageFileName.substring(dotIndex + 1);
        Document document = new Document();
        String pdfImageFileName = pdfFileName;
        if (pdfFileName.toLowerCase().endsWith(DOT_PDF)) {
            pdfImageFileName = pdfFileName.substring(0, pdfFileName.lastIndexOf(".")) + imageFileExtension + DOT_PDF;
        }

        pdfImageFileName = pdfImageFileName + imageFileExtension + DOT_PDF;
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfImageFileName));
        pdfWriter.open();
        document.open();
        document.add(Image.getInstance((new URL(imageFileName))));
        document.close();
        pdfWriter.close();
    }
}
