package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.dto.ScreenDTO;
import com.kalpesh.cinematheatre.repo.HallRepo;
import com.kalpesh.cinematheatre.repo.ScreenRepo;
import com.kalpesh.cinematheatre.service.ScreenService;
import com.movie.exception.AddressNotFoundException;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private HallRepo hallRepo;

	@Autowired
	private ScreenRepo screenRepo;

	@Override
	public boolean addScreen(ScreenDTO screen, Long hallId) {
		Optional<CinemaHall> maybeHall = hallRepo.findById(hallId);
		Screen sc = new Screen();
		BeanUtils.copyProperties(screen, sc);
		sc.setHall(maybeHall.get());
		screenRepo.save(sc);
		return true;
	}

	@Override
	public List<Screen> getScreenByHallId(Long hallId) {
		List<Screen> screen = screenRepo.findOrderByHall(hallId);
		if (screen == null) {
			throw new AddressNotFoundException(Constant.SCREEN_DETAILS_NOT_fOUND, Constant.NOT_FOUND_RESPONSE_CODE);
		}
		return screen;
	}

	@Override
	public boolean updateScreen(Screen screenObj) {
		Optional<Screen> maybeScreenPresent = screenRepo.findById(screenObj.getScreenId());
		if (!maybeScreenPresent.isPresent()) {
			throw new AddressNotFoundException(Constant.SCREEN_DETAILS_NOT_fOUND, Constant.NOT_FOUND_RESPONSE_CODE);
		}
		screenObj.setHall(maybeScreenPresent.get().getHall());
		screenRepo.save(screenObj);
		return true;
	}

}