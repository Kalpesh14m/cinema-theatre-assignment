package com.kalpesh.cinematheatre.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "screen")
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id")
	private Long screenId;

	@Column(name = "screen_booked", nullable = false)
	private boolean sBooked;

	@Column(name = "s_capacity", nullable = false)
	private Long sCapacity;

	@Column(name = "bookings", nullable = false)
	private Long bookingCounter;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "hall_id")
	@JsonIgnore
	private CinemaHall hall;

	@OneToMany(mappedBy = "screen", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Show> show;

	public Screen() {
		this.sBooked = false;
		this.bookingCounter = (long) 0;
	}

//	public Screen(Long screenId, boolean sBooked, Long sCapacity, Long bookingCounter, CinemaHall hall) {
//		super();
//		this.screenId = screenId;
//		this.sBooked = sBooked;
//		this.sCapacity = sCapacity;
//		this.bookingCounter = bookingCounter;
//		this.hall = hall;
//	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
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

	public Long getBookingCounter() {
		return bookingCounter;
	}

	public void setBookingCounter(Long bookingCounter) {
		this.bookingCounter = bookingCounter;
	}

	public CinemaHall getHall() {
		return hall;
	}

	public void setHall(CinemaHall hall) {
		this.hall = hall;
	}

	public List<Show> getShow() {
		return show;
	}

	public void setShow(List<Show> show) {
		this.show = show;
	}

}