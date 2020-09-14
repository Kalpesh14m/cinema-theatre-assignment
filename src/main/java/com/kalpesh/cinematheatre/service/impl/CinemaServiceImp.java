package com.kalpesh.cinematheatre.service.impl;

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
import com.kalpesh.cinematheatre.model.dto.CinemaDTO;
import com.kalpesh.cinematheatre.model.dto.CinemaUpdateDTO;
import com.kalpesh.cinematheatre.repo.CinemaRepo;
import com.kalpesh.cinematheatre.service.CinemaService;

@Service
public class CinemaServiceImp implements CinemaService {

	@Autowired
	private CinemaRepo cinemaRepo;

	public void registerCinema(CinemaDTO cinemaDetails) {
		if (getMovieWithNameAndReleaseDate(cinemaDetails).isPresent())
			throw new AlreadyRegisteredException(Constant.CINEMA_DETAILS_ALREADY_REGISTER);
		Cinema cinemaEntity = new Cinema();
		BeanUtils.copyProperties(cinemaDetails, cinemaEntity);
		cinemaRepo.save(cinemaEntity);
	}

	public Cinema getCinemaById(Long cinemaId) {
		Optional<Cinema> maybeCinema = cinemaRepo.findById(cinemaId);
		if (!maybeCinema.isPresent())
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		return maybeCinema.get();
	}

	@Override
	public void updateCinema(CinemaUpdateDTO request) {
		Cinema cinema = getCinemaById(request.getMovieId());
		if (cinema == null)
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		BeanUtils.copyProperties(request, cinema);
		cinemaRepo.save(cinema);
	}

	@Override
	public void deleteCinema(Long cinemaId) {
		Cinema maybehall = getCinemaById(cinemaId);
		if (maybehall == null)
			throw new NotFoundException(Constant.CINEMA_DETAILS_NOT_FOUND);
		cinemaRepo.deleteById(maybehall.getMovieId());
	}

	@Override
	public List<Cinema> getCinemaByFilter(String movieName, String movieGenre, String director, String producer,
			Date releasedDate) {
		return cinemaRepo.filterByPara(movieName, movieGenre, director, producer, releasedDate);
	}

	private Optional<Cinema> getMovieWithNameAndReleaseDate(CinemaDTO cinemaDetails) {
		return cinemaRepo.findByReleasedDateAndMovieName(cinemaDetails.getReleasedDate(), cinemaDetails.getMovieName());
	}
}
