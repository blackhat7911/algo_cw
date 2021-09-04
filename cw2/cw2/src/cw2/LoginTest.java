package cw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	void test() {
		Login login = new Login();
		boolean actualOutput = login.loginUser("test", "test");
		assertEquals(true, actualOutput);
	}

}
