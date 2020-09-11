package com.kalpesh.cinematheatre.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class ScreenDTO {
	private String chName;
	private String movieName;
	private Long sCapacity;
	private LocalDate sShowDate;
	private LocalTime sShowTime;

	public ScreenDTO() {
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
