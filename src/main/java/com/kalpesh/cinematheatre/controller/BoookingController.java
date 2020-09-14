package com.kalpesh.cinematheatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.dto.BookingDTO;
import com.kalpesh.cinematheatre.response.Response;
import com.kalpesh.cinematheatre.service.BookingService;

@RestController
@RequestMapping(value = "/booking")

public class BoookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping(value = "/{showId}")
	public ResponseEntity<Response> addShow(@RequestBody BookingDTO request, @PathVariable Long showId) {
		String msg = bookingService.bookShow(request, showId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.BOOKING_DETAILS_ADDED + msg, Constant.OK_RESPONSE_CODE));
	}

	@GetMapping("/")
	public List<Booking> getAllBookings(@RequestParam(name = "User Name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "Mobile Number", required = false) Long mobileNumber,
			@RequestParam(name = "Booking Id", required = false) String bookingUniqueId) {
		return bookingService.getBookings(bookingUniqueId, name, email, mobileNumber);
	}

	@PutMapping("/")
	public ResponseEntity<Response> updateBookingInfo(@RequestBody BookingDTO bookingInfo,
			@RequestParam(name = "Unique Booking Id", required = false) String uniqueBookingId,
			@RequestParam(required = false) Long bookingId) {
		if (bookingService.updateBooking(bookingInfo, uniqueBookingId, bookingId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(Constant.BOOKING_DETAILS_UPDATED, Constant.OK_RESPONSE_CODE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(Constant.BOOKING_DETAILS_FAIL_TO_UPDATE, Constant.BAD_REQUEST_RESPONSE_CODE));
	}

	@DeleteMapping("/")
	public ResponseEntity<Response> cancleBooking(@RequestParam String bookingId) {
		if (bookingService.cancleBooking(bookingId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(Constant.BOOKING_DETAILS_DELETED, Constant.OK_RESPONSE_CODE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response(Constant.BOOKING_DETAILS_FAIL_TO_DELETE, Constant.BAD_REQUEST_RESPONSE_CODE));
	}
}
