package com.kalpesh.cinematheatre.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.Booking;
import com.kalpesh.cinematheatre.repo.custom.CustomRepo;

@Repository
@Transactional
public interface BookingRepo extends JpaRepository<Booking, Long>, CustomRepo {

}
