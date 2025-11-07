package com.bankingManagement.BankingManagement_api;

import org.springframework.boot.SpringApplication;

public class TestBankingManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankingManagementApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
