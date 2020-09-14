package com.kalpesh.cinematheatre.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_hall")
public class CinemaHall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hall_id")
	private Long chId;

	@Column(name = "ch_name", nullable = false)
	private String chName;

	@Column(name = "ch_city", nullable = false)
	private String chCity;

	@Column(name = "ch_state", nullable = false)
	private String chState;

	@Column(name = "ch_country", nullable = false)
	private String chCountry;

	@OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Screen> screen;

	public List<Screen> getScreen() {
		return screen;
	}

	public void setScreen(List<Screen> screen) {
		this.screen = screen;
	}

	public CinemaHall() {
	}

	public Long getChId() {
		return chId;
	}

	public void setChId(Long chId) {
		this.chId = chId;
	}

	public String getChName() {
		return chName.toLowerCase();
	}

	public void setChName(String chName) {
		this.chName = chName.toLowerCase();
	}

	public String getChCity() {
		return chCity.toLowerCase();
	}

	public void setChCity(String chCity) {
		this.chCity = chCity.toLowerCase();
	}

	public String getChState() {
		return chState.toLowerCase();
	}

	public void setChState(String chState) {
		this.chState = chState.toLowerCase();
	}

	public String getChCountry() {
		return chCountry.toLowerCase();
	}

	public void setChCountry(String chCountry) {
		this.chCountry = chCountry.toLowerCase();
	}
}
