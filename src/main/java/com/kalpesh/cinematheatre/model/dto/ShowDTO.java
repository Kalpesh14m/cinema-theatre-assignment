package com.kalpesh.cinematheatre.model.dto;

import java.time.LocalDate;

public class ShowDTO {

	private LocalDate showDate;
	private String showTime;
	private String movieName;

	public ShowDTO() {
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

}
