/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
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
package com.rslakra.appsuite.mimecraft;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <a href="http://www.ietf.org/rfc/rfc2387.txt">RFC 2387</a>-compliant encoded
 * data.
 */
public final class Multipart implements Part {

    /**
     * Multipart MIME types.
     */
    public enum Type {
        /**
         * The "mixed" subtype of "multipart" is intended for use when the body
         * parts are independent
         * and need to be bundled in a particular order. Any "multipart"
         * subtypes that an
         * implementation does not recognize must be treated as being of subtype
         * "mixed".
         */
        MIXED("mixed"),
        /**
         * The "multipart/alternative" type is syntactically identical to
         * "multipart/mixed", but the
         * semantics are different. In particular, each of the body parts is an
         * "alternative" version
         * of the same information.
         */
        ALTERNATIVE("alternative"),
        /**
         * This type is syntactically identical to "multipart/mixed", but the
         * semantics are different.
         * In particular, in a digest, the default {@code Content-Type} value
         * for a body part is
         * changed from "text/plain" to "message/rfc822".
         */
        DIGEST("digest"),
        /**
         * This type is syntactically identical to "multipart/mixed", but the
         * semantics are different.
         * In particular, in a parallel entity, the order of body parts is not
         * significant.
         */
        PARALLEL("parallel"),
        /**
         * The media-type multipart/form-data follows the rules of all multipart
         * MIME data streams as
         * outlined in RFC 2046. In forms, there are a series of fields to be
         * supplied by the user who
         * fills out the form. Each field has a name. Within a given form, the
         * names are unique.
         */
        FORM("form-data");

        final String contentType;

        private Type(String contentType) {
            this.contentType = contentType;
        }
    }

    /**
     * Fluent API to build {@link Multipart} instances.
     */
    public static class Builder {
        private final String boundary;
        private final List<Part> parts = new ArrayList<Part>();
        private Type type = Type.MIXED;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String boundary) {
            this.boundary = boundary;
        }

        /**
         * Set the MIME type.
         */
        public Builder type(Type type) {
            Utils.isNotNull(type, "Type must not be null.");
            this.type = type;
            return this;
        }

        /**
         * Add a part to the body.
         */
        public Builder addPart(Part part) {
            Utils.isNotNull(part, "Part must not be null.");
            parts.add(part);
            return this;
        }

        /**
         * Assemble the specified parts into a {@link Multipart}.
         */
        public Multipart build() {
            if (parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new Multipart(type, parts, boundary);
        }
    }

    private final List<Part> parts;
    private final Map<String, String> headers;
    private final String boundary;

    private Multipart(Type type, List<Part> parts, String boundary) {
        Utils.isNotNull(type, "Multipart type must not be null.");

        this.parts = parts;
        this.headers = Collections.singletonMap("Content-Type", "multipart/" + type.contentType + "; boundary=" + boundary);
        this.boundary = boundary;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void writeBodyTo(OutputStream stream) throws IOException {
        byte[] boundary = this.boundary.getBytes("UTF-8");
        boolean first = true;
        for (Part part : parts) {
            writeBoundary(stream, boundary, first, false);
            writePart(stream, part);
            first = false;
        }
        writeBoundary(stream, boundary, false, true);
    }

    private static void writeBoundary(OutputStream out, byte[] boundary, boolean first, boolean last) throws IOException {
        if (!first) {
            out.write('\r');
            out.write('\n');
        }
        out.write('-');
        out.write('-');
        out.write(boundary);
        if (last) {
            out.write('-');
            out.write('-');
        } else {
            out.write('\r');
            out.write('\n');
        }
    }

    private static void writePart(OutputStream out, Part part) throws IOException {
        Map<String, String> headers = part.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                out.write(header.getKey().getBytes("UTF-8"));
                out.write(':');
                out.write(' ');
                out.write(header.getValue().getBytes("UTF-8"));
                out.write('\r');
                out.write('\n');
            }
        }
        out.write('\r');
        out.write('\n');
        part.writeBodyTo(out);
    }
}
