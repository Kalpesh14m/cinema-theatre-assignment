package com.kalpesh.cinematheatre.service;

import java.util.Date;
import java.util.List;

import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.ShowDTO;

public interface ShowService {

	public void addShow(ShowDTO request, Long hallId, Long screenId);

	public void updateShow(ShowDTO showInfo, Long hallId, Long screenId);

	public void deleteShow(Long hallId, Long screenId, Long showId);

	public List<Show> getShows(Long hallId, Long screenId);

	public List<Show> getFilteredShows(Date startDate, Date endDate);

}
