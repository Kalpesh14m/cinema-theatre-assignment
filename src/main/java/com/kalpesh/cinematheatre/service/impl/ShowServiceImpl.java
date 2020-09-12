package com.kalpesh.cinematheatre.service.impl;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.AlreadyRegisteredException;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.ShowDTO;
import com.kalpesh.cinematheatre.repo.CinemaRepo;
import com.kalpesh.cinematheatre.repo.HallRepo;
import com.kalpesh.cinematheatre.repo.ScreenRepo;
import com.kalpesh.cinematheatre.repo.ShowRepo;
import com.kalpesh.cinematheatre.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepo showRepo;

	@Autowired
	private HallRepo hallRepo;

	@Autowired
	private ScreenRepo screenRepo;

	@Autowired
	private CinemaRepo cinemaRepo;

	@Override
	public boolean addShow(ShowDTO request, Long hallId, Long screenId) {
		Optional<CinemaHall> maybeHall = hallRepo.findById(hallId);
		Optional<Screen> maybeScreen = screenRepo.findById(screenId);
		System.out.println("to Movie");
		Optional<Cinema> maybeMovie = cinemaRepo.findByMovieName(request.getMovieName());
		LocalTime showTime = LocalTime.parse(request.getShowTime());
		Optional<Show> maybeShow = showRepo.findByShowDateAndShowTime(request.getShowDate(), showTime);
		if (!maybeHall.isPresent()) {
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		} else if (!maybeScreen.isPresent()) {
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_fOUND);
		} else if (!maybeMovie.isPresent()) {
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		} else if (maybeShow.isPresent() && maybeShow.get().getShowId() == screenId) {
			throw new AlreadyRegisteredException(Constant.SHOW_DETAILS_FOUND);
		}
		Show sc = new Show();
		BeanUtils.copyProperties(request, sc);
		sc.setMovie(maybeMovie.get());
		sc.setScreen(maybeScreen.get());
		sc.setShowTime(showTime);
		showRepo.save(sc);
		return true;
	}

//	@Override
//	public List<Screen> getScreenByHallId(Long hallId) {
//		Optional<CinemaHall> maybeHall = hallRepo.findById(hallId);
//		if (!maybeHall.isPresent()) {
//			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_fOUND);
//		}
//		return maybeHall.get().getScreen();
//	}
//
//	@Override
//	public boolean updateScreen(Screen screenObj) {
//		Optional<Screen> maybeScreenPresent = screenRepo.findById(screenObj.getScreenId());
//		if (!maybeScreenPresent.isPresent()) {
//			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_fOUND);
//		}
//		screenObj.setHall(maybeScreenPresent.get().getHall());
//		screenRepo.save(screenObj);
//		return true;
//	}

}
