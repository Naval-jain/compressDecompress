package com.naval.core;

import java.io.Serializable;

public class Node implements Serializable {


	private static final long serialVersionUID = 1451784124266938907L;
	
	String fileName;
	byte[] bytes;
	String directoryStructure;

	public Node(String fileName, byte[] bytes, String directoryStructure) {
		this.fileName = fileName;
		this.bytes = bytes;
		this.directoryStructure = directoryStructure;
	}
}
