package com.apparatus.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Apr 22, 2015 4:52:15 PM
 */
public class FileHelper {
	
	// 1K Buffer Size
	public static final int BUFFER_1K = 1024;
	// 4K Buffer Size
	public static final int BUFFER_4K = 4 * BUFFER_1K;
	// 8K Buffer Size
	public static final int BUFFER_8K = 2 * BUFFER_4K;
	// 16K Buffer Size
	public static final int BUFFER_16K = BUFFER_4K * BUFFER_4K;
	
	// DEFAULT_URL
	private static final String DEFAULT_URL = "https://unreachable/";
	
	/**
	 * Returns the path for the given parentDirectory and the given fileName.
	 * 
	 * @param parentDirectory
	 * @param fileName
	 * @return
	 */
	public static String pathString(String parentDirectory, String fileName) {
		return ((StringHelper.isNullOrEmpty(parentDirectory) ? "" : parentDirectory + File.separator) + fileName);
	}
	
	/**
	 * Returns the bytes of the specified path, if exists, otherwise null.
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] readFile(String path) {
		byte[] bytes = null;
		if(!StringHelper.isNullOrEmpty(path)) {
			// Read file into buffer
			RandomAccessFile randomAccessFile = null;
			try {
				randomAccessFile = new RandomAccessFile(new File(path), "r");
				long length = randomAccessFile.length();
				bytes = new byte[(int) length];
				randomAccessFile.readFully(bytes);
			} catch(IOException ex) {
				System.err.println(ex);
			} finally {
				close(randomAccessFile);
			}
		}
		
		return bytes;
	}
	
	/**
	 * Writes the file on the specified path and populated with the provided
	 * data.
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public static boolean writeFile(String path, byte[] data) {
		boolean result = false;
		if(StringHelper.isNullOrEmpty(path)) {
			throw new NullPointerException("Path must be provided!");
		}
		
		if(ObjectHelper.isNullOrEmpty(data)) {
			throw new NullPointerException("data must be provided!");
		}
		
		try {
			return write(data, new FileOutputStream(new File(path)), true);
		} catch(FileNotFoundException ex) {
			System.err.println(ex);
		} catch(IOException ex) {
			System.err.println(ex);
		}
		
		return result;
	}
	
	/**
	 * Loads the properties file pointing to an inputStream.
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Properties loadProperties(InputStream inputStream) {
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch(Exception ex) {
			System.err.println(ex);
		}
		return properties;
	}
	
	/**
	 * Loads the specified properties file. All the android specific properties
	 * should be kept in the assets folder and just pass the name of the
	 * properties file
	 * 
	 * @param filePath
	 * @return
	 */
	public static Properties loadProperties(String filePath) {
		Properties properties = new Properties();
		try {
			properties = loadProperties(new FileInputStream(filePath));
		} catch(Exception ex) {
			System.err.println(ex);
		}
		
		return properties;
	}
	
	/**
	 * Saves the specified properties into the specified file path.
	 * 
	 * @param filePath
	 * @param properties
	 */
	public static void saveProperties(String filePath, Properties properties) {
		if(!StringHelper.isNullOrEmpty(filePath)) {
			FileOutputStream outputStream = null;
			try {
				File file = new File(filePath);
				if(!file.exists()) {
					boolean fileCreated = file.createNewFile();
					if(!fileCreated) {
						System.out.println("Unable to create the file:" + filePath);
					}
				}
				outputStream = new FileOutputStream(file);
				properties.store(outputStream, null);
				outputStream.flush();
				System.out.println("Version information saved to file:" + filePath);
			} catch(FileNotFoundException ex) {
				System.err.println(ex);
			} catch(IOException ex) {
				System.err.println(ex);
			} finally {
				close(outputStream);
			}
		}
	}
	
	/**
	 * Prints the specified properties.
	 * 
	 * @param properties
	 */
	public static void printProperties(Properties properties) {
		if(properties != null) {
			Enumeration<Object> keys = properties.keys();
			while(keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = (String) properties.get(key);
				System.out.println(key + ": " + value);
			}
		}
	}
	
	/**
	 * Returns true if the specified file exists, otherwise false.
	 * 
	 * @param properties
	 */
	public static boolean isExist(String filePath) {
		return (!StringHelper.isNullOrEmpty(filePath) && new File(filePath).exists());
	}
	
	/**
	 * Returns true if the specified file exists, otherwise false.
	 * 
	 * @param properties
	 */
	public static boolean isExist(File file) {
		return (file != null && file.exists());
	}
	
	/**
	 * Creates the buffer with the available size if its greater than the
	 * defaultSize.
	 * 
	 * @param available
	 * @param bufferSize
	 * @return
	 */
	public static byte[] getBuffer(int available, int defaultSize) {
		return new byte[(available > defaultSize ? available : defaultSize)];
	}
	
	/**
	 * 
	 * @param available
	 * @return
	 */
	public static byte[] getBuffer(int available) {
		return getBuffer(available, BUFFER_8K);
	}
	
	/**
	 * Writes the contents of the input steam to output stream.
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @param closeStreams
	 * @throws IOException
	 */
	public static int write(InputStream inputStream, OutputStream outputStream, boolean closeStreams) throws IOException {
		int fileSize = 0;
		if(inputStream != null && outputStream != null) {
			try {
				byte[] buffer = getBuffer(inputStream.available());
				int length = 0;
				while((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
					fileSize += length;
				}
				
				/* flush output streams. */
				outputStream.flush();
			} catch(IOException ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				/* close streams. */
				if(closeStreams) {
					close(inputStream);
					close(outputStream);
				}
			}
		}
		
		return fileSize;
	}
	
	/**
	 * Writes the contents of the input steam to output stream.
	 * 
	 * @param input
	 * @param outputStream
	 * @param closeStreams
	 * @throws IOException
	 */
	public static boolean write(byte[] input, OutputStream outputStream, boolean closeStreams) throws IOException {
		boolean result = false;
		if(input != null && outputStream != null) {
			try {
				outputStream.write(input);
				/* flush output streams. */
				outputStream.flush();
				result = true;
			} catch(IOException ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				/* close streams. */
				if(closeStreams) {
					close(outputStream);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Writes the file contents to the specified output stream and closes the
	 * streams if allowed.
	 * 
	 * @param file
	 * @param outputStream
	 * @param closeStreams
	 * @param compress
	 * @throws IOException
	 */
	public static void write(File file, OutputStream outputStream, boolean closeStreams) throws IOException {
		write(new FileInputStream(file), outputStream, closeStreams);
	}
	
	/**
	 * Saves the input stream contents into the specified file path.
	 * 
	 * @param inputStream
	 * @param filePath
	 */
	public static void saveFile(InputStream inputStream, String filePath) {
		if(inputStream != null && !StringHelper.isNullOrEmpty(filePath)) {
			OutputStream outputStream = null;
			try {
				File file = new File(filePath);
				/* create the file if it does not exist. */
				if(!file.exists()) {
					file.createNewFile();
				}
				outputStream = new BufferedOutputStream(new FileOutputStream(file));
				int fileSize = write(inputStream, outputStream, false);
				System.out.println("File [" + filePath + "] saved successfully! fileSize:" + fileSize);
			} catch(Exception ex) {
				System.err.println(ex);
			} finally {
				close(inputStream);
				close(outputStream);
			}
		}
	}
	
	/**
	 * Saves the input into the specified file.
	 * 
	 * @param input
	 * @param file
	 */
	public static void saveFile(byte[] input, File file) throws IOException {
		if(file != null) {
			/* create the file if it does not exist. */
			if(!file.exists()) {
				file.createNewFile();
			}
			write(input, new FileOutputStream(file), true);
		}
	}
	
	/**
	 * Returns true if the fileName extension ends any of the specified
	 * extensions otherwise false.
	 * 
	 * @param fileName
	 * @param extensions
	 * @return
	 */
	public static boolean endsWith(String fileName, String... extensions) {
		boolean result = false;
		if(!StringHelper.isNullOrEmpty(fileName) && !ObjectHelper.isNullOrEmpty(extensions)) {
			for(String ext : extensions) {
				if(fileName.endsWith(ext)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param fileChannel
	 */
	public static void close(FileChannel fileChannel) {
		try {
			if(fileChannel != null) {
				fileChannel.close();
				fileChannel = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * 
	 * @param writer
	 */
	public static void close(Writer writer) {
		try {
			if(writer != null) {
				writer.close();
				writer = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * 
	 * @param outputStream
	 */
	public static void close(OutputStream outputStream) {
		try {
			if(outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * 
	 * @param reader
	 */
	public static void close(Reader reader) {
		try {
			if(reader != null) {
				reader.close();
				reader = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * Closes the given input stream
	 * 
	 * @param inputStream
	 */
	public static void close(InputStream inputStream) {
		try {
			if(inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * 
	 * @param reader
	 */
	public static void close(RandomAccessFile randomAccessFile) {
		try {
			if(randomAccessFile != null) {
				randomAccessFile.close();
				randomAccessFile = null;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	/**
	 * Creates the directory if not exists. If <code>override</code> is set to
	 * true, delete the existing directory and creates the new one.
	 * 
	 * @param dirPath
	 */
	public static File makeDirectory(String dirPath, boolean override) {
		File temp = null;
		if(!StringHelper.isNullOrEmpty(dirPath)) {
			temp = new File(dirPath);
			if(temp.exists()) {
				System.out.println("Directory '" + temp.getAbsolutePath() + "' already exists.");
				if(temp.isDirectory() && override) {
					if(!delete(temp, override)) {
						System.out.println("Unable to delete '" + temp.getAbsolutePath() + "' directory.");
					}
					
					if(!temp.mkdirs()) {
						System.out.println("Unable to create '" + temp.getAbsolutePath() + "' directory.");
					}
				}
			} else {
				if(!temp.mkdirs()) {
					System.out.println("Unable to create '" + temp.getAbsolutePath() + "' directory.");
				}
			}
		}
		
		return temp;
	}
	
	/**
	 * Creates the directory if not exists. If <code>override</code> is set to
	 * true, delete the existing directory and creates the new one.
	 * 
	 * @param dirPath
	 */
	public static File makeDirectory(String dirPath) {
		return makeDirectory(dirPath, false);
	}
	
	/**
	 * Deletes the contents of the specified <code>path</code> irrespective of
	 * its contents, if its not null or empty.
	 * 
	 * @param path
	 * @param force
	 */
	public static boolean delete(File path, boolean force) {
		boolean deleted = false;
		if(path != null && path.exists()) {
			if(path.isDirectory()) {
				/* check if any file exist in directory. */
				File[] files = path.listFiles();
				if(files != null) {
					/* if exist, then delete all files. */
					for(File file : files) {
						if(file.isDirectory()) {
							/* call recursive to delete folders. */
							deleted = delete(file, force);
						} else if(file.exists()) {
							/* delete file. */
							deleted = file.delete();
						}
					}
				}
				
				/* finally delete the folder. */
				deleted = path.delete();
			} else if(path.isFile()) {
				deleted = path.delete();
			}
		}
		
		return deleted;
	}
	
	/**
	 * Deletes the contents of the specified <code>path</code> irrespective of
	 * its contents, if its not null or empty.
	 * 
	 * @param path
	 * @param force
	 */
	public static boolean delete(String path, boolean force) {
		/* check the file path is not null or not empty. */
		if(!StringHelper.isNullOrEmpty(path)) {
			return delete(new File(path), force);
		}
		
		return false;
	}
	
	/**
	 * Deletes the contents of the specified <code>path</code> irrespective of
	 * its contents, if its not null or empty.
	 * 
	 * @param path
	 */
	public static boolean delete(String path) {
		return delete(path, false);
	}
	
	/**
	 * Returns the bytes of the specified input stream.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream inputStream, boolean closeStream) throws IOException {
		byte[] bytes = null;
		if(inputStream != null) {
			ByteArrayOutputStream outputStream = null;
			BufferedInputStream bInputStream = new BufferedInputStream(inputStream);
			try {
				bInputStream = new BufferedInputStream(inputStream);
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = getBuffer(bInputStream.available(), BUFFER_16K);
				int length = 0;
				while((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
				
				outputStream.flush();
				bytes = outputStream.toByteArray();
			} catch(IOException ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				/* close streams. */
				close(outputStream);
				if(closeStream) {
					close(bInputStream);
				}
			}
		}
		
		return bytes;
	}
	
	/**
	 * Returns the bytes of the specified input stream.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		return readBytes(inputStream, false);
	}
	
	/**
	 * This method writes the specified string into the specified output stream.
	 * 
	 * @param outputStream
	 * @param parameters
	 * @throws IOException
	 */
	public static void writeStringToOutputStream(OutputStream outputStream, String parameters) throws IOException {
		if(outputStream != null) {
			DataOutputStream dataOutputStream = null;
			try {
				dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeBytes(parameters);
				dataOutputStream.flush();
			} catch(IOException ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				close(dataOutputStream);
			}
		}
	}
	
	/**
	 * Converts the bytes to StringBuilder object.
	 * 
	 * @param bytes
	 * @return StringBuilder
	 */
	public static StringBuilder getBytesAsStringBuilder(byte... bytes) {
		StringBuilder sBuilder = new StringBuilder();
		if(bytes != null) {
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
				String inputLine = null;
				while((inputLine = bufferedReader.readLine()) != null) {
					sBuilder.append(inputLine);
				}
				
				close(bufferedReader);
			} catch(IOException ex) {
				System.err.println(ex);
			} finally {
				close(bufferedReader);
			}
		}
		
		return sBuilder;
	}
	
	/**
	 * Reads the specified file bytes.
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] readFileBytes(String fileName) throws IOException {
		return readBytes(new FileInputStream(fileName), true);
	}
	
	/**
	 * Copies the source file at the target file.
	 * 
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(String sourceFilePath, String targetFilePath) throws IOException {
		boolean copied = false;
		if(!StringHelper.isNullOrEmpty(sourceFilePath) && !StringHelper.isNullOrEmpty(targetFilePath)) {
			File srcFile = new File(sourceFilePath);
			if(srcFile.exists()) {
				int fileSize = write(new FileInputStream(srcFile), new FileOutputStream(targetFilePath), true);
				if(fileSize > 0) {
					copied = true;
					System.out.println("File [" + sourceFilePath + "] copied successfully! fileSize:" + fileSize);
				}
			} else {
				System.out.println("File [" + sourceFilePath + "] does not exist!");
			}
		}
		
		return copied;
	}
	
	/**
	 * 
	 * @param outputStream
	 * @param object
	 * @param compress
	 * @throws Exception
	 */
	public static void writeObject(OutputStream outputStream, Object object, boolean compress) throws IOException {
		if(outputStream != null) {
			ObjectOutputStream objOutputStream = null;
			try {
				objOutputStream = new ObjectOutputStream(outputStream);
				if(compress) {
					/* avoid resources leak. */
					objOutputStream.close();
					outputStream = new ObjectOutputStream(new GZIPOutputStream(outputStream));
				}
				
				objOutputStream.writeObject(object);
				objOutputStream.flush();
			} catch(IOException ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				close(objOutputStream);
			}
		}
	}
	
	/**
	 * 
	 * @param outputStream
	 * @param object
	 * @param compress
	 * @throws Exception
	 */
	public static void writeObject(OutputStream outputStream, Object object) throws IOException {
		writeObject(outputStream, object, false);
	}
	
	/**
	 * 
	 * @param inputStream
	 * @param compress
	 * @return
	 * @throws Exception
	 */
	public static Object readObject(InputStream inputStream, boolean compress) throws Exception {
		Object object = null;
		if(inputStream != null) {
			ObjectInputStream objInputStream = null;
			try {
				objInputStream = new ObjectInputStream(inputStream);
				if(compress) {
					/* avoid resources leak. */
					objInputStream.close();
					objInputStream = new ObjectInputStream(new GZIPInputStream(inputStream));
				}
				
				object = objInputStream.readObject();
			} catch(Exception ex) {
				System.err.println(ex);
				throw ex;
			} finally {
				close(objInputStream);
			}
		}
		
		return object;
	}
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static Object readObject(InputStream inputStream) throws Exception {
		return readObject(inputStream, false);
	}
	
	/**
	 * Writes the input stream data into the specified response string.
	 * 
	 * @param inputStream
	 * @param response
	 * @param closeConnection
	 * @throws IOException
	 */
	public static StringBuilder streamAsStringBuilder(InputStream inputStream, boolean closeStreams) throws IOException {
		StringBuilder streamString = new StringBuilder();
		if(inputStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine = null;
			while((inputLine = bufferedReader.readLine()) != null) {
				streamString.append(inputLine);
			}
			
			if(closeStreams) {
				close(bufferedReader);
			}
		}
		
		return streamString;
	}
	
	/**
	 * Writes the input stream data into the specified response string.
	 * 
	 * @param inputStream
	 * @param response
	 * @param closeConnection
	 * @throws IOException
	 */
	public static StringBuilder writeResponse(InputStream inputStream, boolean closeStreams, boolean useExistingFile, String hashCodeFilePath) throws IOException {
		StringBuilder response = new StringBuilder();
		if(inputStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine = null;
			while((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			
			if(closeStreams) {
				close(bufferedReader);
			}
		}
		
		return response;
	}
	
	/**
	 * Returns the validated file or folder/directory name as per the file
	 * management naming conventions.
	 * 
	 * @param fileOrFolderName
	 * @return
	 */
	public static String validateFileOrFolderName(String fileOrFolderName) {
		if(!StringHelper.isNullOrEmpty(fileOrFolderName)) {
			fileOrFolderName = fileOrFolderName.replace("<", "_");
			fileOrFolderName = fileOrFolderName.replace(">", "_");
			fileOrFolderName = fileOrFolderName.replace(":", "_");
			fileOrFolderName = fileOrFolderName.replace("\"", "_");
			fileOrFolderName = fileOrFolderName.replace("\\", "_");
			fileOrFolderName = fileOrFolderName.replace("/", "_");
			fileOrFolderName = fileOrFolderName.replace("|", "_");
			fileOrFolderName = fileOrFolderName.replace("?", "_");
			fileOrFolderName = fileOrFolderName.replace("*", "_");
		}
		
		return fileOrFolderName;
	}
	
	// /**
	// * Checks whether the directory has proper read/write permissions or not.
	// *
	// * @param installDirPath
	// * @return
	// */
	// public static boolean hasReadWritePrivileges(String installDirPath) {
	// Debug.d(DEBUG_KEY, "+hasReadWritePrivileges(" + installDirPath + ")");
	// boolean allowed = false;
	// File installDir = new File(installDirPath);
	// if (installDir.exists() && installDir.isDirectory()) {
	// try {
	// File lockFile = File.createTempFile(".lock", null, installDir);
	// allowed = lockFile.delete();
	// } catch (IOException ex) {
	// Debug.e(DEBUG_KEY, ex);
	// }
	// }
	//
	// Debug.d(DEBUG_KEY, "-hasReadWritePrivileges(), allowed:" + allowed);
	// return allowed;
	// }
	
	/**
	 * Returns the proxy file path.
	 * 
	 * @return
	 */
	public static String getProxyFilePath() {
		return (System.getProperty("user.home") + File.separator + "Proxy.cfg");
	}
	
	/**
	 * 
	 * @param proxyHost
	 * @param proxyPort
	 */
	public static void writeProxyFile(String proxyHost, int proxyPort) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileWriter(getProxyFilePath()));
			printWriter.println(proxyHost);
			printWriter.println(proxyPort);
			printWriter.flush();
		} catch(Exception ex) {
			System.out.println("Error writing proxy file to session dir.");
			ex.printStackTrace();
		} finally {
			close(printWriter);
		}
	}
	
	/**
	 * 
	 */
	public static void removeProxyFile() {
		File proxyFile = new File(getProxyFilePath());
		if(proxyFile.exists()) {
			proxyFile.delete();
		}
	}
	
	/**
	 * 
	 */
	public static void detectProxy() {
		System.out.println("http.proxyHost:" + System.getProperty("http.proxyHost"));
		System.out.println("http.proxyPort:" + System.getProperty("http.proxyPort"));
		
		System.out.println("https.proxyHost:" + System.getProperty("https.proxyHost"));
		System.out.println("https.proxyPort:" + System.getProperty("https.proxyPort"));
		
		try {
			System.setProperty("java.net.useSystemProxies", "true");
			List<Proxy> proxies = ProxySelector.getDefault().select(new URI(DEFAULT_URL));
			for(Proxy proxy : proxies) {
				System.out.println("proxy:" + proxy + ", proxy.type:" + proxy.type());
				InetSocketAddress socketAddress = (InetSocketAddress) proxy.address();
				if(socketAddress == null) {
					System.out.println("No Proxy");
					removeProxyFile();
				} else {
					System.out.println("proxy hostname:" + socketAddress.getHostName() + ", port:" + socketAddress.getPort());
					System.setProperty("http.proxyHost", socketAddress.getHostName());
					System.setProperty("http.proxyPort", Integer.toString(socketAddress.getPort()));
					writeProxyFile(socketAddress.getHostName(), socketAddress.getPort());
					break;
				}
			}
			
			System.out.println("http.proxyHost:" + System.getProperty("http.proxyHost"));
			System.out.println("http.proxyPort:" + System.getProperty("http.proxyPort"));
			System.clearProperty("http.proxyPort");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}