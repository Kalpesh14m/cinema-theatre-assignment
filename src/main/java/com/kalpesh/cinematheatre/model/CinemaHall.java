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

	@OneToMany(mappedBy = "hall", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Screen> screen;

	public List<Screen> getScreen() {
		return screen;
	}

	public void setScreen(List<Screen> screen) {
		this.screen = screen;
	}

	public CinemaHall() {
	}

	public CinemaHall(Long chId, String chName, String chCity, String chState, String chCountry) {
		this.chId = chId;
		this.chName = chName;
		this.chCity = chCity;
		this.chState = chState;
		this.chCountry = chCountry;
	}

	public Long getChId() {
		return chId;
	}

	public void setChId(Long chId) {
		this.chId = chId;
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
