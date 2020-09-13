package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.AlreadyRegisteredException;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.model.Screen;
import com.kalpesh.cinematheatre.model.Show;
import com.kalpesh.cinematheatre.model.dto.BookingDTO;
import com.kalpesh.cinematheatre.repo.BookingRepo;
import com.kalpesh.cinematheatre.repo.ShowRepo;
import com.kalpesh.cinematheatre.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private ShowRepo showRepo;

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private ScreenServiceImpl screens;

	@Override
	public boolean bookShow(BookingDTO request, Long showId) {
		Optional<Show> maybeShow = getShowById(showId);
		if (!maybeShow.isPresent()) {
			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
		}
		Screen maybeScreen = maybeShow.get().getScreen();
		if (maybeScreen.getBookingCounter() >= maybeScreen.getsCapacity()) {
			throw new AlreadyRegisteredException(Constant.SHOW_FULL);
		}
		maybeScreen.setBookingCounter(maybeScreen.getBookingCounter() + 1);
		screens.updateScreen(maybeScreen);
		Booking book = new Booking();
		BeanUtils.copyProperties(request, book);
		book.setShow(maybeShow.get());
		bookingRepo.save(book);
		return true;
	}

//	@Override
//	public boolean updateShow(ShowDTO request, Long hallId, Long screenId) {
//
//		Optional<Screen> maybeScreen = getScreenById(screenId);
//		Optional<Cinema> maybeMovie = getMovieByName(request.getMovieName());
//		LocalTime showTime = LocalTime.parse(request.getShowTime());
//		Optional<Show> maybeShow = getShowsByDateAndTime(request, showTime);
//
//		if (!getCinemaHallById(hallId).isPresent()) {
//			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
//		} else if (!maybeScreen.isPresent()) {
//			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
//		} else if (!maybeMovie.isPresent()) {
//			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
//		} else if (maybeShow.isPresent() && maybeShow.get().getShowId() == screenId) {
//			throw new AlreadyRegisteredException(Constant.SHOW_DETAILS_FOUND);
//		}
//		Show sc = new Show();
//		BeanUtils.copyProperties(request, sc);
//		sc.setMovie(maybeMovie.get());
//		sc.setScreen(maybeScreen.get());
//		sc.setShowTime(showTime);
//		showRepo.save(sc);
//		return true;
//	}
//
//	@Override
//	public boolean deleteShow(Long hallId, Long screenId, Long showId) {
//		Optional<Show> maybeShow = getShowById(showId);
//
//		if (!getCinemaHallById(hallId).isPresent()) {
//			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
//		} else if (!getScreenById(screenId).isPresent()) {
//			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
//		} else if (!maybeShow.isPresent()) {
//			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
//		}
//
//		showRepo.delete(maybeShow.get());
//		return true;
//	}
//
//	@Override
//	public List<Show> getShows(Long hallId, Long screenId) {
//		Optional<CinemaHall> maybeHall = getCinemaHallById(hallId);
//		if (!maybeHall.isPresent() || hallId > maybeHall.get().getScreen().size()) {
//			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
//		} else if (maybeHall.get().getScreen() == null || screenId > maybeHall.get().getScreen().size()) {
//			throw new NotFoundException(Constant.SCREEN_DETAILS_NOT_FOUND);
//		} else if (maybeHall.get().getScreen().get(screenId.intValue() - 1).getShow().isEmpty()) {
//			throw new NotFoundException(Constant.SHOW_DETAILS_NOT_FOUND);
//		}
//		return maybeHall.get().getScreen().get(screenId.intValue() - 1).getShow();
//	}
//
//	@Override
//	public List<Show> getFilteredShows(LocalDate startDate, LocalDate endDate) {
//		return showRepo.getFilteredShows(startDate, endDate);
//	}
//

	private Optional<Show> getShowById(Long showId) {
		return showRepo.findById(showId);
	}

	@Override
	public List<Booking> getBookings(String bookingUniqueId, String name, String email, Long mobileNumber) {
		System.out.println("Booking Id: " + bookingUniqueId);
		return bookingRepo.getBooking(bookingUniqueId, name, email, mobileNumber);
	}

}
