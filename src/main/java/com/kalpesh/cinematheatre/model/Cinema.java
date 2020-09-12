package com.kalpesh.cinematheatre.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinema")
public class Cinema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long movieId;

	@Column(name = "movie_name", nullable = false)
	private String movieName;

	@Column(name = "movie_genre", nullable = false)
	private String movieGenre;

	@Column(name = "director", nullable = false)
	private String director;

	@Column(name = "producer", nullable = false)
	private String producer;

	@Column(name = "release_date", nullable = false)
	private LocalDate releasedDate;

	public Cinema() {
	}
//
//	public Cinema(Long movieId, String movieName, String movieGenre, String director, String producer,
//			LocalDate releasedDate, Show movieShow) {
//		super();
//		this.movieId = movieId;
//		this.movieName = movieName;
//		this.movieGenre = movieGenre;
//		this.director = director;
//		this.producer = producer;
//		this.releasedDate = releasedDate;
//		this.movieShow = movieShow;
//	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

}
