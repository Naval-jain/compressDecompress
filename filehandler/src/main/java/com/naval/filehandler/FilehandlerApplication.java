package com.naval.filehandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.naval")
public class FilehandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FilehandlerApplication.class, args);
	}
}

