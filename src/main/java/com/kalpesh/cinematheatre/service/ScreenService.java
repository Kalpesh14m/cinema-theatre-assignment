package com.kalpesh.cinematheatre.service;

import java.util.List;

import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.dto.ScreenDTO;

public interface ScreenService {

	public boolean addScreen(ScreenDTO address, Long userId);

	public List<Screen> getScreenByHallId(Long userId);

	public boolean updateScreen(Screen address);
}
