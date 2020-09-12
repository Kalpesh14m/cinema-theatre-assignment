package com.kalpesh.cinematheatre.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

}