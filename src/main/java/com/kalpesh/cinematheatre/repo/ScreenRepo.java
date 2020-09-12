package com.kalpesh.cinematheatre.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpesh.cinematheatre.model.Screen;

@Repository
@Transactional
public interface ScreenRepo extends JpaRepository<Screen, Long> {

}
