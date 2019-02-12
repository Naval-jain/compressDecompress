package com.naval.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naval.core.FileCompressorDecompressorService;
import com.naval.dtos.FileCompressRequestDTO;

import io.swagger.annotations.ApiOperation;

@RestController
public class FileController {

	@Autowired
	private FileCompressorDecompressorService fileCompressorDecompressorService;

	@ApiOperation(value = "Compress")
	@PostMapping(path = "file/handler:compress", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String compress(@RequestBody FileCompressRequestDTO filecompressorRequest) {

		fileCompressorDecompressorService.getFilesDirectory(filecompressorRequest.getInputDir(), "",
				filecompressorRequest.getOutputDir());
		return "success";
	}

	@ApiOperation(value = "Compress")
	@PostMapping(path = "file/handler:decompress", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String decompress(@RequestBody FileCompressRequestDTO filecompressorRequest) {

		try {
			fileCompressorDecompressorService.uncompressFile(filecompressorRequest.getInputDir(),
					filecompressorRequest.getOutputDir());
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return "success";
	}

}
