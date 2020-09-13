package com.kalpesh.cinematheatre.repo.custom;

import java.time.LocalDate;
import java.util.List;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Show;

public interface CustomRepo {
	public List<CinemaHall> search(String chName, String chCity);

	public List<Show> getFilteredShows(LocalDate startDate, LocalDate endDate);

	public List<Booking> getBooking(String bookingUniqueId, String name, String email, Long mobileNumber);
}
