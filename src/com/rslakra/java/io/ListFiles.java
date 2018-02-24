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
package com.rslakra.java.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Date;

/**
 * @author rohtash.singh
 * Created on Jul 8, 2005
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ListFiles {

	public static void main(String[] args) {
		ListFiles lf = new ListFiles();
		lf.init();
	}

	private void init() {
		MessageLoggingTimer msgLogTimer = new MessageLoggingTimer();
		msgLogTimer.start();
	}

	class MessageLoggingTimer extends Thread {
		private static final String PATH = "./*.log";

		private static final String TAG_MESSAGE_LOG_INTERVAL = "MessageLogInterval";
		private static final int DEFAULT_LOG_INTERVAL = 10000; // 10 Seconds
		private static final String LOG_DIR = "avrlog";
		int interval;
		String path;

		public MessageLoggingTimer() {
			interval = DEFAULT_LOG_INTERVAL;
			path = PATH;
		}

		public void run() {
			Date start = new Date();

			try {
				System.err.println("Sleep");
				sleep(1000 * 60 * 2);
				System.err.println("Wake Up");
			} catch (InterruptedException ex) {
				System.err.println("Exception while sleeping : " + ex);
			}

			File file = new File(getPath());
			System.err.println("file : " + file);
			logFiles(file, start);

			try {
				sleep(getInterval());
			} catch (InterruptedException ex) {
				System.err.println("Exception while sleeping : " + ex);
			}
		} // End of Run

		private void logFiles(File file, Date startTime) {

			if (file != null) {
				System.out.println("Logging : " + file.getAbsolutePath());

				File[] filesList;

				if (file.isDirectory()) {
					filesList = file.listFiles(new LogFileFilter(startTime));
					System.out.println("filesList.length : " + filesList.length);

					sortOnTimeStamp(filesList);

					for (int i = 0; i < filesList.length; i++) {
						File temp = filesList[i];

						if (checkTimeStamp(temp, startTime)) {
							System.out.println(temp + " : " + temp.lastModified() + " : " + startTime.getTime());

							if (temp.isDirectory()) {
								logFiles(temp, startTime);
							} else {
								logFile(temp);
							}

							// Delete file after logging
							System.out.println("Deleting : " + temp.getAbsolutePath());
							temp.delete();
						}
					}
				} //
			}
		}

		private void sortOnTimeStamp(File[] filesList) {

			for (int i = 0; i < filesList.length; i++) {

				for (int j = i + 1; j < filesList.length; j++) {

					if (filesList[i].lastModified() > filesList[j].lastModified()) {
						File tempFile = filesList[j];
						filesList[j] = filesList[i];
						filesList[i] = tempFile;
					}
				}
			}
		}

		private boolean checkTimeStamp(File file, Date start) {
			return file.lastModified() <= start.getTime();
		}

		private void logFile(File file) {
			LineNumberReader reader = null;

			try {
				reader = new LineNumberReader(new FileReader(file));

				String line;

				while ((line = reader.readLine()) != null) {
					System.out.println("line : " + line);

					String[] messages = line.split(",");
					System.out.println("messages.length : " + messages.length);

					String eventId = (messages[0] != null) ? messages[0] : null;
					System.err.println("eventId : " + eventId);

					String eventType = (messages[1] != null) ? messages[1] : null;
					System.err.println("eventType : " + eventType);

					String comment = (messages[5] != null) ? messages[5] : null;
					System.err.println("comment : " + comment);
				}

				// Close File.
				reader.close();
			} catch (FileNotFoundException ex) {
				System.out.println("No File Found with name : " + file);
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println("Error while reading File : " + file);
				ex.printStackTrace();
			}
		} // End of logFile method

		/**
		 * Getter/Setters
		 */
		public int getInterval() {
			return interval;
		}

		public String getPath() {
			return path;
		}

	} // End of AVRTimerDispatcher

	/* LogFileFilter filters all files having extension .log. */
	private class LogFileFilter implements FileFilter {
		public static final String DEFAULT_EXT = "log";
		private Date runTime = null;

		public LogFileFilter(Date runTime) {
			this.runTime = runTime;
		}

		public boolean accept(File f) {

			if (f.lastModified() < runTime.getTime()) {

				if (f.isDirectory()) {
					return true;
				}

				String extension = getExtension(f);

				if ((extension != null) && extension.equals(DEFAULT_EXT)) {
					return true;
				}
			}

			return false;
		}

		// The description of this filter
		public String getDescription() {
			return "Log Files (*.log)";
		}

		private String getExtension(File file) {
			String ext = null;

			if (file != null) {
				String fileName = file.getName();
				int index = fileName.lastIndexOf(".");

				if ((index > 0) && (index < (fileName.length() - 1))) {
					ext = fileName.substring(index + 1).toLowerCase();
				}
			}

			return ext;
		}
	} // End of LogFileFilter
}