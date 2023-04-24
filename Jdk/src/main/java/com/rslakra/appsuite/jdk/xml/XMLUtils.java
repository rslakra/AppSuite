/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.appsuite.jdk.xml;

/**
 * XmlUtils.java
 * <p>
 * The <code>XmlUtils</code> TODO Define Purpose here
 *
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 21, 2010 1:06:19 PM
 */
public enum XMLUtils {

    INSTANCE;

    /**
     * TAG_QUOTE
     */
    public static final char[] TAG_QUOTE = "&quot;".toCharArray();

    /**
     * TAG_AMP
     */
    public static final char[] TAG_AMPERSAND = "&amp;".toCharArray();

    /**
     * TAG_LESS_THAN
     */
    public static final char[] TAG_LESS_THAN = "&lt;".toCharArray();

    /**
     * TAG_GREATER_THAN
     */
    public static final char[] TAG_GREATER_THAN = "&gt;".toCharArray();

    /**
     * TAG_APOSTROPHE
     */
    public static final char[] TAG_APOSTROPHE = "&apos;".toCharArray();

    /**
     * @param input
     * @param index
     * @param lastIndex
     * @param output
     * @return
     */
    private int appendAndGetLastIndex(char[] input, int index, int lastIndex, StringBuffer output) {
        if (index > lastIndex) {
            output.append(input, lastIndex, index - lastIndex);
        }

        return (index + 1);
    }

    /**
     * This method takes a string and converts the '<', '>', '&', '"', ''' to
     * their XML escape sequences, like "&lt;", "&lt;" etc.
     *
     * <p>
     * In addition, ASCII control characters that are not permitted in XML
     * documents will be removed.
     * </p>
     *
     * @param textString in the text to be converted.
     * @return - the input string with the characters '<' and '>' replaced with
     * the escape sequences
     */
    public String escapeXMLSequences(String textString) {
        char ch;
        int index = 0;
        int last = 0;
        char[] input = textString.toCharArray();
        StringBuffer out = new StringBuffer((int) (input.length * 1.3));

        for (; index < input.length; index++) {
            ch = input[index];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                last = appendAndGetLastIndex(input, index, last, out);
                out.append(TAG_LESS_THAN);
            } else if (ch == '>') {
                last = appendAndGetLastIndex(input, index, last, out);
                out.append(TAG_GREATER_THAN);
            } else if (ch == '&') {
                last = appendAndGetLastIndex(input, index, last, out);
                out.append(TAG_AMPERSAND);
            } else if (ch == '"') {
                last = appendAndGetLastIndex(input, index, last, out);
                out.append(TAG_QUOTE);
            } else if (ch == '\'') {
                last = appendAndGetLastIndex(input, index, last, out);
                out.append(TAG_APOSTROPHE);
            } else if ((ch < 0x20) && ((ch != 0x09) && (ch != 0x0A) && (ch != 0x0D))) {
                /**
                 * Omit ASCII control characters, as per XML specification (see
                 * http://www.w3.org/TR/2000/REC-xml-20001006#charsets);
                 */
                last = appendAndGetLastIndex(input, index, last, out);
            }
        }

        if (last == 0) {
            return textString;
        }

        appendAndGetLastIndex(input, index, last, out);

        return out.toString();
    }

    /**
     * This method returns the string representation of an XML element as
     * <name>value</name>
     *
     * @param name
     * @param value
     * @return
     */
    public String generateElement(String name, int value) {
        return generateElement(name, String.valueOf(value));
    }

    /**
     * This method returns the string representation of an XML element as
     * <name>value</name>
     *
     * @param name
     * @param value
     * @return
     */
    public String generateElement(String name, boolean value) {
        return generateElement(name, String.valueOf(value));
    }

    /**
     * This method returns the string representation of an XML element as
     * <name>value</name>
     *
     * @param name
     * @param value
     * @return
     */
    public String generateElement(String name, long value) {
        return generateElement(name, String.valueOf(value));
    }

    /**
     * This method returns the string representation of an XML element as
     * <name>value</name>
     *
     * @param name
     * @param value
     * @return
     */
    public String generateElement(String name, double value) {
        return generateElement(name, String.valueOf(value));
    }

    /**
     * This method returns the string representation of an XML element as
     * <name>value</name>
     *
     * @param name
     * @param value
     * @return
     */
    public String generateElement(String name, String value) {
        if (value == null) {
            return emptyElement(name);
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(startElement(name));
        buffer.append(escapeXMLSequences(value));
        buffer.append(endElement(name));

        return buffer.toString();
    }

    /**
     * This method returns the string representation of an XML element start tag
     * as <name>
     *
     * @param name
     * @return
     */
    public String startElement(String name) {
        return ("<" + name + ">\n");
    }

    /**
     * This method returns the string representation of an xml element end tag
     * as </name>
     *
     * @param name
     * @return
     */
    public String endElement(String name) {
        return ("</" + name + ">\n");
    }

    /**
     * This method returns the string representation of an xml empty element as
     * <name />
     *
     * @param name
     * @return
     */
    public String emptyElement(String name) {
        return ("<" + name + " />\n");
    }

    /**
     * This method returns the string representation of an xml element as <name
     * attnames1="attvalues1" attnames2="attvalues2" ... />
     *
     * @param name
     * @param attNames
     * @param attValues
     * @return
     */
    public String generateElement(String name, String[] attNames, String[] attValues) {
        StringBuffer buf = new StringBuffer();
        buf = buf.append("<");
        buf = buf.append(name);

        for (int i = 0; i < attNames.length; i++) {
            buf.append(" ");
            buf.append(attNames[i]);
            buf.append("=\"");
            buf.append(escapeXMLSequences(attValues[i]));
            buf.append("\"");
        }

        buf = buf.append(" />\n");

        return buf.toString();
    }

    /**
     * This method returns the string representation of an XML element as <name
     * attnames1="attvalues1" attnames2="attvalues2" ... >value</name>
     *
     * @param name
     * @param value
     * @param attNames
     * @param attValues
     * @return
     */
    public String generateElement(String name, String value, String[] attNames, String[] attValues) {
        StringBuffer buf = new StringBuffer();
        buf = buf.append("<");
        buf = buf.append(name);

        for (int i = 0; i < attNames.length; i++) {
            buf.append(" ");
            buf.append(attNames[i]);
            buf.append("=\"");
            buf.append(escapeXMLSequences(attValues[i]));
            buf.append("\"");
        }

        buf = buf.append(">");

        buf = buf.append(escapeXMLSequences(value));

        buf = buf.append("</");
        buf = buf.append(name);
        buf = buf.append(">\n");

        return buf.toString();
    }

    /**
     * This method returns the string representation of an XML element start tag
     * as <name attnames1="attvalues1" attnames2="attvalues2" ... >
     *
     * @param name
     * @param attNames
     * @param attValues
     * @return
     */
    public String startElement(String name, String[] attNames, String[] attValues) {
        StringBuffer buf = new StringBuffer();
        buf = buf.append("<");
        buf = buf.append(name);

        for (int i = 0; i < attNames.length; i++) {
            buf.append(" ");
            buf.append(attNames[i]);
            buf.append("=\"");
            buf.append(escapeXMLSequences(attValues[i]));
            buf.append("\"");
        }

        buf = buf.append(">\n");

        return buf.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        String in = "this is a simple & working test<>";
        System.out.println(XMLUtils.INSTANCE.escapeXMLSequences(in));
        String element = "Address";
        System.out.println(XMLUtils.INSTANCE.generateElement("Address", "V & P. O. Kanheli"));
        System.out.println(XMLUtils.INSTANCE.startElement(element));
        System.out.println(XMLUtils.INSTANCE.generateElement("City", new String[]{"CODE", "ZIP"},
                new String[]{"01262", "124001"}));
        System.out.println(XMLUtils.INSTANCE.generateElement("State", "Haryana", new String[]{"Region"},
                new String[]{"North"}));
        System.out.println(XMLUtils.INSTANCE.endElement(element));
    }
}
