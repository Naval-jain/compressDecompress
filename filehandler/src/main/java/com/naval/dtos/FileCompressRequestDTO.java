package com.naval.dtos;

public class FileCompressRequestDTO {

	String inputDir;

	String outputDir;

	Integer maxSize;

	public String getInputDir() {
		return inputDir;
	}

	public FileCompressRequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "FileCompressRequestDTO [inputDir=" + inputDir + ", outputDir=" + outputDir + ", maxSize=" + maxSize
				+ "]";
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

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

}
