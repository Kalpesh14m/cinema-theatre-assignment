package com.kalpesh.cinematheatre.service.impl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
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
	public void addShow(ShowDTO request, Long hallId, Long screenId) {

		Optional<Screen> maybeScreen = getScreenById(screenId);
		Optional<Cinema> maybeMovie = getMovieByName(request.getMovieName());
		LocalTime showTime = LocalTime.parse(request.getShowTime());
		Optional<Show> maybeShow = getShowsByDateAndTime(request, showTime);

		if (!getCinemaHallById(hallId).isPresent()) {
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		} else if (!maybeScreen.isPresent()) {
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
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
	}

	@Override
	public void updateShow(ShowDTO request, Long hallId, Long screenId) {

		Optional<Screen> maybeScreen = getScreenById(screenId);
		Optional<Cinema> maybeMovie = getMovieByName(request.getMovieName());
		LocalTime showTime = LocalTime.parse(request.getShowTime());
		Optional<Show> maybeShow = getShowsByDateAndTime(request, showTime);

		if (!getCinemaHallById(hallId).isPresent()) {
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		} else if (!maybeScreen.isPresent()) {
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
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
	}

	@Override
	public void deleteShow(Long hallId, Long screenId, Long showId) {
		Optional<Show> maybeShow = getShowById(showId);

		if (!getCinemaHallById(hallId).isPresent()) {
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		} else if (!getScreenById(screenId).isPresent()) {
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
		} else if (!maybeShow.isPresent()) {
			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
		}
		showRepo.delete(maybeShow.get());
	}

	@Override
	public List<Show> getShows(Long hallId, Long screenId) {
		Optional<CinemaHall> maybeHall = getCinemaHallById(hallId);
		if (!maybeHall.isPresent() || hallId > maybeHall.get().getScreen().size()) {
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		} else if (maybeHall.get().getScreen() == null || screenId > maybeHall.get().getScreen().size()) {
			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
		} else if (maybeHall.get().getScreen().get(screenId.intValue() - 1).getShow().isEmpty()) {
			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
		}
		return maybeHall.get().getScreen().get(screenId.intValue() - 1).getShow();
	}

	@Override
	public List<Show> getFilteredShows(Date startDate, Date endDate) {
		return showRepo.getFilteredShows(startDate, endDate);
	}

	private Optional<Screen> getScreenById(Long screenId) {
		return screenRepo.findById(screenId);
	}

	private Optional<CinemaHall> getCinemaHallById(Long hallId) {
		return hallRepo.findById(hallId);
	}

	private Optional<Cinema> getMovieByName(String movieName) {
		return cinemaRepo.findByMovieName(movieName);
	}

	private Optional<Show> getShowsByDateAndTime(ShowDTO request, LocalTime showTime) {
		return showRepo.findByShowDateAndShowTime(request.getShowDate(), showTime);
	}

	private Optional<Show> getShowById(Long showId) {
		return showRepo.findById(showId);
	}

}
