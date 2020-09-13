package com.kalpesh.cinematheatre.service;

import java.time.LocalDate;
import java.util.List;

import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.ShowDTO;

public interface ShowService {

	public boolean addShow(ShowDTO request, Long hallId, Long screenId);

	public boolean updateShow(ShowDTO showInfo, Long hallId, Long screenId);

	public boolean deleteShow(Long hallId, Long screenId, Long showId);

	public List<Show> getShows(Long hallId, Long screenId);

	public List<Show> getFilteredShows(LocalDate startDate, LocalDate endDate);

	public List<Show> getFilteredShows();

}
