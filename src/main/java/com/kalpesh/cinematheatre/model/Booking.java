package com.kalpesh.cinematheatre.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "booking_unique_id", updatable = false, nullable = false)
	private String bookingUniqueId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "number", nullable = false)
	private Long mobileNumber;

	@Column(name = "email", nullable = false)
	private String emailId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "show_id")
	private Show show;

	public Booking() {
		bookingUniqueId = UUID.randomUUID().toString();
	}

	public Booking(Long bookingId, String name, Long mobileNumber, String emailId) {
		super();
		this.bookingId = bookingId;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.bookingUniqueId = UUID.randomUUID().toString();
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getBookingUniqueId() {
		return bookingUniqueId;
	}

	public void setBookingUniqueId(String bookingUniqueId) {
		this.bookingUniqueId = bookingUniqueId;
	}

}