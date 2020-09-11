package com.kalpesh.cinematheatre.service;

import java.util.List;

import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.dto.CinemaHallDTO;

public interface HallService {

	public void registerHall(CinemaHallDTO request);

	public List<CinemaHall> getHalls(String chName, String chCity);

	public CinemaHall getHallById(Long hallId);

	public void updateHall(CinemaHall request);

	public void deleteHall(Long hallId);

}
