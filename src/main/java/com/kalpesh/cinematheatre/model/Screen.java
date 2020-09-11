package com.kalpesh.cinematheatre.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "screen")
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id")
	private Long screenId;

	@Column(name = "ch_name", nullable = false)
	private String chName;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "screen_booked", nullable = false)
	private boolean sBooked;

	@Column(name = "show_date")
	private LocalDate sShowDate;

	@Column(name = "show_time")
	private LocalTime sShowTime;

	@Column(name = "s_capacity", nullable = false)
	private Long sCapacity;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "hall_id")
	@JsonIgnore
	private CinemaHall hall;

	public CinemaHall getHall() {
		return hall;
	}

	public void setHall(CinemaHall hall) {
		this.hall = hall;
	}

	public Screen() {
	}

	public Screen(Long screenId, String chName, String movieName, boolean sBooked, Long sCapacity, CinemaHall hall,
			LocalDate sShowDate, LocalTime sShowTime) {
		this.screenId = screenId;
		this.chName = chName;
		this.movieName = movieName;
		this.sBooked = false;
		this.sCapacity = sCapacity;
		this.sShowDate = sShowDate;
		this.sShowTime = sShowTime;
		this.hall = hall;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public boolean issBooked() {
		return sBooked;
	}

	public void setsBooked(boolean sBooked) {
		this.sBooked = sBooked;
	}

	public Long getsCapacity() {
		return sCapacity;
	}

	public void setsCapacity(Long sCapacity) {
		this.sCapacity = sCapacity;
	}

	public LocalDate getsShowDate() {
		return sShowDate;
	}

	public void setsShowDate(LocalDate sShowDate) {
		this.sShowDate = sShowDate;
	}

	public LocalTime getsShowTime() {
		return sShowTime;
	}

	public void setsShowTime(LocalTime sShowTime) {
		this.sShowTime = sShowTime;
	}

}