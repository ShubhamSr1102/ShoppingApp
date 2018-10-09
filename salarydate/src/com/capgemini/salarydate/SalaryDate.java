package com.capgemini.salarydate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SalaryDate {

	public static LocalDate salaryDateGenerator() {

		LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		DayOfWeek day = localDate.getDayOfWeek();
		LocalDate lastWorkingday = localDate;
		switch (day) {
		case SATURDAY:
			lastWorkingday = localDate.minusDays(1);
			break;
		case SUNDAY:
			lastWorkingday = localDate.minusDays(2);
			break;
		default:
			break;
		}
		System.out.println(lastWorkingday);
		return lastWorkingday;
	}

	public static LocalDate salaryDateGeneratorForGivenMonth(int year, int month) {

		LocalDate localDate = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());
		DayOfWeek day = localDate.getDayOfWeek();
		LocalDate lastWorkingday = localDate;
		switch (day) {
		case SATURDAY:
			lastWorkingday = localDate.minusDays(1);
			break;
		case SUNDAY:
			lastWorkingday = localDate.minusDays(2);
			break;
		default:
			break;
		}
		System.out.println(lastWorkingday);
		return lastWorkingday;
	}
}
