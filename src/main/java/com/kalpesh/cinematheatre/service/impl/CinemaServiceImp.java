package com.kalpesh.cinematheatre.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.AlreadyRegisteredException;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.dto.CinemaDTO;
import com.kalpesh.cinematheatre.model.dto.CinemaUpdateDTO;
import com.kalpesh.cinematheatre.repo.CinemaRepo;
import com.kalpesh.cinematheatre.service.CinemaService;

@Service
public class CinemaServiceImp implements CinemaService {

	@Autowired
	private CinemaRepo cinemaRepo;

	public void registerCinema(CinemaDTO cinemaDetails) {
		Optional<Cinema> maybeMovieExists = cinemaRepo.findByReleasedDateAndMovieName(cinemaDetails.getReleasedDate(),
				cinemaDetails.getMovieName());
		if (maybeMovieExists.isPresent()) {
			throw new AlreadyRegisteredException(Constant.CINEMA_DETAILS_ALREADY_REGISTER);
		}
		Cinema cinemaEntity = new Cinema();
		BeanUtils.copyProperties(cinemaDetails, cinemaEntity);
		cinemaRepo.save(cinemaEntity);
	}

	public Cinema getCinemaById(Long cinemaId) {
		Optional<Cinema> maybeCinema = cinemaRepo.findById(cinemaId);
		if (!maybeCinema.isPresent()) {
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		}
		return maybeCinema.get();
	}

//	@Override
//	public List<CinemaHall> getHalls(String chName, String chCity) {
//		return cinemaRepo.search(chName, chCity);
//	}

	@Override
	public void updateCinema(CinemaUpdateDTO request) {
		Optional<Cinema> cinema = cinemaRepo.findById(request.getMovieId());
		if (!cinema.isPresent()) {
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		}
		cinema.get().setDirector(request.getDirector());
		cinema.get().setMovieId(request.getMovieId());
		cinema.get().setProducer(request.getProducer());
		cinema.get().setMovieGenre(request.getMovieGenre());
		cinemaRepo.save(cinema.get());

	}

	@Override
	public void deleteCinema(Long cinemaId) {
		Optional<Cinema> maybehall = cinemaRepo.findById(cinemaId);
		if (!maybehall.isPresent()) {
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		}
		cinemaRepo.deleteById(maybehall.get().getMovieId());
	}
}
