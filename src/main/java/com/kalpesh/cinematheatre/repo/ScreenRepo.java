package com.kalpesh.cinematheatre.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.Screen;

@Repository
@Transactional
public interface ScreenRepo extends JpaRepository<Screen, Long> {

	public List<Screen> findOrderByHall(Long hallId);

}
