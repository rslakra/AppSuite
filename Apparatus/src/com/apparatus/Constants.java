/******************************************************************************
 * Copyright (C) Devamatre/Devmatre Inc. 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code or the
 * snippet is not permitted without prior express written consent of Devamatre.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 * 
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.apparatus;

/**
 * The <code>Constants</code> class defines the constants used in this project.
 * 
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created Dec 01, 2012 11:14:03 PM
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
	String SQLSERVER_PORT = "1433"; // Default Port for SQL Server
	
	/* User Details */
	String USERNAME = "sa";
	String PASSWORD = "hrhk";
	
	/* SQL Server */
	String SQL_SERVER_DRIVER = "com.ddtek.jdbc.sqlserver.SQLServerDriver";
	String SQL_DRIVER_VENDER_NAME = "jdbc:datadirect:sqlserver";
	String DATABASE_NAME = "Vines";
	String ConnectionURL = SQL_DRIVER_VENDER_NAME + "://" + HOST + ":" + SQLSERVER_PORT + ";DatabaseName=" + DATABASE_NAME;
	
	public String EMPTY_STRING = "";
	public String SLASH = "/";
	public String NEWLINE = "\n";
	public String LOCAL_HOST = "localhost";
	public String LOCAL_HOST_ADDRESS = "127.0.0.1";
	public boolean enableDrawings = true;
	public boolean enableRecentDocs = false;
	public boolean enableThumbnails = false;
	public double serverVersion = 6.0;
	public boolean showHeadersInRepository = false;
	public boolean enableDocumentOrientation = false;
	
	public interface Android {
		public float BUTTON_ENABLED_OPACITY = 1f;
		public float BUTTON_DISABLED_OPACITY = 0.5f;
		public int NO_MENU = -1;
	}
	
	public interface Services {
		/**
		 * If {@link #SHOULD_PRINT_I_AM_ALIVE} is true, our services will print
		 * an "I'm alive" message to the console every this-many seconds.
		 */
		public int PRINT_I_AM_ALIVE_FREQUENCY = 60;
		
		/**
		 * If true, our Android services will print an "I'm alive" message to
		 * the console every {@link #PRINT_I_AM_ALIVE_FREQUENCY} seconds.
		 */
		public boolean SHOULD_PRINT_I_AM_ALIVE = true;
	}
	
	public interface Cache {
		public int RECENT_DOCUMENT_CACHE_SIZE = 15;
		public int REPOSITORY_THUMBNAIL_CACHE_SIZE = 50;
	}
	
	/**
	 * The names of the strings which tell our software, and the server's
	 * software, that we're looking for the top level of a special folder
	 * hierarchy, like the Briefcase or Approvals.
	 */
	public interface FakeContainerParents {
		/** Root of the MeetX folder hierarchy. */
		public String MEETX_REPOSITORY_ROOT_ID = "redrockrootid";
		public String MEETX_FAVORITE_ROOT_ID = "favoriterootid";
		public String MEETX_SITES_ROOT_ID = "redrocksitesrootid";
	}
	
	public interface Http {
		
		public String HTTP_VERSION = "HTTP/1.1";
		public int HTTP_CONNECTION_TIMEOUT_SECONDS = 45;
		public int HTTP_READ_TIMEOUT_SECONDS = 45;
		
		public interface Headers {
			public String ACCEPT = "Accept";
			public String ACCEPT_ENCODING = "Accept-Encoding";
			public String ACCEPT_LANGUAGE = "Accept-Language";
			public String CONTENT_TYPE = "Content-Type";
			public String PRAGMA = "Pragma";
			public String USER_AGENT = "User-Agent";
			
			// JUP Headers
			public String CONTENT_LENGTH = "Content-Length";
			public String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
			public String MULTIPART_FORM_DATA = "multipart/form-data";
			
			public String CLIENT_REQUEST_HASH_CODE = "Client-Request-HashCode";
			public String HASH_CODE_FILE_LAST_MODIFIED = "Hash-Code-File-Last-Modified";
			public String HASH_CODE_FILE_SIZE = "Hash-Code-File-Size";
			public String USE_EXISTING_FILE = "Use-Existing-File";
			
			public String CONTENT_DISPOSITION = "Content-Disposition";
			public String KEY_FILE_NAME_EQUAL = "fileName=";
			public String RESPONSE_HEADERS = "Response-Headers";
		}
		
		public interface HeaderValues {
			public interface Accept {
				public String ALL = "*/*";
			}
			
			public interface AcceptEncoding {
				public String GZIP_DEFLATE = "gzip, deflate";
			}
			
			public interface AcceptLanguage {
				public String EN_US = "en-us";
			}
			
			public interface Pragma {
				public String NO_CACHE = "no-cache";
			}
		}
		
		public interface Methods {
			public String GET = "GET";
			public String DELETE = "DELETE";
			public String POST = "POST";
			public String PUT = "PUT";
		}
		
		public interface Response {
			public int STATUS_CODE_OK = 200;
		}
	}
	
	/**
	 * The names of the JSON keys (as in, key-value pairs) that we send to or
	 * receive from the server.
	 */
	public interface JsonKeys {
		/**
		 * The name of the class to instantiate for a given BO arriving from the
		 * server.
		 */
		public String BO_NAME = "boName";
	}
	
	public interface JUP {
		// JUP Files/Folders Structure
		public String JUP_ZIP_FILE_NAME = "JUP_ZIP_FILE_NAME";
		public String ZIP_FILE_DIR = "ZipFileDir";
		public String UI_FILES_DIR = "ui";
		public String DATA_FILES_DIR = "data";
		public String TEMP_DIR = "temp";
		public String WEB_SERVER_DIR = "webserver";
		public String WEB_APPS_DIR = "webapps";
		public String KEY_STORE_DIR = "keystore";
		public String LOGS_DIR = "logs";
		
		public String HTML = "html";
		public String ROOT_CONTEXT = SLASH + HTML;
		
		public String JUP_WEB_SERVER_FILE_NAME = "html.war";
		public String INDEX_HTML_FILE = "index.html";
		public String BV_PROXY_REQUEST = "BVProxyRequest";
		public String RESPONSE_FILE_EXT = ".res";
		
		public String ZIP_FILE_PREFIX = "MeetX";
		public String VERSION_PROPERTIES_FILE_NAME = "MeetX.version.properties";;
		
		public interface JUPWebServer {
			/** PORTS - default available ports. */
			public int[] PORTS = { 4132, 5210, 5220, 5230, 5240, 5250, 5260, 5270, 5280, 5290};
			
		}
		
		public interface WebView {
			public String IGNORE_BROWSER_LOGIN = "IGNORE_BROWSER_LOGIN";
			public String TAG_JAVA_SCRIPT = "<script language=\"javascript\">";
			public String IPAD_IGNORE_BROWSER_LOGIN = "localStorage.setItem('" + IGNORE_BROWSER_LOGIN + "', 'true');";
			
			public String JS_JUP_DISABLED_STRING = "var isJUPEnabled = false;";
			public String JS_JUP_ENABLED_STRING = "var isJUPEnabled = true;";
			
			public String JS_JUP_OFFLINE_STRING = "var isJUPOffline = false;";
			public String JS_JUP_ONLINE_STRING = "var isJUPOffline = true;";
			
			public String BVJS_PROTOCOL_PREFIX = "bvJS2IOS://";
			public String BVJS_SUCCESS_CALLBACK = "success";
			public String BVJS_ERROR_CALLBACK = "error";
			public String BVJS_RESULT = "result";
		}
	}
	
	public interface Keys {
		public String COMPANY_BO = "COMPANY_BO";
		public String DOMAIN_SELECTED = "DOMAIN_SELECTED";
		public String LOAD_SELECT_DOMAIN_ACTIVITY = "LOAD_SELECT_DOMAIN_ACTIVITY";
		public String SHARE_CONTAINER_BO = "SHARE_CONTAINER_BO";
		// "true" or "false"
		public String IS_HELP_VIDEO = "isHelpVideo";
		// "native", and I don't know other options.
		public String RENDERER = "renderer";
		
	}
	
	/**
	 * This class is for localization constants we know come from our server.
	 * 
	 * Any time we use this, be aware we may need to use Android localizations
	 * instead.
	 * 
	 * For more info:
	 * http://developer.android.com/reference/java/util/Locale.html
	 */
	public interface Locale {
		public String EN_US = "en_US";
	}
	
	public interface Login {
		public String ANDROID = "android";
		public String COMPANY_DOMAIN = "companyDomainName";
		public String DEVICE_ID = "deviceId";
		public String DEVICE_NAME = "deviceName";
		public String DEVICE_TYPE = "deviceType";
		public String DOMAIN = "windowsDomain"; // "BOARDVANTAGE"
		public String EMAIL = "email";
		public String IPAD = "iPAD";
		public String LOGIN_TYPE = "loginType";
		public String MAC_ADDRESS = "macAddress";
		public String MILLIS = "millis";
		public String PASSWORD = "password";
		public String SECOND_TIER_COOKIE = "secondTierCookie";
		public String SHOULD_ENCRYPT = "encrypt";
		public String TIME_ZONE_ID = "timeZoneId";
		public String TIME_ZONE_OFFSET = "timezoneOffset";
		public String USERNAME = "handle";
		public String USERNAME_DURING_RETRY = "token";
		public String TOKEN = "token";
		public String WHICH_TIER = "whichTier";
		public Boolean REMEMBER_LOGIN_CREDENTIALS_DEFAULT = true;
		public String USERNAME_KEY = "USERNAME_KEY";
		public String EMAIL_KEY = "EMAIL_KEY";
		public String DOMAIN_KEY = "DOMAIN_KEY";
		public String SUPPORT_PHONE_NUMBERS_KEY = "SUPPORT_PHONE_NUMBERS_KEY";
		public String SUPPORT_EMAIL_KEY = "SUPPORT_EMAIL_KEY";
		
	}
	
	public interface BrandingColors {
		public String LOGIN_BACKGROUND_BRANDING_COLOR_KEY = "LOGIN_BACKGROUND_BRANDING_COLOR_KEY";
		public String LOGIN_ACCENT_BRANDING_COLOR_KEY = "LOGIN_ACCENT_BRANDING_COLOR_KEY";
		public String LOGIN_BACKGROUND_TEXT_COLOR_KEY = "LOGIN_BACKGROUND_TEXT_COLOR_KEY";
		public String LOGIN_ACCENT_TEXT_COLOR_KEY = "LOGIN_ACCENT_TEXT_COLOR_KEY";
		public String COMPANY_PRIMARY_SPOT_COLOR_KEY = "COMPANY_PRIMARY_SPOT_COLOR_KEY";
		public String COMPANY_SECONDARY_SPOT_COLOR_KEY = "COMPANY_SECONDARY_SPOT_COLOR_KEY";
		public String COMPANY_PRIMARY_TEXT_COLOR_KEY = "COMPANY_PRIMARY_TEXT_COLOR_KEY";
		public String COMPANY_SECONDARY_TEXT_COLOR_KEY = "COMPANY_SECONDARY_TEXT_COLOR_KEY";
	}
	
	public interface Modules {
		public String BRIEFCASE = "BRIEFCASE";
		public String REPOSITORY = "REPOSITORY";
		public String MYCONTENT = "MYCONTENT";
		public String WEB_REPOSITORY = "WEB_REPOSITORY";
	}
	
	public interface Documents {
		public String DOCUMENT_PAGE_NUMBER = "DOCUMENT_PAGE_NUMBER";
	}
	
	public interface Server {
		public interface ExceptionName {
			public String InvalidCredentialsException = "InvalidCredentialsException";
			public String MBNotConnectedException = "MBNotConnectedException";
			public String MBUnauthorizedException = "MBUnauthorizedException";
		}
		
		public interface Keys {
			public String METHOD_NAME = "methodname";
			public String SERVICE_NAME = "servicename";
			public String CONTAINER_FOLDER = "containerFolder";
			public String ECXSESSIONID = "ECXSESSIONID";
			public String CSRF_TOKEN = "csrfToken";
			
		}
		
		public interface Methods {
			public String IPAD_REMOTE_SERVICE = "IPadRemoteService";
			public String IPAD_PUBLIC_REMOTE_SERVICE = "IPadPublicRemoteService";
			
			public String JUP_BV_JSON_DOCUMENT_SERVICE = "BvJsonDocumentService";
			public String JUP_BV_JSON_MESSAGES_SERVICE = "BvJsonMessagesService";
		}
		
		public interface Services {
			public String SERVICES = "services";
			public String LOGIN_IPAD_PUBLIC = "login/ipadpublic";
			public String LOGIN_RESOURCES = "login/resources";
			public String SERVICES_IPAD = "services/ipad";
			public String SERVICES_IPAD_PUBLIC = "services/ipadpublic";
			public String SERVICES_DOWNLOAD = "services/download";
			public String DESKTOP_ZIP_SERVICE = "desktop/zipservice";
			public String SERVICES_IPAD_IMAGE_LOADER = "services/ipadimageloader";
		}
	}
	
	public interface Threads {
		public String EVENT_THREAD_NAME = "EventHandlerThread";
	}
	
	public interface Thumbnails {
		public float ASPECT_RATIO_WIDTH = 8.5f;
		public float ASPECT_RATIO_HEIGHT = 11f;
	}
	
	public interface UUIDs {
		public String PARENT_UUID = "parentUuid";
		public String OBJECT_BASE_UUID = "objectBaseUuid";
		public String BASE_UUID = "baseuuid";
		public String JSON_ARRAY_LOAD = "jsonArrayLoad";
		public String HASH_SEPARATED_UUIDS = "hashSeparatedUuids";
		public String OBJECT_BASE_UUIDS = "objectBaseUuids";
		public String OBJECT_VERSION_UUID = "objectVersionUuid";
		public String RRSITE_UUID = "rrsiteuuid";
	}
	
	public interface UUIDValues {
		public String USER_BRIEFCASE_FOLDER_UUID = "userBriefcaseFolder";
		public String USER_NEW_ITEMS_FOLDER_UUID = "newItemsFolder";
		public String USER_UUID_SYNC_FOLDER_UUID = "userUuidSyncFolder";
	}
}