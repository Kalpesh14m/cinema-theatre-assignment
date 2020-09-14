package com.kalpesh.cinematheatre.service;

import java.util.List;

import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.dto.ScreenDTO;

public interface ScreenService {

	public void addScreen(ScreenDTO address, Long userId);

	public List<Screen> getScreenByHallId(Long userId);

	public void updateScreen(Screen address);

	public void deleteScreen(Long screenId);
}
