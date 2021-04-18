import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import  java.util.Calendar;

import java.util.Date;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String firstdate = new String("11.12.2020");
		String seconddate = new String("12.12.2020");
	
		LocalDate firstdatel = LocalDate.parse(firstdate, formatter);
		LocalDate seconddatel = LocalDate.parse(seconddate, formatter);
		Period period = Period.between(firstdatel, seconddatel);
		long days = ChronoUnit.DAYS.between(firstdatel, seconddatel);
		
		System.out.println(days);
		
		
				
		if(days==1) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}

}

