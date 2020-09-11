package com.playground;

import java.time.LocalTime;

public class Plaground {
	public static void getCurrentTimeUsingCalendar() {

		String time = "08:20:45";
		LocalTime today = LocalTime.parse(time);
		System.out.println("3 " + today);
	}

	public static void main(String[] args) {
		getCurrentTimeUsingCalendar();
	}
}
