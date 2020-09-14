package com.kalpesh.cinematheatre.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	private DateUtil() {
	}

	public static String date(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}

	public static String time(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return time.format(formatter);
	}

	public static LocalDate dateToLocalDate(Date date) {
		return LocalDate.parse(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
}