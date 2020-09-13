package com.kalpesh.cinematheatre.service;

import java.util.List;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.dto.BookingDTO;

public interface BookingService {

	public boolean bookShow(BookingDTO request, Long showId);

	public List<Booking> getBookings(String bookingUniqueId, String name, String email, Long mobileNumber);

}
