package com.kalpesh.cinematheatre.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.cinematheatre.constant.Constant;
import com.kalpesh.cinematheatre.model.Cinema;
import com.kalpesh.cinematheatre.model.dto.CinemaDTO;
import com.kalpesh.cinematheatre.model.dto.CinemaUpdateDTO;
import com.kalpesh.cinematheatre.response.Response;
import com.kalpesh.cinematheatre.service.CinemaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/movie")
@Api(value = "Cinema Controller")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;

	@ApiOperation(value = "Register new Cinema in database and return response")
	@PostMapping(value = "/")
	public ResponseEntity<Response> register(
			@ApiParam(value = "Taking CinemaDTO as a RequestBody") @RequestBody CinemaDTO request) {
		cinemaService.registerCinema(request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.CINEMA_DETAILS_REGISTER_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}

	@ApiOperation(value = "Get Cinema with cinema ID")
	@GetMapping("/{cinemaId}")
	public Cinema hallById(@ApiParam(value = "Taking cinemaId as a path Variable") @PathVariable Long cinemaId) {
		return cinemaService.getCinemaById(cinemaId);
	}

	@ApiOperation(value = "Get Cinema with cinema ID")
	@GetMapping("/")
	public List<Cinema> getAllMovies(
			@ApiParam(value = "Taking cinemaId as a path Variable") @RequestParam(required = false) String movieName,
			@RequestParam(required = false) String movieGenre, @RequestParam(required = false) String director,
			@RequestParam(required = false) String producer,
			@RequestParam(name = "Released Date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releasedDate) {
		return cinemaService.getCinemaByFilter(movieName, movieGenre, director, producer, releasedDate);
	}

	@ApiOperation(value = "Update Movie Information")
	@PutMapping("/")
	public ResponseEntity<Response> updateCinemaInfo(@RequestBody CinemaUpdateDTO request) {
		cinemaService.updateCinema(request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Constant.CINEMA_DETAILS_UPDATED_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}

	@DeleteMapping("/{cinemaId}")
	public ResponseEntity<Response> deleteSource(@PathVariable Long cinemaId) {
		cinemaService.deleteCinema(cinemaId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response(Constant.CINEMA_DETAILS_DELETED_SUCESSFULLY, Constant.OK_RESPONSE_CODE));
	}
}
