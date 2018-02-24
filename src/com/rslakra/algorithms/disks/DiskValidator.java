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
package com.rslakra.algorithms.disks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rslakra.utils.CoreHelper;
import com.rslakra.utils.IOHelper;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 02/02/2018 01:35:55 PM
 */
public class DiskValidator {

	/**
	 * Returns the <code>List<Disk></code> object created after reading the
	 * given fileName.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Disk> readDisks(String fileName) {
		List<Disk> disks = null;
		if (CoreHelper.isNotNullOrEmpty(fileName)) {
			String filePath = IOHelper.filePath(DiskValidator.class);
			filePath += File.separator + fileName;
			System.out.println(filePath);
			try (Stream<String> stream = Files.lines(Paths.get(filePath));) {
				disks = stream.map(line -> {
					Disk disk = null;
					String[] tokens = line.split("\\s+");
					if (tokens != null) {
						disk = new Disk(tokens[0], Integer.parseInt(tokens[1]));
					}

					return disk;
				}).collect(Collectors.<Disk>toList());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return disks;
	}

	/**
	 * Returns the <code>List<DiskFile></code> object created after reading the
	 * given fileName.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<DiskFile> readDiskFiles(String fileName) {
		List<DiskFile> diskFiles = null;
		if (CoreHelper.isNotNullOrEmpty(fileName)) {
			String filePath = IOHelper.filePath(DiskValidator.class);
			filePath += File.separator + fileName;
			System.out.println(filePath);
			try (Stream<String> stream = Files.lines(Paths.get(filePath));) {
				diskFiles = stream.map(line -> {
					DiskFile diskFile = null;
					String[] tokens = line.split("\\s+");
					if (tokens != null) {
						diskFile = new DiskFile(tokens[0], Integer.parseInt(tokens[1]));
					}

					return diskFile;
				}).collect(Collectors.<DiskFile>toList());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return diskFiles;
	}

	/**
	 * 
	 * @param disks
	 * @param diskFiles
	 * @return
	 */
	public boolean hasEnoughSpace(List<Disk> disks, List<DiskFile> diskFiles) {
		if (disks != null && diskFiles != null) {
			Collectors.reducing(0, DiskFile::getSize, Integer::sum);
			diskFiles.stream().map(DiskFile::getSize).collect(Collectors.toSet());
			// (disk, diskFile) -> disk.getSize() >=
			// diskFiles.stream().map(mapper);
			// disks.stream().spliterator().tryAdvance(action);
		}

		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Disk> disks = readDisks("Disks.txt");
		disks.stream().forEach(System.out::println);

		DiskValidator diskValidator = new DiskValidator();
		System.out.println();
		List<DiskFile> diskFiles = readDiskFiles("DiskFiles.txt");
		diskFiles.stream().forEach(System.out::println);
		boolean result = diskValidator.hasEnoughSpace(disks, diskFiles);
		System.out.println(result);

		System.out.println();
		List<DiskFile> diskFiles2 = readDiskFiles("DiskFiles2.txt");
		diskFiles2.stream().forEach(System.out::println);
		result = diskValidator.hasEnoughSpace(disks, diskFiles2);
		System.out.println(result);
	}

}
