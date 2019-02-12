package com.naval.dtos;

public class FileDecompressDTO {

	String inputDir;

	String outputDir;

	public FileDecompressDTO() {
		super();
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
}
