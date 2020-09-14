package com.kalpesh.cinematheatre.repo.custom;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.utils.DateUtil;

public class CustomRepoImpl implements CustomRepo {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CinemaHall> search(String chName, String chCity, Long hallId) {
		String sql = "FROM CinemaHall h where 1=1 ";
		if (!StringUtils.isEmpty(chName)) {
			sql += String.format("and h.chName = '%s' ", chName);
		}
		if (!StringUtils.isEmpty(chCity)) {
			sql += String.format("and h.chCity = '%s' ", chCity);
		}
		if (!StringUtils.isEmpty(hallId)) {
			sql += String.format("and h.chId = '%s' ", hallId);
		}
		return entityManager.createQuery(sql, CinemaHall.class).getResultList();
	}

	@Override
	public List<Show> getFilteredShows(Date startDate, Date endDate) {
		String sql = "FROM Show s where 1=1 ";
		if (!StringUtils.isEmpty(startDate)) {
			sql += String.format("and s.showDate >= '%tF' ", DateUtil.dateToLocalDate(startDate));
		}
		if (!StringUtils.isEmpty(endDate)) {
			sql += String.format("and s.showDate <= '%tF' ", DateUtil.dateToLocalDate(endDate));
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

	@Override
	public List<Cinema> filterByPara(String movieName, String movieGenre, String director, String producer,
			Date releasedDate) {
		String sql = "FROM Cinema c where 1=1 ";
		if (!StringUtils.isEmpty(movieName)) {
			sql += String.format("and c.movieName = '%s' ", movieName);
		}
		if (!StringUtils.isEmpty(movieGenre)) {
			sql += String.format("and c.movieGenre = '%s' ", movieGenre);
		}
		if (!StringUtils.isEmpty(director)) {
			sql += String.format("and c.director = '%s' ", director);
		}
		if (!StringUtils.isEmpty(producer)) {
			sql += String.format("and c.producer = '%s' ", producer);
		}
		if (!StringUtils.isEmpty(releasedDate)) {
			sql += String.format("and c.releasedDate = '%tF' ", DateUtil.dateToLocalDate(releasedDate));
		}
		return entityManager.createQuery(sql, Cinema.class).getResultList();
	}

}
