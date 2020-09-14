package com.kalpesh.cinematheatre.service;

import java.util.Date;
import java.util.List;

import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.dto.CinemaDTO;
import com.kalpesh.cinematheatre.model.dto.CinemaUpdateDTO;

public interface CinemaService {

	public void registerCinema(CinemaDTO request);

	public Cinema getCinemaById(Long cinemaId);

	public void updateCinema(CinemaUpdateDTO request);

	public void deleteCinema(Long cinemaId);

	public List<Cinema> getCinemaByFilter(String movieName, String movieGenre, String director, String producer,
			Date releasedDate);

}
