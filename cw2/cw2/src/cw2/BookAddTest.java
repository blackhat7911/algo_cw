package cw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookAddTest {

	@Test
	void test() {
		Home home = new Home(null, false);
		boolean actualOutput = home.addBook("JUnit", "Java", "2021-04-14", 500, 25);
		assertEquals(true, actualOutput);
	}

}
