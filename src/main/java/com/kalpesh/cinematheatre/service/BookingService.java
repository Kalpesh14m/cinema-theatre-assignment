package com.kalpesh.cinematheatre.service;

import java.util.List;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.dto.BookingDTO;

public interface BookingService {

	public String bookShow(BookingDTO request, Long showId);

	public List<Booking> getBookings(String bookingUniqueId, String name, String email, Long mobileNumber);

	public boolean updateBooking(BookingDTO bookingInfo, String uniqueBookingId, Long bookingId);

	public boolean cancleBooking(String bookingId);

}
