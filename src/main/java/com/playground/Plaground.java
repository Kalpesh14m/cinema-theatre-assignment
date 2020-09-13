package com.playground;

import java.util.UUID;

public class Plaground {
	public static void main(String[] args) {

		// Creating a random UUID (Universally unique identifier).
		String uuid = UUID.randomUUID().toString();

		System.out.println("Random UUID String = " + uuid);
	}
}
