package com.kalpesh.cinematheatre.repo.custom;

import java.util.Date;
import java.util.List;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Show;

public interface CustomRepo {
	public List<CinemaHall> search(String chName, String chCity, Long hallId);

	public List<Show> getFilteredShows(Date startDate, Date endDate);

	public List<Booking> getBooking(String bookingUniqueId, String name, String email, Long mobileNumber);

	public List<Cinema> filterByPara(String movieName, String movieGenre, String director, String producer,
			Date releasedDate);

}
