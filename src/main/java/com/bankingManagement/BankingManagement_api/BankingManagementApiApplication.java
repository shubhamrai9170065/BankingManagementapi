package com.bankingManagement.BankingManagement_api;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Log4j2
@SpringBootApplication
public class BankingManagementApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingManagementApiApplication.class, args);
        log.info("Application started successfully");
	}
}
