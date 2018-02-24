/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
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
package com.rslakra.java.enums;

/**
 * (A) The following items are considered punctuation:
 * 
 * <pre>
 * Period					.
 * Slash					/
 * Comma					,
 * Back Slash				\
 * Semicolon				;
 * Hyphen or Dash 			-
 * Colon					:
 * Underline				_
 * Apostrophe				‘
 * Swung Dash/Tilde			~
 * Single Quotation Mark	‘
 * Parentheses				( )
 * Double Quotation Mark	“ ”
 * Brackets					[ ]
 * Question Mark			?
 * Angle Brackets			< >
 * Exclamation Mark			!
 * Braces					{ }
 * 
 * (B) The following items are considered symbols:
 * At / each				@
 * Number / pounds			#
 * Dollars					$
 * Percent					%
 * Caret					^
 * Ampersand				&
 * Asterisk					*
 * Plus / positive			+
 * Equal					=
 * 
 * @author singhr
 * 
 */
public enum Symbols {
	/* Period */
	PERIOD("."),

	/* Slash */
	SLASH("/"),

	/* Comma */
	COMMA(","),

	/* BackSlash */
	BACK_SLASH("\\"),

	/* Semicolon */
	SEMICOLON(";"),

	/* Hyphen */
	HYPHEN("-"),

	/* Colon */
	COLON(":"),

	/* Underline */
	UNDERLINE("_"),

	/* Apostrophe */
	APOSTROPHE("'"),

	/* Tilde */
	TILDE("~"),

	/* SingleQuotes */
	SINGLE_QUOTE("'"),

	/* Parentheses */
	PARENTHESES("( )"),

	/* DoubleQuotes */
	DOUBLE_QUOTE("\""),

	/* Brackets */
	BRACKETS("[ ]"),

	/* QuestionMark */
	QUESTION_MARK("?"),

	/* AngleBrackets */
	ANGLE_BRACKETS("< >"),

	/* ExclamationMark */
	EXCLAMATION_MARK("!"),

	/* Braces */
	BRACES("{ }"),

	/* At */
	At("@"),

	/* Pound */
	POUND("#"),

	/* Dollar */
	DOLLAR("$"),

	/* Percent */
	PERCENT("%"),

	/* Caret */
	CARET("^"),

	/* Ampersand */
	AMPERSAND("&"),

	/* Asterisk */
	ASTERISK("*"),

	/* Plus */
	PLUS("+"),

	/* Equal */
	EQUAL_TO("=");

	Symbols(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	private String symbol;

}