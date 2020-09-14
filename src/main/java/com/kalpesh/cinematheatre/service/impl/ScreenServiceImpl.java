package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.dto.ScreenDTO;
import com.kalpesh.cinematheatre.repo.HallRepo;
import com.kalpesh.cinematheatre.repo.ScreenRepo;
import com.kalpesh.cinematheatre.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private HallRepo hallRepo;

	@Autowired
	private ScreenRepo screenRepo;

	@Override
	public void addScreen(ScreenDTO screen, Long hallId) {
		Optional<CinemaHall> maybeHall = getHallById(hallId);
		if (!maybeHall.isPresent())
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		Screen sc = new Screen();
		BeanUtils.copyProperties(screen, sc);
		sc.setHall(maybeHall.get());
		screenRepo.save(sc);
	}

	@Override
	public List<Screen> getScreenByHallId(Long hallId) {
		Optional<CinemaHall> maybeHall = getHallById(hallId);
		if (!maybeHall.isPresent())
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		return maybeHall.get().getScreen();
	}

	@Override
	public void updateScreen(Screen screenObj) {
		Optional<Screen> maybeScreenPresent = getScreenById(screenObj.getScreenId());
		if (!maybeScreenPresent.isPresent())
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
		screenObj.setHall(maybeScreenPresent.get().getHall());
		screenRepo.save(screenObj);
	}

	@Override
	public void deleteScreen(Long screenId) {
		Optional<Screen> maybeScreen = getScreenById(screenId);
		if (!maybeScreen.isPresent())
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
		hallRepo.deleteById(maybeScreen.get().getScreenId());
	}

	private Optional<Screen> getScreenById(Long screenId) {
		return screenRepo.findById(screenId);
	}

	private Optional<CinemaHall> getHallById(Long hallId) {
		return hallRepo.findById(hallId);
	}
}
