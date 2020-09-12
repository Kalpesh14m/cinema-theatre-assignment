package com.kalpesh.cinematheatre.repo;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.repo.custom.CustomRepo;

@Repository
@Transactional
public interface CinemaRepo extends JpaRepository<Cinema, Long>, CustomRepo {

	Optional<Cinema> findByReleasedDateAndMovieName(LocalDate releasedDate, String movieName);

	Optional<Cinema> findByMovieName(String movieName);

}
