package com.capgemini.salarydate.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.capgemini.salarydate.SalaryDate;

class SalaryDateTest {

	@Test
	void testSalaryDate() {
		assertEquals(LocalDate.of(2018, 10, 31), SalaryDate.salaryDateGenerator());
	}

	@Test
	void testSalaryDateForGivenMonth() {
		assertEquals(LocalDate.of(2018, 9, 28), SalaryDate.salaryDateGeneratorForGivenMonth(2018, 9));
	}

}
