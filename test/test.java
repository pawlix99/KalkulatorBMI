import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AppTest {

	@Test
	void isIntShouldBeTrueTest() {
		App number = new App();
		boolean output = number.isInt("1"); 
		assertEquals(true, output);
	}
	
	
	@Test
	void isIntShouldBeFalseTest() {
		App number = new App();
		boolean output = number.isInt("1.1"); 
		assertEquals(false, output);
	}
	
	@Test
	void isNumberPositiveShouldBeTrueTest() {
		App number = new App();
		boolean output = number.isNumberPositive(1); 
		assertEquals(true, output);
	}

	@Test
	void isNumberPositiveShouldBeFalseTest() {
		App number = new App();
		boolean output = number.isNumberPositive(-1); 
		assertEquals(false, output);
	}
	
	@Test
	void bmiTest() {
		App number = new App();
		double output = number.BMI(75, 180);
		assertEquals(23.15, output);
	}

	@Test
	void bmrWomanTest() {
		App number = new App();
		double output = number.BMRwoman(50, 160, 20);
		assertEquals(1336, output);
	}
	
	@Test
	void bmrManTest() {
		App number = new App();
		double output = number.BMRman(75, 180, 20);
		assertEquals(1859, output);
	}

}


