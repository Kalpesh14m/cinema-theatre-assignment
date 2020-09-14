package com.kalpesh.cinematheatre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.exception.AlreadyRegisteredException;
import com.kalpesh.cinematheatre.exception.NotFoundException;
import com.kalpesh.cinematheatre.model.CinemaHall;
import com.kalpesh.cinematheatre.model.dto.CinemaHallDTO;
import com.kalpesh.cinematheatre.repo.HallRepo;
import com.kalpesh.cinematheatre.service.HallService;

@Service
public class HallServiceImp implements HallService {

	@Autowired
	private HallRepo hallRepo;

	public void registerHall(CinemaHallDTO hallDetails) {
		if (getHallByCithAndName(hallDetails.getChCity(), hallDetails.getChName()).isPresent())
			throw new AlreadyRegisteredException(Constant.HALL_DETAILS_ALREADY_REGISTER);
		CinemaHall hallEntity = new CinemaHall();
		BeanUtils.copyProperties(hallDetails, hallEntity);
		hallRepo.save(hallEntity);
	}

	@Override
	public List<CinemaHall> getHalls(String chName, String chCity, Long hallId) {
		return hallRepo.search(chName, chCity, hallId);
	}

	@Override
	public void updateHall(CinemaHall request) {
		if (getHalls(null, null, request.getChId()) == null)
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		if (getHallByCithAndName(request.getChCity(), request.getChName()).isPresent())
			throw new AlreadyRegisteredException(Constant.HALL_DETAILS_ALREADY_REGISTER);
		hallRepo.save(request);
	}

	@Override
	public void deleteHall(Long hallId) {
		CinemaHall hall = getHalls(null, null, hallId).get(0);
		if (hall == null)
			throw new NotFoundException(Constant.HALL_DETAILS_NOT_FOUND);
		hallRepo.deleteById(hall.getChId());
	}

	private Optional<CinemaHall> getHallByCithAndName(String chCity, String chName) {
		return hallRepo.findByChCityAndChName(chCity, chName);
	}
}
