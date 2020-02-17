package com.SS.library.LMS_Admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.SS.library.Utility","com.SS.library.Controller","com.SS.library.Service","com.SS.library.DAO"})
public class LmsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsAdminApplication.class, args);
	}

}
