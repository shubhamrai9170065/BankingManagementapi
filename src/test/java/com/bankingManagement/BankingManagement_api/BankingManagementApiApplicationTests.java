package com.bankingManagement.BankingManagement_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BankingManagementApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
