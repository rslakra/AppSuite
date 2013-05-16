/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.core.lang;

/**
 * Constants.java
 * 
 * The <code>Constants</code> 
 * TODO Define Purpose here
 * 
 * 
  * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
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
    String SQLSERVER_PORT = "1433"; //Default Port for SQL Server

    /* User Details */
    String USERNAME = "sa";
    String PASSWORD = "hrhk";

    /* SQL Server */
    String SQL_SERVER_DRIVER = 	"com.ddtek.jdbc.sqlserver.SQLServerDriver";
    String SQL_DRIVER_VENDER_NAME = "jdbc:datadirect:sqlserver";
    String DATABASE_NAME = "Vines";
    String ConnectionURL = SQL_DRIVER_VENDER_NAME + "://" + HOST + ":"  + 
    	SQLSERVER_PORT + ";DatabaseName=" + DATABASE_NAME;
}