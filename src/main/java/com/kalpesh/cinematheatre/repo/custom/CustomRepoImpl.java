package com.kalpesh.cinematheatre.repo.custom;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Show;

public class CustomRepoImpl implements CustomRepo {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CinemaHall> search(String chName, String chCity) {
		String sql = "FROM CinemaHall h where 1=1 ";
		if (!StringUtils.isEmpty(chName)) {
			sql += String.format("and h.chName = '%s' ", chName);
		}
		if (!StringUtils.isEmpty(chCity)) {
			sql += String.format("and h.chCity = '%s' ", chCity);
		}
		return entityManager.createQuery(sql, CinemaHall.class).getResultList();
	}

	@Override
	public List<Show> getFilteredShows(LocalDate startDate, LocalDate endDate) {
		String sql = "FROM Show s where 1=1 ";
		if (!StringUtils.isEmpty(startDate)) {
			sql += String.format("and s.showDate >= '%tF' ", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			sql += String.format("and s.showDate <= '%tF' ", endDate);
		}
		return entityManager.createQuery(sql, Show.class).getResultList();
	}

	@Override
	public List<Booking> getBooking(String bookingUniqueId, String name, String email, Long mobileNumber) {
		String sql = "FROM Booking b where 1=1 ";
		if (!StringUtils.isEmpty(bookingUniqueId)) {
			sql += String.format("and b.bookingUniqueId = '%s' ", bookingUniqueId);
		}
		if (!StringUtils.isEmpty(name)) {
			sql += String.format("and b.name = '%s' ", name);
		}
		if (!StringUtils.isEmpty(email)) {
			sql += String.format("and b.emailId = '%s' ", email);
		}
		if (!StringUtils.isEmpty(mobileNumber)) {
			sql += String.format("and b.mobileNumber = '%s' ", mobileNumber.toString());
		}
		return entityManager.createQuery(sql, Booking.class).getResultList();
	}
}
