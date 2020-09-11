package com.kalpesh.cinematheatre.service;

import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.dto.CinemaDTO;
import com.kalpesh.cinematheatre.model.dto.CinemaUpdateDTO;

public interface CinemaService {

	public void registerCinema(CinemaDTO request);

//	public List<CinemaHall> getHalls(String chName, String chCity);

	public Cinema getCinemaById(Long cinemaId);

	public void updateCinema(CinemaUpdateDTO request);

	public void deleteCinema(Long cinemaId);

}
