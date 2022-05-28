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
package com.rslakra.java;

/**
 * Constants.java
 * 
 * The <code>Constants</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 01, 2010 12:42:11 PM
 */
public interface Constants {

	boolean TRUE = true;
	boolean FALSE = false;

	/* SQL Server Driver Details */
	String DRIVER_PREFIX = "jdbc:odbc:";

	/* JDBC */
	String JDBC_DRIVER_CLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
	String DSN_NAME = "jdbc:odbc:BankDB";

	/* System Details */
	String HOST = "192.168.0.43";
	String SQLSERVER_PORT = "1433"; // Default Port for SQL Server

	/* User Details */
	String USERNAME = "sa";
	String PASSWORD = "hrhk";

	/* SQL Server */
	String SQL_SERVER_DRIVER = "com.ddtek.jdbc.sqlserver.SQLServerDriver";
	String SQL_DRIVER_VENDER_NAME = "jdbc:datadirect:sqlserver";
	String DATABASE_NAME = "Vines";
	String ConnectionURL = SQL_DRIVER_VENDER_NAME + "://" + HOST + ":" + SQLSERVER_PORT + ";DatabaseName="
			+ DATABASE_NAME;
}