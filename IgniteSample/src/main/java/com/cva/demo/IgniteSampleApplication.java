package com.cva.demo;




import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class IgniteSampleApplication {

	private static final Logger logger = Logger.getLogger(IgniteSampleApplication.class);
	

	public static void main(String[] args) {
		SpringApplication.run(IgniteSampleApplication.class, args);
	}
}
