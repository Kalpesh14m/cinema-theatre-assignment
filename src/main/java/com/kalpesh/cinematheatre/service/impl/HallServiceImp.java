package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.dto.CinemaHallDTO;
import com.kalpesh.cinematheatre.repo.HallRepo;
import com.kalpesh.cinematheatre.service.HallService;
import com.movie.exception.HallAlreadyRegisteredException;
import com.movie.exception.HallNotFoundException;

@Service
public class HallServiceImp implements HallService {

	@Autowired
	private HallRepo hallRepo;

	public void registerHall(CinemaHallDTO hallDetails) {
		Optional<CinemaHall> maybeHallExists = hallRepo.findByChCityAndChName(hallDetails.getChCity(),
				hallDetails.getChName());
		if (maybeHallExists.isPresent()) {
			throw new HallAlreadyRegisteredException(Constant.HALL_ALREADY_REGISTER);
		}
		CinemaHall hallEntity = new CinemaHall();
		BeanUtils.copyProperties(hallDetails, hallEntity);
		hallRepo.save(hallEntity);
	}

	public CinemaHall getHallById(Long hallId) {
		Optional<CinemaHall> maybeHall = hallRepo.findById(hallId);
		if (!maybeHall.isPresent()) {
			throw new HallNotFoundException(Constant.HALL_NOT_FOUND);
		}
		return maybeHall.get();
	}

	@Override
	public List<CinemaHall> getHalls(String chName, String chCity) {
		return hallRepo.search(chName, chCity);
	}

	@Override
	public void updateHall(CinemaHall request) {
		Optional<CinemaHall> hall = hallRepo.findById(request.getChId());
		Optional<CinemaHall> maybeHall = hallRepo.findByChCityAndChName(request.getChCity(), request.getChName());
		if (!hall.isPresent()) {
			throw new HallNotFoundException(Constant.HALL_NOT_FOUND);
		} else if (maybeHall.isPresent()) {
			throw new HallAlreadyRegisteredException(Constant.HALL_ALREADY_REGISTER);
		} else {
			hallRepo.save(request);
		}
	}

	@Override
	public void deleteHall(Long hallId) {
		Optional<CinemaHall> maybehall = hallRepo.findById(hallId);
		if (!maybehall.isPresent()) {
			throw new HallNotFoundException(Constant.HALL_NOT_FOUND);
		}
		hallRepo.deleteById(maybehall.get().getChId());
	}
}
