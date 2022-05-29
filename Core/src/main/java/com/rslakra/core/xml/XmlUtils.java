package com.rslakra.core.xml;

import com.rslakra.core.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * @author Rohtash Lakra
 * @created 5/19/20 4:18 PM
 */
public enum XmlUtils {

    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * Returns the new <code>DocumentBuilder</code> object.
     *
     * @return
     * @throws ParserConfigurationException
     */
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /**
     * Returns the new <code>ByteArrayOutputStream</code> object.
     *
     * @return
     */
    public ByteArrayOutputStream newByteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }

    /**
     * Returns the new <code>Transformer</code> object.
     *
     * @param encoding
     * @return
     * @throws TransformerConfigurationException
     */
    public Transformer newTransformer(final String encoding) throws TransformerConfigurationException {
        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        if (BeanUtils.isNotNullOrEmpty(encoding)) {
            transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
        }

        return transformer;
    }

    /**
     * Returns the new <code>Transformer</code> object.
     *
     * @return
     * @throws TransformerConfigurationException
     */
    public Transformer newTransformer() throws TransformerConfigurationException {
        return newTransformer(null);
    }

}
