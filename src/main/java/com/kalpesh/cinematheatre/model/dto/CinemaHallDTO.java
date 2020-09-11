package com.kalpesh.cinematheatre.model.dto;

public class CinemaHallDTO {

	private String chName;
	private String chCity;
	private String chState;
	private String chCountry;

	public CinemaHallDTO() {
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChCity() {
		return chCity;
	}

	public void setChCity(String chCity) {
		this.chCity = chCity;
	}

	public String getChState() {
		return chState;
	}

	public void setChState(String chState) {
		this.chState = chState;
	}

	public String getChCountry() {
		return chCountry;
	}

	public void setChCountry(String chCountry) {
		this.chCountry = chCountry;
	}

}
