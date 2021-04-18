import static org.junit.Assert.*;

import org.junit.Test;

public class AutoTest {

	@Test
	public void test() {
		Float salary = new Float(80000F); //оклад
		int days = 10; //кол-во проработанных дней

		Float profit;
		profit = new Float(salary/30*days);
	
	double otvet=((Math.ceil(profit*Math.pow(10, 2)))/Math.pow(10, 2));

	
	if(otvet==6.67) {
		assertTrue(true);
	}else {
		assertTrue(false);
	}
	}

}
