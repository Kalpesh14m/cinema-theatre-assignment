package com.kalpesh.cinematheatre.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.Show;

@Repository
@Transactional
public interface ShowRepo extends JpaRepository<Show, Long> {

	public Optional<Show> findByShowDateAndShowTime(LocalDate showDate, LocalTime showTime);

}
