package com.kalpesh.cinematheatre.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.CinemaHall;

@Repository
@Transactional
public interface HallRepo extends JpaRepository<CinemaHall, Long> {

//	public Optional<CinemaHall> findById(Long userId);

	public Optional<CinemaHall> findByChCityAndChName(String chCity, String chName);

}
