package com.kalpesh.cinematheatre.service;

import com.kalpesh.cinematheatre.model.dto.ShowDTO;

public interface ShowService {

	boolean addShow(ShowDTO request, Long hallId, Long screenId);

//	public List<Screen> getScreenByHallId(Long userId);
//
//	public boolean updateScreen(Screen address);
}
