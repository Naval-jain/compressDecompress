package com.naval.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import org.springframework.stereotype.Component;

@Component
public class FileCompressorDecompressorService {

	public void getFilesDirectory(String inputDir, String directoryStructure, String outputDir) {
		File folder = new File(inputDir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File : " + listOfFiles[i].getName() + " directory Name:" + directoryStructure);
				compressAndWriteTo(listOfFiles[i], directoryStructure, outputDir);
			} else {
				getFilesDirectory(listOfFiles[i].getAbsolutePath(), directoryStructure + "/" + listOfFiles[i].getName(),
						outputDir);
			}
		}
	}

	private void compressAndWriteTo(File file, String directoryStructure, String outputDir) {

		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(file.toPath());

			ByteArrayOutputStream ba = new ByteArrayOutputStream(fileContent.length);
			DeflaterOutputStream dos = new DeflaterOutputStream(ba);

			dos.write(fileContent);
			dos.finish();
			dos.flush();

			Node node = new Node(file.getName(), ba.toByteArray(), directoryStructure);
			FileOutputStream fout = new FileOutputStream(outputDir + "/" + node.fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(node);

			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void uncompressFile(String inputDir, String outputDir) throws IOException, ClassNotFoundException {

		File folder = new File(inputDir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			FileInputStream fin = new FileInputStream(listOfFiles[i]);
			ObjectInputStream oit = new ObjectInputStream(fin);
			Node node = (Node) oit.readObject();
			oit.close();

			byte[] fileContent = node.bytes;
			String directoryStructure = node.directoryStructure;
			if (!directoryStructure.isEmpty())
				makeDirectoryStructure(directoryStructure, outputDir);

			ByteArrayInputStream bai = new ByteArrayInputStream(fileContent);
			FileOutputStream fos = new FileOutputStream(outputDir + directoryStructure + "/" + node.fileName);
			InflaterInputStream iis = new InflaterInputStream(bai);
			int data;
			while ((data = iis.read()) != -1) {
				fos.write(data);
			}

			fos.close();
			iis.close();
		}
	}

	private void makeDirectoryStructure(String directoryStructure, String outputDir) {

		File theDir = new File(outputDir + directoryStructure);

		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;
			try {
				theDir.mkdirs();
				result = true;
			} catch (SecurityException se) {
				System.out.println(se.getMessage());
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

}
