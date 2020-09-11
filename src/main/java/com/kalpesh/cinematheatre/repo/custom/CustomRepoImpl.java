package com.kalpesh.cinematheatre.repo.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import com.kalpesh.cinematheatre.model.CinemaHall;

public class CustomRepoImpl implements CustomRepo {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CinemaHall> search(String chName, String chCity) {
		String sql = "FROM CinemaHall h where 1=1 ";
		if (!StringUtils.isEmpty(chName)) {
			sql += String.format("and h.chName = '%s' ", chName);
		}
		if (!StringUtils.isEmpty(chCity)) {
			sql += String.format("and h.chCity = '%s' ", chCity);
		}
		return entityManager.createQuery(sql, CinemaHall.class).getResultList();
	}
}
