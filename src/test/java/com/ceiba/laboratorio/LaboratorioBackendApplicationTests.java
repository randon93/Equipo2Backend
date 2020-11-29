package com.ceiba.laboratorio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LaboratorioBackendApplicationTests {

	@Autowired
	PasswordEncoder encoder;

	@Test
	void contextLoads() {
		System.out.println(encoder.encode("admin"));
	}

}
