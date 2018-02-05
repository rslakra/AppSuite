package com.rslakra.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
public class FileUtil {
	
	public static final int BUFFER_1K = 1024; // 1K
	public static final int BUFFER_4K = 4 * BUFFER_1K; // 4K
	public static final int BUFFER_8K = 8 * BUFFER_1K; // 8K
	public static final int BUFFER_20K = 20 * BUFFER_1K; // 20K
	
	/** imageTypes */
	private static List<String> imageTypes;
	
	/**
	 * Returns the full path for the directory and childName.
	 * 
	 * @param parentFolder
	 * @param childName
	 * @return
	 */
	public static String pathString(String parentFolder, String childName) {
		String pathString = null;
		if(StringUtils.isNullOrEmpty(parentFolder)) {
			throw new IllegalArgumentException("Parent directory should not be null/empty!");
		} else {
			/* Removes unnecessary spaces from parentFolder and fileName. */
			pathString = parentFolder.trim();
			if(!StringUtils.isNullOrEmpty(childName)) {
				pathString += (childName.startsWith("/") ? "" : File.separator) + childName.trim();
			}
		}
		
		return pathString;
	}
	
	/**
	 * Returns the path of the given file, if its not null otherwise null.
	 * 
	 * @param file
	 * @return
	 */
	public static String getFilePath(File file) {
		return (file == null ? null : file.getAbsolutePath());
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
		if(StringUtils.isNullOrEmpty(path)) {
			throw new NullPointerException("Path must be provided!");
		}
		
		if(ObjectUtils.isNullOrEmpty(data)) {
			throw new NullPointerException("data must be provided!");
		}
		
		try {
			result = saveFile(data, path);
		} catch(IOException ex) {
			LogUtil.error(ex);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		return delete(path);
	}
	
	/**
	 * 
	 * @param folderPath
	 * @return
	 */
	public static boolean deleteFolder(String folderPath) {
		return delete(folderPath, true);
	}
	
	/**
	 * 
	 * @param fileOrFolder
	 * @return
	 */
	public static boolean deleteFolder(File fileOrFolder) {
		return delete(fileOrFolder, true);
	}
	
	/**
	 * Returns the bytes of the specified file, if exists, otherwise null.
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] readFile(File file) {
		byte[] bytes = null;
		if(ObjectUtils.isNotNull(file)) {
			// Read file into buffer
			RandomAccessFile randomAccessFile = null;
			try {
				randomAccessFile = new RandomAccessFile(file, "r");
				long length = randomAccessFile.length();
				bytes = new byte[(int) length];
				randomAccessFile.readFully(bytes);
			} catch(IOException ex) {
				LogUtil.error(ex);
			} finally {
				close(randomAccessFile);
			}
		}
		
		return bytes;
	}
	
	/**
	 * Returns the bytes of the specified path, if exists, otherwise null.
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] readFile(String path) {
		return (StringUtils.isNullOrEmpty(path) ? null : readFile(new File(path)));
	}
	
	/**
	 * Returns true if the given extension exists in the list otherwise false.
	 * 
	 * @param extension
	 * @return
	 */
	public static boolean isImageType(String extension) {
		// populate with supported images types.
		if(ObjectUtils.isNull(imageTypes)) {
			imageTypes = new ArrayList<String>();
			imageTypes.add("tif");
			imageTypes.add("tiff");
			imageTypes.add("jpg");
			imageTypes.add("jpeg");
			imageTypes.add("png");
			imageTypes.add("pcd");
			imageTypes.add("psd");
			imageTypes.add("tga");
			imageTypes.add("dcx");
			imageTypes.add("emf");
			imageTypes.add("wmf");
			imageTypes.add("gif");
			imageTypes.add("bmp");
			imageTypes.add("ico");
			imageTypes.add("svg");
			imageTypes.add("cur");
		}
		
		if(extension.startsWith(".") && extension.length() > 1) {
			extension = extension.substring(1);
		}
		
		return imageTypes.contains(extension);
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
			LogUtil.error(ex);
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
			LogUtil.error(ex);
		}
		
		return properties;
	}
	
	/**
	 * Returns the Properties object prepared from the given dataBytes.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static Properties createProperties(byte[] dataBytes) {
		return loadProperties(new ByteArrayInputStream(dataBytes));
	}
	
	/**
	 * Saves the specified properties into the specified file path.
	 * 
	 * @param filePath
	 * @param properties
	 */
	public static void saveProperties(String filePath, Properties properties) {
		if(!StringUtils.isNullOrEmpty(filePath)) {
			FileOutputStream outputStream = null;
			try {
				File file = new File(filePath);
				if(!file.exists()) {
					boolean fileCreated = file.createNewFile();
					if(!fileCreated) {
						LogUtil.debug("Unable to create the properties file:" + filePath);
					}
				}
				outputStream = new FileOutputStream(file);
				properties.store(outputStream, null);
				outputStream.flush();
				LogUtil.debug("Properties saved at:" + filePath);
			} catch(FileNotFoundException ex) {
				LogUtil.error(ex);
			} catch(IOException ex) {
				LogUtil.error(ex);
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
				LogUtil.debug(key + ": " + value);
			}
		}
	}
	
	/**
	 * Merges the given sourceProperties into the given targetProperties.
	 * 
	 * @param sourceProperties
	 * @param targetProperties
	 * @return
	 */
	public static Properties mergeProperties(Properties sourceProperties, Properties targetProperties) {
		if(ObjectUtils.isNull(targetProperties)) {
			targetProperties = new Properties();
		}
		
		// if not null, merge it into target.
		if(ObjectUtils.isNotNull(sourceProperties)) {
			targetProperties.putAll(sourceProperties);
		}
		
		return targetProperties;
	}
	
	/**
	 * Returns true if the specified file exists, otherwise false.
	 * 
	 * @param properties
	 */
	public static boolean isExist(String filePath) {
		return (!StringUtils.isNullOrEmpty(filePath) && new File(filePath).exists());
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
	 * Returns true if the specified file is a directory, otherwise false.
	 * 
	 * @param properties
	 */
	public static boolean isDirectory(File file) {
		return (file != null && file.isDirectory());
	}
	
	/**
	 * Returns true if the specified file exists and is a directory, otherwise
	 * false.
	 * 
	 * @param properties
	 */
	public static boolean isExistAndFolder(File file) {
		return (isExist(file) && file.isDirectory());
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
		return getBuffer(available, BUFFER_20K);
	}
	
	/**
	 * Copies the contents of an <code>sourceStream</code> into an
	 * <code>targetStream</code>.
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @param closeStreams
	 * @return
	 * @throws IOException
	 */
	public static int copyStream(InputStream sourceStream, OutputStream targetStream, boolean closeStreams) throws IOException {
		int fileSize = 0;
		if(sourceStream != null && targetStream != null) {
			try {
				byte[] buffer = getBuffer(sourceStream.available());
				
				int byteCount = 0;
				while((byteCount = sourceStream.read(buffer)) != -1) {
					targetStream.write(buffer, 0, byteCount);
					fileSize += byteCount;
				}
				
				/* flush output streams. */
				targetStream.flush();
			} catch(IOException ex) {
				LogUtil.error(ex);
				throw ex;
			} finally {
				/* close streams. */
				if(closeStreams) {
					close(sourceStream);
					close(targetStream);
				}
			}
		}
		
		return fileSize;
	}
	
	/**
	 * Writes the <code>bytes</code> to <code>outputStream</code> and closes it.
	 * 
	 * @param dataBytes
	 * @param outputStream
	 * @param closeStream
	 * @throws IOException
	 */
	public static boolean writeBytes(byte[] dataBytes, OutputStream outputStream, boolean closeStream) throws IOException {
		// Debug.d(DEBUG_KEY, "+writeBytes(" + dataBytes + ", " + outputStream +
		// ", " + closeStream + ")");
		boolean result = false;
		if(!ObjectUtils.isNullOrEmpty(dataBytes) && outputStream != null) {
			try {
				outputStream.write(dataBytes);
				/* flush output streams. */
				outputStream.flush();
				result = true;
			} catch(IOException ex) {
				LogUtil.error(ex);
				throw ex;
			} finally {
				/* close streams. */
				if(closeStream) {
					close(outputStream);
				}
			}
		}
		
		// Debug.d(DEBUG_KEY, "-writeBytes(), result:" + result);
		return result;
	}
	
	/**
	 * Writes the <code>file</code> contents to the given
	 * <code>outputStream</code> and flush the results.
	 * 
	 * @param file
	 * @param outputStream
	 * @throws IOException
	 */
	public static void sendLocalFile(File file, OutputStream outputStream) throws IOException {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] buffer = getBuffer(inputStream.available());
			int byteCount = 0;
			while((byteCount = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteCount);
			}
			
			// flush output streams.
			outputStream.flush();
		} catch(IOException ex) {
			LogUtil.error(ex);
			throw ex;
		} finally {
			/* close streams. */
			close(inputStream);
			close(outputStream);
		}
	}
	
	/**
	 * Copies the contents of an <code>sourceStream</code> into an
	 * <code>targetFile</code>.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @param closeStreams
	 * @return
	 * @throws IOException
	 */
	public static int copyFile(FileInputStream sourceFile, FileOutputStream targetFile, boolean closeStreams) throws IOException {
		int fileSize = 0;
		if(sourceFile != null && targetFile != null) {
			try {
				byte[] buffer = getBuffer(sourceFile.available());
				int byteCount = 0;
				while((byteCount = sourceFile.read(buffer)) != -1) {
					targetFile.write(buffer, 0, byteCount);
					fileSize += byteCount;
				}
				
				// flush output streams.
				targetFile.flush();
			} catch(IOException ex) {
				LogUtil.error(ex);
				throw ex;
			} finally {
				/* close streams. */
				if(closeStreams) {
					close(sourceFile);
					close(targetFile);
				}
			}
		}
		
		return fileSize;
	}
	
	/**
	 * Saves the input stream contents into the specified file path.
	 * 
	 * @param inputStream
	 * @param filePath
	 */
	public static void saveFile(InputStream inputStream, String filePath) {
		// Debug.d(DEBUG_KEY, "saveFile(" + inputStream + ", " + filePath +
		// ")");
		if(inputStream != null && !StringUtils.isNullOrEmpty(filePath)) {
			OutputStream outputStream = null;
			int fileSize = 0;
			try {
				File file = new File(filePath);
				/* create the file if it does not exist. */
				if(!file.exists()) {
					file.createNewFile();
				}
				
				// write the contents of the input stream to output stream
				outputStream = new BufferedOutputStream(new FileOutputStream(file));
				byte[] buffer = getBuffer(inputStream.available());
				int byteCount = 0;
				while((byteCount = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, byteCount);
					fileSize += byteCount;
				}
				
				// flush output streams.
				outputStream.flush();
				LogUtil.debug("File [" + filePath + "] saved successfully! fileSize:" + fileSize);
			} catch(Exception ex) {
				LogUtil.error(ex);
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
	 * @return
	 * @throws IOException
	 */
	public static boolean saveFile(byte[] input, File file) throws IOException {
		// Debug.d(DEBUG_KEY, "+saveFile(" + input + ", " + file + ")");
		boolean result = false;
		if(ObjectUtils.isNotNull(file) && !ObjectUtils.isNullOrEmpty(input)) {
			FileOutputStream outputStream = null;
			try {
				LogUtil.debug("Writing file:" + file.getAbsolutePath());
				// make sure the parent directories exists.
				FileUtil.makeDirectory(file.getParentFile());
				
				/* create the file if it does not exist. */
				if(!file.exists()) {
					boolean newFileCreated = file.createNewFile();
					if(!newFileCreated) {
						LogUtil.debug("Unable to create the file:" + file.getAbsolutePath());
					}
				}
				
				outputStream = new FileOutputStream(file);
				outputStream.write(input);
				outputStream.flush();
				result = true;
			} catch(IOException ex) {
				LogUtil.error(ex);
				throw ex;
			} finally {
				close(outputStream);
			}
		}
		
		// Debug.d(DEBUG_KEY, "-saveFile(), result:" + result);
		return result;
	}
	
	/**
	 * Saves the input into the specified file.
	 * 
	 * @param input
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static boolean saveFile(byte[] input, String filePath) throws IOException {
		return saveFile(input, new File(filePath));
	}
	
	/**
	 * Closes the specified fileChannel.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Closes the specified writer.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Closes the specified outputStream.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Closes the specified reader.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Closes the specified inputStream.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Closes the specified randomAccessFile.
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
			LogUtil.error(ex);
		}
	}
	
	/**
	 * Creates the directory if not exists.
	 * 
	 * @param directory
	 */
	public static File makeDirectory(File directory) {
		if(ObjectUtils.isNotNull(directory)) {
			if(!directory.exists()) {
				if(!directory.mkdirs()) {
					LogUtil.debug("Unable to create '" + directory.getAbsolutePath() + "' directory.");
				}
			}
		}
		
		return directory;
	}
	
	/**
	 * Creates the directory if its not exists. If <code>override</code> is set
	 * to true, delete the existing directory and creates the new one.
	 * 
	 * @param dirPath
	 */
	public static File makeDirectory(File directory, boolean override) {
		if(ObjectUtils.isNotNull(directory)) {
			if(directory.exists()) {
				LogUtil.debug("Directory '" + directory.getAbsolutePath() + "' already exists.");
				if(directory.isDirectory() && override) {
					if(!delete(directory, override)) {
						LogUtil.debug("Unable to delete '" + directory.getAbsolutePath() + "' directory.");
					}
					
					// recreate the directory
					directory = makeDirectory(directory);
				}
			} else {
				// create the directory
				directory = makeDirectory(directory);
			}
		}
		
		return directory;
	}
	
	/**
	 * Creates the directory if not exists. If <code>override</code> is set to
	 * true, delete the existing directory and creates the new one.
	 * 
	 * @param dirPath
	 * @param override
	 * @return
	 */
	public static File makeDirectory(String dirPath, boolean override) {
		return makeDirectory(new File(dirPath), override);
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
		if(isExist(path)) {
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
		if(!StringUtils.isNullOrEmpty(path)) {
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
	public static byte[] readBytes(final InputStream inputStream, final boolean closeStream) throws IOException {
		byte[] resultBytes = null;
		if(inputStream != null) {
			ByteArrayOutputStream outputStream = null;
			BufferedInputStream bInputStream = new BufferedInputStream(inputStream);
			try {
				bInputStream = new BufferedInputStream(inputStream);
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = getBuffer(bInputStream.available(), BUFFER_20K);
				int length = 0;
				while((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
				
				outputStream.flush();
				resultBytes = outputStream.toByteArray();
			} catch(IOException ex) {
				LogUtil.error(ex);
				throw ex;
			} finally {
				/* close streams. */
				close(outputStream);
				if(closeStream) {
					close(bInputStream);
				}
			}
		}
		
		return resultBytes;
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
				LogUtil.error(ex);
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
				LogUtil.error(ex);
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
	 * Reads the specified file bytes.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileBytes(File file) throws IOException {
		return readBytes(new FileInputStream(file), true);
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
				LogUtil.error(ex);
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
				LogUtil.error(ex);
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
	 * @param fileName
	 * @return
	 */
	public static String validateFileOrFolderName(String fileName) {
		// populate with supported images types.
		if(!StringUtils.isNullOrEmpty(fileName)) {
			fileName = fileName.replace("<", "_");
			fileName = fileName.replace(">", "_");
			fileName = fileName.replace(":", "_");
			fileName = fileName.replace("\"", "_");
			fileName = fileName.replace("\\", "_");
			fileName = fileName.replace("/", "_");
			fileName = fileName.replace("|", "_");
			fileName = fileName.replace("?", "_");
			fileName = fileName.replace("*", "_");
			fileName = fileName.replace(",", "_");
			fileName = fileName.replace("[", "_");
			fileName = fileName.replace("]", "_");
			fileName = fileName.replace("{", "_");
			fileName = fileName.replace("}", "_");
			fileName = fileName.replace("!", "_");
			fileName = fileName.replace(";", "_");
			fileName = fileName.replace("$", "_");
			fileName = fileName.replace("¢", "_");
			fileName = fileName.replace("®", "_");
			fileName = fileName.replace("™", "_");
		}
		
		return fileName;
	}
	
	/**
	 * Checks whether the directory has proper read/write permissions or not.
	 * 
	 * @param dirPath
	 * @return
	 */
	public static boolean hasReadWritePrivileges(String dirPath) {
		boolean allowed = false;
		File dirFile = new File(dirPath);
		// if not exists, show warning.
		if(!dirFile.exists()) {
			LogUtil.debug("Directory '" + dirPath + "' does not exist.");
		}
		
		// Create 'lock.rsl' file and delete to check read write permission.
		if(dirFile.exists() && dirFile.isDirectory()) {
			try {
				File lockFile = File.createTempFile("lock.rsl", null, dirFile);
				allowed = lockFile.delete();
			} catch(IOException ex) {
				LogUtil.error(ex);
			}
		}
		
		return allowed;
	}
	
	/**
	 * Returns true if the fileName extension ends with any of the given
	 * extensions otherwise false.
	 * 
	 * @param fileName
	 * @param extensions
	 * @return
	 */
	public static boolean endsWith(String fileName, String... extensions) {
		boolean result = false;
		if(!StringUtils.isNullOrEmpty(fileName) && !StringUtils.isNullOrEmpty(extensions)) {
			for(String extension : extensions) {
				if(fileName.endsWith(extension)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the list of all files of the given <code>extensions</code> from
	 * the given <code>directory</code> (and optionally its sub-directories, if
	 * recursive is true). If the given <code>extensions</code> is null or
	 * empty, all files are returned.
	 * 
	 * @param directory
	 * @param extensions
	 * @param recursive
	 * @return
	 */
	public static List<File> listFiles(File directory, String[] extensions, boolean recursive) {
		List<File> listFiles = new ArrayList<File>();
		if(isExistAndFolder(directory)) {
			File[] files = directory.listFiles();
			if(!ObjectUtils.isNullOrEmpty(files)) {
				for(File file : files) {
					if(recursive && isDirectory(file)) {
						listFiles.addAll(listFiles(directory, extensions, recursive));
					} else {
						if(StringUtils.isNullOrEmpty(extensions)) {
							listFiles.add(file);
						} else {
							if(endsWith(file.getName(), extensions)) {
								listFiles.add(file);
							}
						}
					}
				}
			}
		}
		
		return listFiles;
	}
	
	/**
	 * Returns the list of all files of the given <code>extensions</code> from
	 * the given <code>directory</code>. If the given <code>extensions</code> is
	 * null or empty, all files are returned.
	 * 
	 * @param directory
	 * @param extensions
	 * @return
	 */
	public static List<File> listFiles(File directory, String... extensions) {
		return listFiles(directory, extensions, false);
	}
	
	/**
	 * Returns the list of all files of the given <code>extensions</code> from
	 * the given <code>directory</code>. If the given <code>extensions</code> is
	 * null or empty, all files are returned.
	 * 
	 * @param directory
	 * @param extensions
	 * @return
	 */
	public static List<File> listFiles(File directory) {
		return listFiles(directory, null, false);
	}
	
	/**
	 * Returns the list of file names of the given <code>extensions</code> from
	 * the given <code>directory</code> (and optionally its sub-directories, if
	 * recursive is true). If the given <code>extensions</code> is null or
	 * empty, all files are returned.
	 * 
	 * @param directory
	 * @param extensions
	 * @param recursive
	 * @return
	 */
	public static List<String> listFileNames(File directory, String[] extensions, boolean recursive) {
		List<String> listFiles = new ArrayList<String>();
		if(isExistAndFolder(directory)) {
			File[] files = directory.listFiles();
			if(!ObjectUtils.isNullOrEmpty(files)) {
				for(File file : files) {
					if(recursive && isDirectory(file)) {
						listFiles.addAll(listFileNames(directory, extensions, recursive));
					} else {
						if(StringUtils.isNullOrEmpty(extensions)) {
							listFiles.add(file.getName());
						} else {
							if(endsWith(file.getName(), extensions)) {
								listFiles.add(file.getName());
							}
						}
					}
				}
			}
		}
		
		return listFiles;
	}
	
	/**
	 * Returns the list of all file names of the given <code>extensions</code>
	 * from the given <code>directory</code>. If the given
	 * <code>extensions</code> is null or empty, all files are returned.
	 * 
	 * @param directory
	 * @param extensions
	 * @param recursive
	 * @return
	 */
	public static List<String> listFileNames(File directory, String... extensions) {
		return listFileNames(directory, extensions, false);
	}
	
	/**
	 * Returns the list of all files of the given <code>extension</code> from
	 * the given <code>directory</code>. If the given <code>extension</code> is
	 * null or empty, all files are returned.
	 * 
	 * @param directory
	 * @return
	 */
	public static List<String> getAllFiles(File directory) {
		return listFileNames(directory, (String) null);
	}
	
	/**
	 * Returns the list of all files of the given <code>extension</code> from
	 * the given <code>directory</code>. If the given <code>extension</code> is
	 * null or empty, all files are returned.
	 * 
	 * @param directory
	 * @param extension
	 * @return
	 */
	public static List<String> getAllFiles(String directory, String extension) {
		return listFileNames(new File(directory), extension);
	}
	
	/**
	 * Returns the list of all files from the given <code>directory</code>.
	 * 
	 * @param directory
	 * @return
	 */
	public static List<String> getAllFiles(String directory) {
		return getAllFiles(directory, null);
	}
	
	/**
	 * Returns the list of all .ZIP files from the given <code>directory</code>.
	 * 
	 * @param directory
	 * @return
	 */
	public static List<String> getAllZipFiles(File directory) {
		return listFileNames(directory, ".zip".intern());
	}
	
	/**
	 * Returns the list of all .ZIP files from the given <code>directory</code>.
	 * 
	 * @param directory
	 * @return
	 */
	public static List<String> getAllZipFiles(String directory) {
		return getAllZipFiles(new File(directory));
	}
	
	/**
	 * Returns the latest version from the version list. For example: if your
	 * versions list contains the following values:
	 * <code>[1.0, 2.0, 3.0, 1.0.0, 1.1.1, 1.0.0.1, 2.0.1, 1.0.1]</code>, then
	 * it will return the <code>3.0</code> as latest version. If the version
	 * list is null or empty, it returns empty string;
	 * 
	 * @param versions
	 * @return
	 */
	public static String getLatestZipFile(List<String> zipFileVersions) {
		String latestZipFile = "";
		if(!ObjectUtils.isNullOrEmpty(zipFileVersions)) {
			Collections.sort(zipFileVersions, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
			latestZipFile = (zipFileVersions.get(zipFileVersions.size() - 1));
		}
		
		return latestZipFile;
	}
	
	/**
	 * Converts an object into the bytes.
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] toBytes(Object object) {
		byte[] objectBytes = null;
		if(ObjectUtils.isNotNull(object)) {
			ByteArrayOutputStream byteArrayOutputStream = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				byteArrayOutputStream = new ByteArrayOutputStream();
				objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
				objectBytes = byteArrayOutputStream.toByteArray();
			} catch(IOException ex) {
				LogUtil.error(ex);
			} finally {
				close(objectOutputStream);
				close(byteArrayOutputStream);
			}
		}
		
		return objectBytes;
	}
	
	/**
	 * Converts the bytes into an object.
	 * 
	 * @param dataBytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(byte[] dataBytes) {
		T object = null;
		if(ObjectUtils.isNotNull(dataBytes)) {
			ByteArrayInputStream byteArrayInputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				byteArrayInputStream = new ByteArrayInputStream(dataBytes);
				objectInputStream = new ObjectInputStream(byteArrayInputStream);
				object = (T) objectInputStream.readObject();
			} catch(ClassNotFoundException ex) {
				LogUtil.error(ex);
			} catch(IOException ex) {
				LogUtil.error(ex);
			} finally {
				close(byteArrayInputStream);
				close(objectInputStream);
			}
		}
		
		return object;
	}
	
	/**
	 * Converts the bytes into the specified object.
	 * 
	 * @param dataBytes
	 * @param classType
	 * @return
	 */
	public static Object bytesAsObject(byte[] dataBytes) {
		return (Object) toObject(dataBytes);
		
	}
	
	/**
	 * Deletes the files which are older than the specified days.
	 * 
	 * @param dirPath
	 * @param olderThanNDays
	 */
	public static void deleteFilesOlderThanNDays(File dirPath, int olderThanNDays) {
		if(isExistAndFolder(dirPath)) {
			File[] listFiles = dirPath.listFiles();
			long purgeTimeMillis = System.currentTimeMillis() - (olderThanNDays * 24 * 60 * 60 * 1000);
			LogUtil.debug("purgeTimeMillis:" + purgeTimeMillis);
			for(File file : listFiles) {
				try {
					if(file.lastModified() < purgeTimeMillis) {
						if(!file.delete()) {
							LogUtil.debug("Unable to delete file:" + file + "file.lastModified:" + file.lastModified());
						}
					}
				} catch(Exception ex) {
					LogUtil.error("Error deleting file:" + file, ex);
				}
			}
		}
	}
	
	/**
	 * Deletes the files which are older than the specified days.
	 * 
	 * @param dirPath
	 * @param olderThanNDays
	 */
	public static void deleteFilesOlderThanNDays(String dirPath, int olderThanNDays) {
		deleteFilesOlderThanNDays(new File(dirPath), olderThanNDays);
	}
	
	/**
	 * Returns the list of file names of the given <code>prefix</code> from
	 * the given <code>directory</code>. If the given <code>extensions</code> is
	 * null or empty, all files are returned.
	 * 
	 * @param directory
	 * @param prefix
	 * @return
	 */
	public static List<File> listPrefixedFiles(File directory, String prefix) {
		List<File> listFiles = new ArrayList<File>();
		if(isExistAndFolder(directory)) {
			File[] files = directory.listFiles(new RequestHashCodeFileFilter(prefix));
			listFiles.addAll(Arrays.asList(files));
		}
		
		return listFiles;
	}
	
	/**
	 * Returns the fileName which starts with the given prefix from the given
	 * parentFolder.
	 * 
	 * @param parentFolder
	 * @param prefix
	 * @return
	 */
	public static String getPrefixedFilePath(String filePath) {
		String prefixedFilePath = null;
		if(StringUtils.isNotNullOrEmpty(filePath)) {
			File path = new File(filePath);
			String requestHashCode = StringUtils.getFileName(filePath, false);
			List<File> listFiles = FileUtil.listPrefixedFiles(path.getParentFile(), requestHashCode);
			if(!ObjectUtils.isNullOrEmpty(listFiles)) {
				prefixedFilePath = listFiles.get(0).getAbsolutePath();
			}
			path = null;
		}
		
		return prefixedFilePath;
	}
	
}

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Dec 9, 2015 4:42:16 PM
 */
final class RequestHashCodeFileFilter implements FileFilter {
	
	// requestHashCode
	private String requestHashCode;
	
	/**
	 * 
	 * @param requestHashCode
	 */
	public RequestHashCodeFileFilter(String requestHashCode) {
		this.requestHashCode = requestHashCode;
	}
	
	/**
	 * Returns true, if the filename starts with the requestHashCode otherwise
	 * false.
	 * 
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathName) {
		return (requestHashCode != null && pathName != null && pathName.getName().startsWith(requestHashCode));
	}
	
}
