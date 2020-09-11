package com.kalpesh.cinematheatre.repo.custom;

import java.util.List;

import com.kalpesh.cinematheatre.model.CinemaHall;

public interface CustomRepo {
	public List<CinemaHall> search(String chName, String chCity);
}
